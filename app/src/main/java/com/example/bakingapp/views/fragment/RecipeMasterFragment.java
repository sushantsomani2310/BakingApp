package com.example.bakingapp.views.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.bakingapp.R;
import com.example.bakingapp.datasource.RecipeAdapter;
import com.example.bakingapp.datasource.RecipeData;
import com.example.bakingapp.models.Recipe;
import com.example.bakingapp.viewmodel.MainViewModel;

import java.util.List;

public class RecipeMasterFragment extends Fragment implements RecipeData.RecipeDataResponseListener,RecipeAdapter.ListItemClickListener{

    private RecyclerView recipeRecyclerView;
    private RecipeAdapter recipeAdapter;
    private MainViewModel mainViewModel;

    public RecipeMasterFragment(){

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle bundle){
        View view = inflater.inflate(R.layout.fragment_master_list,container,false);
        recipeRecyclerView = (RecyclerView) view.findViewById(R.id.recipe_recycler_view);
        recipeAdapter = new RecipeAdapter(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recipeRecyclerView.setLayoutManager(layoutManager);
        recipeRecyclerView.setAdapter(recipeAdapter);
        recipeRecyclerView.setHasFixedSize(true);
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.fetchRecipeData(this);
        return view;
    }

    @Override
    public void onRecipeDataResponse(List<Recipe> recipesList) {
        //COMPLETED populate list in Recycler View here
        recipeAdapter.setRecipeList(recipesList);
    }

    @Override
    public void onRecipeFetchError(String errorMessage) {
       //Toast.makeText(getC,"Oops: "+errorMessage,Toast.LENGTH_LONG).show();
    }

    /**
     * handles item click on a recycler view
     * @param selectedRecipe is the selected recipe by the user
     */
    @Override
    public void onListItemClick(Recipe selectedRecipe) {
        //TODO
        int sdf=354;
    }
}
