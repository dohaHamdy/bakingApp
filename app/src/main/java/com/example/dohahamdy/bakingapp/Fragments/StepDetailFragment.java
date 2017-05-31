package com.example.dohahamdy.bakingapp.Fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaCodec;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.dohahamdy.bakingapp.Network.Network;
import com.example.dohahamdy.bakingapp.R;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StepDetailFragment extends Fragment implements  ExoPlayer.EventListener{
    public static String TAG="STEPVIDEO";
    public String videoString;
    @BindView(R.id.shortDesc) TextView mDesc;
    @BindView(R.id.nextStep) Button mNext;
    @BindView(R.id.default_image)ImageView mImage;
    private SimpleExoPlayer player;
    //private ExoPlayer player;
    private static final int BUFFER_SEGMENT_SIZE = 64 * 1024;
    private static final int BUFFER_SEGMENT_COUNT = 256;
    int stepIndex;
    int recipeIndex;
    String stepDesc;
    SimpleExoPlayerView playerView;
    DataSource.Factory dataSourceFactory;
    ExtractorsFactory extractorsFactory;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_step_detail, container, false);
        ButterKnife.bind(this,view);

        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stepIndex ++;
                new FetchVideo().execute(recipeIndex,stepIndex,1);
            }
        });
        // 1. Create a default TrackSelector
        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelection.Factory videoTrackSelectionFactory =
                new AdaptiveTrackSelection.Factory(bandwidthMeter);
        TrackSelector trackSelector =
                new DefaultTrackSelector(videoTrackSelectionFactory);

        // 2. Create a default LoadControl
        LoadControl loadControl = new DefaultLoadControl();
        // 3. Create the player
        player = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector, loadControl);

        playerView = (SimpleExoPlayerView) view.findViewById(R.id.player_view);
        playerView.setPlayer(player);
        playerView.setKeepScreenOn(true);
        // Produces DataSource instances through which media data is loaded.
        dataSourceFactory = new DefaultDataSourceFactory(getContext(), Util.getUserAgent(getContext(), "ExoPlayer"));

        // Produces Extractor instances for parsing the media data.
        extractorsFactory = new DefaultExtractorsFactory();
        player.addListener(this);
        loadVideoData(1);
        return view;
    }

    private void loadVideoData(int type){
        Intent intent = getActivity().getIntent();
        if (intent != null) {
            if (intent.hasExtra(StepDetailFragment.TAG)&&intent.hasExtra("recipeID")) {

                stepIndex =Integer.parseInt(intent.getStringExtra(StepDetailFragment.TAG));
                recipeIndex=intent.getIntExtra("recipeID",-1);
                new FetchVideo().execute(recipeIndex,stepIndex,type);
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
                datas.add(0,video);
                datas.add(1,description);
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
                    if(!videoString.equals("")) {
                        Log.d(TAG, "onPostExecute: Video");
                        mImage.setVisibility(View.INVISIBLE);
                        playerView.setVisibility(View.VISIBLE);

                        MediaSource videoSource = new ExtractorMediaSource(Uri.parse(videoString),
                                dataSourceFactory, extractorsFactory, null, null);
                        // Prepare the player with the source.

                        player.prepare(videoSource);
                        playerView.requestFocus();
                        player.setPlayWhenReady(true);
                    }
                    else
                    {
                        mImage.setVisibility(View.VISIBLE);

                        Log.d(TAG, "onPostExecute: no Video");
                        playerView.setVisibility(View.INVISIBLE);

                    }
                }
                else{
                }

            }
        }
    }


    @Override
    public void onTimelineChanged(Timeline timeline, Object manifest) {

    }

    @Override
    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

    }

    @Override
    public void onLoadingChanged(boolean isLoading) {

    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
        switch (playbackState) {
            case ExoPlayer.STATE_BUFFERING:
                //You can use progress dialog to show user that video is preparing or buffering so please wait
                break;
            case ExoPlayer.STATE_IDLE:
                //idle state
                break;
            case ExoPlayer.STATE_READY:
                // dismiss your dialog here because our video is ready to play now
                break;
            case ExoPlayer.STATE_ENDED:
                // do your processing after ending of video
                break;
        }
    }

    @Override
    public void onPlayerError(ExoPlaybackException error) {
        // show user that something went wrong. I am showing dialog but you can use your way
        AlertDialog.Builder adb = new AlertDialog.Builder(StepDetailFragment.this.getContext());
        adb.setTitle("Could not able to stream video");
        adb.setMessage("It seems that something is going wrong.\nPlease try again.");
        adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                getActivity().finish(); // take out user from this activity. you can skip this
            }
        });
        AlertDialog ad = adb.create();
        ad.show();
    }

    @Override
    public void onPositionDiscontinuity() {
        //Video is not streaming properly
        Log.d("Mayur", "Discontinuity");
    }

    @Override
    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

    }

    @Override
    public void onPause() {
        super.onPause();
        if (player != null) {
            player.setPlayWhenReady(false); //to pause a video because now our video player is not in focus

        }
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
        player.release();   //it is important to release a player
    }
}


