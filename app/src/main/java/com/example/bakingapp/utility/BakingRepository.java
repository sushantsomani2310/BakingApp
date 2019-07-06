package com.example.bakingapp.utility;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.example.bakingapp.datasource.BakingAppRoomDatabase;
import com.example.bakingapp.datasource.RecipeData;
import com.example.bakingapp.datasource.dao.DesirableRecipeDao;
import com.example.bakingapp.models.Ingredients;
import com.example.bakingapp.models.Recipe;

import java.util.List;

public class BakingRepository {

    private Context context;
    private List<Ingredients> desirableIngredients;
    private DesirableRecipeDao desirableRecipeDao;
    //singleton object of repository
    private static Object LOCK =new Object();
    private static BakingRepository sInstance;

    private BakingRepository(Context context){
        BakingAppRoomDatabase roomDatabase = BakingAppRoomDatabase.getInstance(context);
        desirableRecipeDao = roomDatabase.getDesirableRecipe();
        this.context = context;
    }

    public static BakingRepository getInstance(Context context){
        if(null==sInstance){
            synchronized (LOCK){
                sInstance = new BakingRepository(context);
            }
        }
        return sInstance;
    }

    /**
     * fetch the recipe response using retrofit
     * and trigger a callback to send the result
     * to the main thread
     * @param responseListener callback listener
     */
    public void fetchRecipeData(RecipeData.RecipeDataResponseListener responseListener){
        RecipeData.getRecipeData(responseListener);
    }

    /**
     * sets the desirable recipe ingredients in the room database
     * @param ingredients value to be inserted in room database
     */
    public void setDesirableRecipeIngredients(final List<Ingredients> ingredients){
        RecipeData.setDesirableRecipeIngredients(ingredients,context);
    }

    public List<Ingredients> getDesirableRecipeIngredients(){
        return RecipeData.getDesirableRecipeIngredients(context);
    }
}
