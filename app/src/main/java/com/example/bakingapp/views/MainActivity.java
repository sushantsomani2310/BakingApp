package com.example.bakingapp.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.bakingapp.R;
import com.example.bakingapp.datasource.RecipeAdapter;
import com.example.bakingapp.datasource.RecipeData;
import com.example.bakingapp.models.Recipe;
import com.example.bakingapp.viewmodel.MainViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements RecipeData.RecipeDataResponseListener,RecipeAdapter.ListItemClickListener{

    private RecyclerView recipeRecyclerView;
    private RecipeAdapter recipeAdapter;
    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recipeRecyclerView = (RecyclerView) findViewById(R.id.recipe_recycler_view);
        recipeAdapter = new RecipeAdapter(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recipeRecyclerView.setLayoutManager(layoutManager);
        recipeRecyclerView.setAdapter(recipeAdapter);
        recipeRecyclerView.setHasFixedSize(true);
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.fetchRecipeData(this);
    }

    @Override
    public void onListItemClick(Recipe selectedRecipe) {
        Intent recipeDetailsIntent = new Intent(this, RecipeDetailActivity.class);
        recipeDetailsIntent.putExtra("RECIPE_DETAILS",selectedRecipe);
        startActivity(recipeDetailsIntent);
    }

    @Override
    public void onRecipeDataResponse(List<Recipe> recipesList) {
        recipeAdapter.setRecipeList(recipesList);
    }

    @Override
    public void onRecipeFetchError(String errorMessage) {

    }
}
