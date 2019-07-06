package com.example.bakingapp.views;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.bakingapp.R;
import com.example.bakingapp.datasource.RecipeStepsAdapter;
import com.example.bakingapp.models.Recipe;
import com.example.bakingapp.viewmodel.RecipeDetailViewModel;
import com.example.bakingapp.views.fragments.RecipeStepsFragment;
import com.example.bakingapp.views.fragments.RecipeVideoStepFragment;

public class RecipeDetailActivity extends AppCompatActivity {

    private RecyclerView stepsRecyclerView;
    private TextView ingredientsTextView;
    private RecipeStepsAdapter recipeStepsAdapter;
    private boolean isMultiPane = false;
    private FragmentManager fragmentManager;
    private RecipeStepsFragment stepFrag;
    private Recipe selectedRecipe;
    private RecipeDetailViewModel recipeDetailViewModel;

    @Override
    protected void onCreate(Bundle onSavedInstanceState){
        super.onCreate(onSavedInstanceState);
        setContentView(R.layout.recipe_detail_layout);

        //initialize view model
        recipeDetailViewModel = ViewModelProviders.of(this).get(RecipeDetailViewModel.class);
        fragmentManager = getSupportFragmentManager();
        stepFrag = (RecipeStepsFragment) fragmentManager.findFragmentById(R.id.recipe_detail_steps_fragment);

        if(findViewById(R.id.recipe_video_tablet_pane)!=null){
            // make tablet layout
            isMultiPane = true;
            stepFrag.setMultiPane(true);
            createTabletLayout(onSavedInstanceState);
        }
        else stepFrag.setIsMultiPane(false);

        //fetch intent to get selected Recipe
        selectedRecipe = (Recipe)getIntent().getSerializableExtra("RECIPE_DETAILS");
    }

    private void createTabletLayout(Bundle onSavedInstanceState){
        RecipeVideoStepFragment videoStepFragment = new RecipeVideoStepFragment();
        stepFrag.setIsMultiPane(true);

        fragmentManager.beginTransaction()
                .add(R.id.video_container,videoStepFragment)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.set_desirable_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int selectedItem = item.getItemId();
        switch (selectedItem){
            case R.id.set_desirable :
                recipeDetailViewModel.setDesirableRecipeIngredients(selectedRecipe.getIngredients());
                break;
        }
        return true;
    }
}
