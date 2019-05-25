package com.example.bakingapp.views;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.bakingapp.R;
import com.example.bakingapp.datasource.RecipeData;
import com.example.bakingapp.models.Recipe;
import com.example.bakingapp.viewmodel.MainViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements RecipeData.RecipeDataResponseListener {

    private MainViewModel mainViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.fetchRecipeData(this);
    }

    @Override
    public void onRecipeDataResponse(List<Recipe> recipesList) {
        int sdf=45;
    }

    @Override
    public void onRecipeFetchError(String errorMessage) {
        Toast.makeText(this,"Oops: "+errorMessage,Toast.LENGTH_LONG).show();
    }
}
