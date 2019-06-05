package com.example.bakingapp.datasource;

import android.support.annotation.NonNull;

import com.example.bakingapp.models.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecipeData {

    private static final String RECIPE_DATA_URL = "https://d17h27t6h515a5.cloudfront.net/";
    private static Retrofit retrofit;

    public interface RecipeDataResponseListener{
        void onRecipeDataResponse(List<Recipe> recipesList);
        void onRecipeFetchError(String errorMessage);
    }

    public static void getRecipeData(final RecipeDataResponseListener responseListener){
        if(null==retrofit){
            retrofit = new Retrofit.Builder()
                    .baseUrl(RECIPE_DATA_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        RecipeApiService recipeApiService = retrofit.create(RecipeApiService.class);
        Call<List<Recipe>> call = recipeApiService.getPopularRecipes();
        call.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(@NonNull Call<List<Recipe>> call, @NonNull Response<List<Recipe>> response) {
                responseListener.onRecipeDataResponse(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<Recipe>> call, @NonNull Throwable t) {
                responseListener.onRecipeFetchError(t.toString());
            }
        });
    }

}
