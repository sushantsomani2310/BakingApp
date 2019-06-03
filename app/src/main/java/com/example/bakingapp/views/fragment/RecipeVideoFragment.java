package com.example.bakingapp.views.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    private ImageView nextStepImageView,prevStepImageView;
    private boolean isLandscapeMode = false;
    private int recipeStepIndex,numSteps;
    private List<RecipeSteps> recipeSteps;
    private View view;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle bundle){
        view = inflater.inflate(R.layout.recipe_video_layout,container,false);
        recipeSteps = (ArrayList<RecipeSteps>)getActivity().getIntent().getSerializableExtra("RECIPE_STEPS_LIST");
        recipeStepIndex = getActivity().getIntent().getIntExtra("RECIPE_STEP_INDEX",1);

        numSteps = recipeSteps.size();
        playerView = view.findViewById(R.id.playerView);
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

    @Override
    public void onStart(){
        super.onStart();
        if(!isLandscapeMode) {
            nextStepImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(recipeStepIndex>=numSteps){
                        //show user that at the last step of recipe
                        Toast.makeText(getContext(),"Recipe completed",Toast.LENGTH_LONG).show();
                    }
                    else{
                        recipeStepIndex++;
                        player=null;
                        setDescriptionContent();
                    }
                }
            });

            prevStepImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(recipeStepIndex==0){
                        Toast.makeText(getContext(),"At first step",Toast.LENGTH_LONG).show();
                    }
                    else{
                        recipeStepIndex--;
                        player=null;
                        setDescriptionContent();
                    }
                }
            });
        }
    }

    private void setDescriptionContent(){
        currentStep = recipeSteps.get(recipeStepIndex);
        if(!currentStep.getVideoURL().isEmpty()) initializePlayer(Uri.parse(currentStep.getVideoURL()));
        else {
            Toast.makeText(getContext(),"No video found",Toast.LENGTH_LONG).show();
            player = null;
            initializePlayer(Uri.parse(currentStep.getVideoURL()));
        }
        //implies that phone in landscape mode
        if(view.findViewById(R.id.video_landscape_mode)!=null){
            isLandscapeMode = true;
        }
        else{
            isLandscapeMode = false;
            stepDescriptionTextView = view.findViewById(R.id.step_instruction_textview);
            stepDescriptionTextView.setText("");
            stepDescriptionTextView.append(currentStep.getDescription());
            nextStepImageView = view.findViewById(R.id.next_step_imageView);
            prevStepImageView = view.findViewById(R.id.prev_step_imageView);
        }
    }
}
