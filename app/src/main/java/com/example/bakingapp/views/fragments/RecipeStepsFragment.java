package com.example.bakingapp.views.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bakingapp.R;
import com.example.bakingapp.datasource.RecipeStepsAdapter;
import com.example.bakingapp.models.Ingredients;
import com.example.bakingapp.models.Recipe;
import com.example.bakingapp.models.RecipeSteps;
import com.example.bakingapp.views.RecipeVideoActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * this fragment shows layout of recipe step details
 * this does not include recipe video and specific step instruction
 */
public class RecipeStepsFragment extends Fragment implements RecipeStepsAdapter.StepSelectItemListener{

    private RecyclerView stepsRecyclerView;
    private TextView ingredientsTextView;
    private RecipeStepsAdapter recipeStepsAdapter;
    private boolean isMultiPane = false;
    private List<RecipeSteps> recipeStepsList;
    private Recipe selectedRecipe;

    public RecipeStepsFragment(){

    }

    public void setIsMultiPane(boolean isMultiPane){
        recipeStepsAdapter = new RecipeStepsAdapter(this,isMultiPane);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        stepsRecyclerView.setLayoutManager(layoutManager);
        stepsRecyclerView.setAdapter(recipeStepsAdapter);
        stepsRecyclerView.setHasFixedSize(true);

        recipeStepsAdapter.setRecipeSteps(recipeStepsList);
        List<Ingredients> ingredientsList = selectedRecipe.getIngredients();
        for(int i=0;i<ingredientsList.size();i++){
            if(i==(ingredientsList.size()-1)){
                ingredientsTextView.append(ingredientsList.get(i).getIngredient()+" "+ingredientsList.get(i).getQuantity()+
                        ingredientsList.get(i).getMeasure());
            }
            else{
                ingredientsTextView.append(ingredientsList.get(i).getIngredient()+" "+ingredientsList.get(i).getQuantity()+
                        ingredientsList.get(i).getMeasure()+", ");
            }
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle bundle){
        View view = inflater.inflate(R.layout.recipe_step_detail,container,false);
        Intent recipeIntent = getActivity().getIntent();
        selectedRecipe = (Recipe)recipeIntent.getSerializableExtra("RECIPE_DETAILS");
        recipeStepsList = selectedRecipe.getRecipeSteps();
        stepsRecyclerView=(RecyclerView)view.findViewById(R.id.recipe_steps_recyclerview);
        ingredientsTextView=(TextView)view.findViewById(R.id.ingredients_textView);
        return view;
    }

    @Override
    public void onStepSelectedListener(List<RecipeSteps> recipeSteps, int stepIndex) {
        Intent videoIntent = new Intent(getActivity(), RecipeVideoActivity.class);
        videoIntent.putExtra("RECIPE_STEPS_LIST",(ArrayList<RecipeSteps>)recipeSteps);
        videoIntent.putExtra("RECIPE_STEP_INDEX",stepIndex);
        startActivity(videoIntent);
    }
}
