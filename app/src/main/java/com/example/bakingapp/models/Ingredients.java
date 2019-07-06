package com.example.bakingapp.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "desirableRecipeIngredients")
public class Ingredients implements Serializable {

    @ColumnInfo(name = "quantity")
    @SerializedName("quantity")
    private float quantity;

    @ColumnInfo(name = "measure")
    @SerializedName("measure")
    private String measure;

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "ingredient")
    @SerializedName("ingredient")
    private String ingredient;

    public Ingredients(float quantity,String measure,String ingredient){
        this.quantity=quantity;
        this.measure=measure;
        this.ingredient=ingredient;
    }

    public float getQuantity(){return quantity;}

    public String getMeasure() {
        return measure;
    }

    public String getIngredient() {
        return ingredient;
    }
}
