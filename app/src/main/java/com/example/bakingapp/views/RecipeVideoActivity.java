package com.example.bakingapp.views;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.bakingapp.R;
import com.example.bakingapp.models.RecipeSteps;
import com.example.bakingapp.views.fragments.RecipeVideoStepFragment;

import java.util.ArrayList;
import java.util.List;

public class RecipeVideoActivity extends AppCompatActivity {

    private int recipeStepIndex,numSteps;
    private List<RecipeSteps> recipeSteps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_video);

        RecipeVideoStepFragment videoStepFragment = new RecipeVideoStepFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.video_container,videoStepFragment)
                .commit();


    }
}
