package com.example.bakingapp.utility;

import android.content.Context;

import com.example.bakingapp.datasource.RecipeData;

public class BakingRepository {

    private Context context;

    //singleton object of repository
    private static Object LOCK =new Object();
    private static BakingRepository sInstance;

    private BakingRepository(Context context){
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
}
