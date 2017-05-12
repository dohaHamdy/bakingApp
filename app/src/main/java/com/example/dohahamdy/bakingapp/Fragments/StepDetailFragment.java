package com.example.dohahamdy.bakingapp.Fragments;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.dohahamdy.bakingapp.Network.Network;
import com.example.dohahamdy.bakingapp.R;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class StepDetailFragment extends Fragment {
    public static String TAG="STEPVIDEO";
    public String videoString;
    private VideoView mVideo;
    private TextView mDesc;
    private Button mNext;
    int stepIndex;
    int recipeIndex;
    String stepDesc;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_step_detail, container, false);
        mVideo=(VideoView)view.findViewById(R.id.videoView);
        mNext=(Button)view.findViewById(R.id.nextStep);
        mDesc=(TextView)view.findViewById(R.id.shortDesc);

        loadVideoData(1);
mNext.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        stepIndex++;

        new FetchVideo().execute(recipeIndex,stepIndex,1);

    }
});
        return view;
    }

    private void loadVideoData(int type){
        Intent intent = getActivity().getIntent();
        if (intent != null) {
            if (intent.hasExtra(StepDetailFragment.TAG)&&intent.hasExtra("recipeID")) {

                stepIndex =Integer.parseInt(intent.getStringExtra(StepDetailFragment.TAG));
                recipeIndex=intent.getIntExtra("recipeID",-1);
                new FetchVideo().execute(recipeIndex,stepIndex,type);
                mDesc.setText(stepDesc);
            }
        }

    }

    public class FetchVideo extends AsyncTask<Integer,Void,List<String>> {
        @Override
        protected List<String> doInBackground(Integer... params) {
            int recipeIndex=params[0];
            int stepIndex=params[1];
            int type=params[2];
            URL requestUrl= Network.buildTheUrl(type);
            try {
                String jsonresponse=Network.getResponse(requestUrl,type);
                String video= Network.StepJson.getVideo(jsonresponse,recipeIndex,stepIndex);
                String description= Network.StepJson.getDescription(jsonresponse,recipeIndex,stepIndex);
                List<String> datas=new ArrayList<>();
                datas.add(video);
                datas.add(description);
                return datas;
            }catch (Exception e){
                return null;
            }


        }

        @Override
        protected void onPostExecute(List<String> datas) {
            if(datas !=null){
                if(datas.get(0)!=null&&datas.get(1)!=null) {
                    videoString = datas.get(0);
                    mDesc.setText(datas.get(1));
                    if(videoString=="") {
                        mVideo.setVideoPath(videoString);
                        mVideo.setMediaController(new MediaController(getContext()));
                        mVideo.requestFocus();
                        mVideo.start();
                    }
                }

            }
        }
    }



}


