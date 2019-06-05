package com.example.bakingapp.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;

import com.example.bakingapp.utility.BakingRepository;
import com.example.bakingapp.datasource.RecipeData;

public class MainViewModel extends AndroidViewModel {

    private BakingRepository repository;
    public MainViewModel(Application application){
        super(application);
        repository =BakingRepository.getInstance(application.getApplicationContext());
    }

    public void fetchRecipeData(RecipeData.RecipeDataResponseListener responseListener){
        repository.fetchRecipeData(responseListener);
    }
}
