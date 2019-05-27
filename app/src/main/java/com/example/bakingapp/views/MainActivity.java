package com.example.bakingapp.views;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.bakingapp.R;
import com.example.bakingapp.datasource.RecipeData;
import com.example.bakingapp.models.Recipe;
import com.example.bakingapp.viewmodel.MainViewModel;
import com.example.bakingapp.views.fragment.RecipeMasterFragment;

import java.util.List;

public class MainActivity extends AppCompatActivity{

    //private MainViewModel mainViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager recipeFragmentManager = getSupportFragmentManager();
//        RecipeMasterFragment recipeMasterFragment = new RecipeMasterFragment();
//
//        recipeFragmentManager.beginTransaction()
//                .add(R.id.recipe_list_master_fragment,recipeMasterFragment)
//                .commit();

        //mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        //mainViewModel.fetchRecipeData(this);
    }

//    @Override
//    public void onRecipeDataResponse(List<Recipe> recipesList) {
//        int sdf=45;
//    }
//
//    @Override
//    public void onRecipeFetchError(String errorMessage) {
//        Toast.makeText(this,"Oops: "+errorMessage,Toast.LENGTH_LONG).show();
//    }
}
