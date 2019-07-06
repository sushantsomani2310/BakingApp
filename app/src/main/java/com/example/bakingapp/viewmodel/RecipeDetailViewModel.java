package com.example.bakingapp.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;

import com.example.bakingapp.models.Ingredients;
import com.example.bakingapp.utility.BakingRepository;

import java.util.List;

public class RecipeDetailViewModel extends AndroidViewModel {

    private BakingRepository repository;

    RecipeDetailViewModel(Application application){
        super(application);
        repository = BakingRepository.getInstance(application);
    }

    public void setDesirableRecipeIngredients(final List<Ingredients> ingredients){
        repository.setDesirableRecipeIngredients(ingredients);
    }
}
