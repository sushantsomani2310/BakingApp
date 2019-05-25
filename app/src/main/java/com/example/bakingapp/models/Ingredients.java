package com.example.bakingapp.models;

import com.google.gson.annotations.SerializedName;

public class Ingredients {
    @SerializedName("quantity")
    private float quantity;

    @SerializedName("measure")
    private String measure;

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
