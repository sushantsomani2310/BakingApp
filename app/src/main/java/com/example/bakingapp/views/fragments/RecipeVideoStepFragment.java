package com.example.bakingapp.views.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bakingapp.R;
import com.example.bakingapp.models.Recipe;
import com.example.bakingapp.models.RecipeSteps;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.ArrayList;
import java.util.List;

public class RecipeVideoStepFragment extends Fragment {

    private SimpleExoPlayerView playerView;
    private SimpleExoPlayer player;
    private int recipeStepIndex,numSteps;
    private List<RecipeSteps> recipeSteps;
    private RecipeSteps currentStep;
    public RecipeVideoStepFragment(){

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle bundle){
        View view = inflater.inflate(R.layout.exo_player_solo,container,false);
        playerView = view.findViewById(R.id.playerView2);
        recipeSteps = (ArrayList<RecipeSteps>)getActivity().getIntent().getSerializableExtra("RECIPE_STEPS_LIST");
        recipeStepIndex = getActivity().getIntent().getIntExtra("RECIPE_STEP_INDEX",1);

        Recipe selectedRecipe = (Recipe)getActivity().getIntent().getSerializableExtra("RECIPE_DETAILS");

        if(null==recipeSteps) {
            recipeSteps = selectedRecipe.getRecipeSteps();
        }

        if(getArguments()!=null) {
            currentStep = (RecipeSteps) getArguments().getSerializable("RecipeStep");
        }
        else currentStep = recipeSteps.get(recipeStepIndex);
        setDescriptionContent();
        return view;
    }

    private void initializePlayer(Uri mediaUri){
        if(null==player){
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            player = ExoPlayerFactory.newSimpleInstance(getContext(),trackSelector,loadControl);
            playerView.setPlayer(player);
            String userAgent = Util.getUserAgent(getContext(),"Baking App");
            MediaSource source =new ExtractorMediaSource(mediaUri,new DefaultDataSourceFactory(
                    getContext(),userAgent
            ),new DefaultExtractorsFactory(),null,null
            );
            player.prepare(source);
            player.setPlayWhenReady(true);
        }
    }

    /**
     * Release ExoPlayer.
     */
    private void releasePlayer() {
        player.stop();
        player.release();
        player = null;
    }

    /**
     * Release the player when the activity is destroyed.
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        releasePlayer();
    }

    private void setDescriptionContent(){
        if(!currentStep.getVideoURL().isEmpty()) initializePlayer(Uri.parse(currentStep.getVideoURL()));
        else {
            Toast.makeText(getContext(),"No video found",Toast.LENGTH_LONG).show();
            player = null;
            initializePlayer(Uri.parse(currentStep.getVideoURL()));
        }
    }

    @Override
    public void onPause(){
        super.onPause();
        //Pause the exoplayer
        pausePlayer();
    }

    @Override
    public void onResume(){
        super.onResume();
        //Start the exoplayer
        startPlayer();
    }

    private void pausePlayer(){
        player.setPlayWhenReady(false);
        player.getPlaybackState();
    }
    private void startPlayer(){
        player.setPlayWhenReady(true);
        player.getPlaybackState();
    }
}
