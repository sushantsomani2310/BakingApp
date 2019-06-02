package com.example.bakingapp.views.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bakingapp.R;
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

public class RecipeVideoFragment extends Fragment {

    private SimpleExoPlayer player;
    private SimpleExoPlayerView playerView;
    private RecipeSteps currentStep;
    private TextView stepDescriptionTextView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle bundle){
        View view = inflater.inflate(R.layout.recipe_video_layout,container,false);

        List<RecipeSteps> recipeSteps = (ArrayList<RecipeSteps>)getActivity().getIntent().getSerializableExtra("RECIPE_STEPS_LIST");
        int recipeStepIndex = getActivity().getIntent().getIntExtra("RECIPE_STEP_INDEX",1);
        currentStep = recipeSteps.get(recipeStepIndex);
        playerView = view.findViewById(R.id.playerView);
        stepDescriptionTextView = view.findViewById(R.id.step_instruction_textview);
        if(!currentStep.getVideoURL().isEmpty()) initializePlayer(Uri.parse(currentStep.getVideoURL()));
        stepDescriptionTextView.append(currentStep.getDescription());
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
}
