package com.example.bakingapp.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.bakingapp.R;
import com.example.bakingapp.models.Recipe;

public class RecipeDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle onSavedInstanceState){
        super.onCreate(onSavedInstanceState);
        setContentView(R.layout.fragment_detail_master);
        Recipe selectedRecipe = (Recipe)getIntent().getSerializableExtra("RECIPE_DETAILS");
        getSupportActionBar().setTitle(selectedRecipe.getName());
    }
}
