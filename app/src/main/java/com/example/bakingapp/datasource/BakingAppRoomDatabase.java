package com.example.bakingapp.datasource;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.bakingapp.datasource.dao.DesirableRecipeDao;
import com.example.bakingapp.models.Ingredients;

@Database(entities = {Ingredients.class},version = 1,exportSchema = false)
public abstract class BakingAppRoomDatabase extends RoomDatabase {
    private static volatile BakingAppRoomDatabase sInstance;
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME="POPULAR_MOVIES_DATABASE";

    public static BakingAppRoomDatabase getInstance(Context context){
        if(sInstance==null){
            synchronized (LOCK){
                sInstance = Room.databaseBuilder(context,BakingAppRoomDatabase.class,
                        DATABASE_NAME)
                        .fallbackToDestructiveMigration()
                        .build();
            }
        }
        return sInstance;
    }

    public abstract DesirableRecipeDao getDesirableRecipe();
}
