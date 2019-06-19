package com.example.bakingapp.views;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.bakingapp.R;
import com.example.bakingapp.models.RecipeSteps;
import com.example.bakingapp.views.fragments.RecipeDescriptionFragment;
import com.example.bakingapp.views.fragments.RecipeVideoStepFragment;

import java.util.ArrayList;
import java.util.List;

public class RecipeVideoActivity extends AppCompatActivity {

    private int recipeStepIndex,numSteps,stepIndex;
    private List<RecipeSteps> recipeSteps;
    private ImageView prevButton,nextButton;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_video);

        prevButton = findViewById(R.id.prev_button);
        nextButton = findViewById(R.id.next_button);
        recipeSteps = (ArrayList<RecipeSteps>) getIntent().getSerializableExtra("RECIPE_STEPS_LIST");
        stepIndex = (int) getIntent().getSerializableExtra("RECIPE_STEP_INDEX");
        RecipeSteps step = recipeSteps.get(stepIndex);
        fragmentManager = getSupportFragmentManager();

        RecipeVideoStepFragment videoStepFragment = new RecipeVideoStepFragment();
        fragmentManager.beginTransaction()
                .add(R.id.video_container,videoStepFragment)
                .commit();

        Bundle descBundle = new Bundle();
        descBundle.putString("StepDesc",step.getDescription());
        RecipeDescriptionFragment descriptionFragment = new RecipeDescriptionFragment();
        descriptionFragment.setArguments(descBundle);
        fragmentManager.beginTransaction()
                .add(R.id.description_container,descriptionFragment)
                .commit();
    }

    @Override
    public void onStart(){
        super.onStart();
        //recipeSteps,recipeStepIndex
        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(stepIndex!=0){
                    // play previous video and show previous description
                    stepIndex--;
                    RecipeSteps futureStep = recipeSteps.get(stepIndex);
                    Bundle descBundle = new Bundle();
                    descBundle.putString("StepDesc",futureStep.getDescription());
                    RecipeDescriptionFragment descriptionFragment = new RecipeDescriptionFragment();
                    descriptionFragment.setArguments(descBundle);
                    fragmentManager.beginTransaction()
                            .replace(R.id.description_container,descriptionFragment)
                            .commit();

                    RecipeVideoStepFragment recipeVideoStepFragment = new RecipeVideoStepFragment();
                    Bundle videoBundle = new Bundle();
                    videoBundle.putSerializable("RecipeStep",futureStep);
                    recipeVideoStepFragment.setArguments(videoBundle);
                    fragmentManager.beginTransaction()
                            .replace(R.id.video_container,recipeVideoStepFragment)
                            .commit();
                }
                else{
                    Toast.makeText(getApplicationContext(),"This is first step",Toast.LENGTH_LONG).show();
                }
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int totalSteps = recipeSteps.size();
                if(stepIndex!=(totalSteps-1)){
                    // play next video and show next description
                    stepIndex++;
                    RecipeSteps futureStep = recipeSteps.get(stepIndex);
                    Bundle descBundle = new Bundle();
                    descBundle.putString("StepDesc",futureStep.getDescription());
                    RecipeDescriptionFragment descriptionFragment = new RecipeDescriptionFragment();
                    descriptionFragment.setArguments(descBundle);
                    fragmentManager.beginTransaction()
                            .replace(R.id.description_container,descriptionFragment)
                            .commit();

                    RecipeVideoStepFragment recipeVideoStepFragment = new RecipeVideoStepFragment();
                    Bundle videoBundle = new Bundle();
                    videoBundle.putSerializable("RecipeStep",futureStep);
                    recipeVideoStepFragment.setArguments(videoBundle);
                    fragmentManager.beginTransaction()
                            .replace(R.id.video_container,recipeVideoStepFragment)
                            .commit();
                }
                else{
                    Toast.makeText(getApplicationContext(),"No more steps",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
