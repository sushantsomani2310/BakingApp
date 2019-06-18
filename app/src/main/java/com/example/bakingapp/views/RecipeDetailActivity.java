package com.example.bakingapp.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.bakingapp.R;
import com.example.bakingapp.datasource.RecipeStepsAdapter;
import com.example.bakingapp.views.fragments.RecipeStepsFragment;
import com.example.bakingapp.views.fragments.RecipeVideoStepFragment;

public class RecipeDetailActivity extends AppCompatActivity {

    private RecyclerView stepsRecyclerView;
    private TextView ingredientsTextView;
    private RecipeStepsAdapter recipeStepsAdapter;
    private boolean isMultiPane = false;
    private FragmentManager fragmentManager;
    private RecipeStepsFragment stepFrag;

    @Override
    protected void onCreate(Bundle onSavedInstanceState){
        super.onCreate(onSavedInstanceState);
        setContentView(R.layout.recipe_detail_layout);

        fragmentManager = getSupportFragmentManager();
        stepFrag = (RecipeStepsFragment) fragmentManager.findFragmentById(R.id.recipe_detail_steps_fragment);

        if(findViewById(R.id.recipe_video_tablet_pane)!=null){
            // make tablet layout
            isMultiPane = true;
            stepFrag.setMultiPane(true);
            createTabletLayout(onSavedInstanceState);
        }
        else stepFrag.setIsMultiPane(false);
    }

    private void createTabletLayout(Bundle onSavedInstanceState){
        RecipeVideoStepFragment videoStepFragment = new RecipeVideoStepFragment();
        //Bundle fragmentBundle = new Bundle();
        //fragmentBundle.putBoolean("isMultiPane",isMultiPane);
        //videoStepFragment.setArguments(fragmentBundle);

        stepFrag.setIsMultiPane(true);

        fragmentManager.beginTransaction()
                .add(R.id.video_container,videoStepFragment)
                .commit();
    }
}
