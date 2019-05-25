package com.example.bakingapp.datasource;

import com.example.bakingapp.models.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RecipeApiService {
    @GET("topher/2017/May/59121517_baking/baking.json")
    Call<List<Recipe>> getPopularRecipes();
}
