package com.example.bakingapp.datasource;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.bakingapp.datasource.dao.DesirableRecipeDao;
import com.example.bakingapp.models.Ingredients;
import com.example.bakingapp.models.Recipe;
import com.example.bakingapp.utility.AppExecutors;

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

    /**
     * sets the ingredients of favourite recipe item in room database
     * @param ingredients of favourite recipe
     * @param context need to refer to RoomDB instance
     */
    public static void setDesirableRecipeIngredients(final List<Ingredients> ingredients, Context context){
        final BakingAppRoomDatabase roomDatabase = BakingAppRoomDatabase.getInstance(context);
        AppExecutors appExecutors = AppExecutors.getInstance();
        appExecutors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                //delete old data from table
                DesirableRecipeDao desirableRecipeDao = roomDatabase.getDesirableRecipe();
                desirableRecipeDao.clearDesirableRecipe();
                //insert new list of ingredients in the table
                desirableRecipeDao.insertDesirableRecipe(ingredients);
            }
        });
    }

    /**
     * reads the ingredients of favourite recipe from room db
     * @param context need to refer to RoomDB instance
     */
    public static void getDesirableRecipeIngredients(Context context){
        final BakingAppRoomDatabase roomDatabase = BakingAppRoomDatabase.getInstance(context);
        AppExecutors appExecutors = AppExecutors.getInstance();
        appExecutors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                DesirableRecipeDao desirableRecipeDao = roomDatabase.getDesirableRecipe();
                List<Ingredients> desirableIngredients = desirableRecipeDao.getDesirableRecipe();
            }
        });
    }
}
