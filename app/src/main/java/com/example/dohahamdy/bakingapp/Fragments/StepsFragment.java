package com.example.dohahamdy.bakingapp.Fragments;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.dohahamdy.bakingapp.Adapter.StepAdapter;
import com.example.dohahamdy.bakingapp.Data.RecipeData;
import com.example.dohahamdy.bakingapp.Data.StepData;
import com.example.dohahamdy.bakingapp.Network.Network;
import com.example.dohahamdy.bakingapp.R;

import java.net.URL;
import java.util.List;

public class StepsFragment extends Fragment implements StepAdapter.StepAdapterOnClickHandler
{

    public static String TAG="STEP";
    //private TextView mYextError;
    private ProgressBar mLoading;
    private RecyclerView mcyclerView;
    private RecyclerView.LayoutManager mLayoutManger;
    private StepAdapter adapt;
    private RecipeData recipeData;
    private List<StepData> stepDatas;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_steps, container, false);
        mLoading=(ProgressBar)view.findViewById(R.id.loadingStep);
        mcyclerView=(RecyclerView) view.findViewById(R.id.recycleStep);

        adapt=new StepAdapter( getContext());
        mcyclerView.setAdapter(adapt);

        mLayoutManger=new GridLayoutManager(getContext(),1);
        mcyclerView.setLayoutManager(mLayoutManger);
        mcyclerView.setHasFixedSize(true);

        showLoading();

        loadStepData(0);

        return view;
    }

    @Override
    public void onClick(StepData data) {
        Intent intent=new Intent(getContext(),getClass());
        intent.putExtra("step detal",data);

        startActivity(intent);
    }

    private void loadStepData(int type){
        showStepData();
        Intent intent = getActivity().getIntent();
        if (intent != null) {
            if (intent.hasExtra(StepsFragment.TAG)) {
                int index;
                index =Integer.parseInt(intent.getStringExtra(StepsFragment.TAG));
                adapt.setRecipeID(index);
                new FetchRecipeDetailData().execute(index,type);

            }
        }

    }
    private void showStepData(){
        mLoading.setVisibility(View.INVISIBLE);
        mcyclerView.setVisibility(View.VISIBLE);
    }


    private void showLoading(){
        mcyclerView.setVisibility(View.INVISIBLE);
        mLoading.setVisibility(View.VISIBLE);
    }

    public class FetchRecipeDetailData extends AsyncTask<Integer,Void,List<StepData>> {
        @Override
        protected List<StepData> doInBackground(Integer... params) {
            URL requestUrl= Network.buildTheUrl(params[1]);
            try {
                String jsonresponse=Network.getResponse(requestUrl,params[1]);
                List<StepData> stepDataJSON= Network.StepJson.getSimpleData(/*getContext(),*/jsonresponse,params[0]);
                return stepDataJSON;
            }catch (Exception e){
                return null;
            }


        }

        @Override
        protected void onPostExecute(List<StepData> datas) {
            if(datas !=null){
                stepDatas=datas;
                adapt.setStepData(stepDatas);
            }
        }
    }



}

