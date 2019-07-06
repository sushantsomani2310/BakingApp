package com.example.bakingapp.datasource.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.bakingapp.models.Ingredients;

import java.util.List;

@Dao
public interface DesirableRecipeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertDesirableRecipe(List<Ingredients> favRecipe);

    @Query("SELECT * FROM desirableRecipeIngredients")
    List<Ingredients> getDesirableRecipe();

    @Query("DELETE FROM desirableRecipeIngredients")
    void clearDesirableRecipe();
}
