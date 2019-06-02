package com.example.bakingapp.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Recipe implements Serializable {
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("ingredients")
    private List<Ingredients> ingredients;

    @SerializedName("steps")
    private List<RecipeSteps> recipeSteps;

    @SerializedName("servings")
    private int servings;

    @SerializedName("image")
    private String image;

    public Recipe(int id,String name,List<Ingredients> ingredients,List<RecipeSteps> recipeSteps,
                  int servings,String image){
        this.id=id;
        this.name=name;
        this.ingredients=ingredients;
        this.recipeSteps=recipeSteps;
        this.servings=servings;
        this.image=image;
    }

    public int getId() {
        return id;
    }

    public List<Ingredients> getIngredients() {
        return ingredients;
    }

    public String getName() {
        return name;
    }

    public List<RecipeSteps> getRecipeSteps() {
        return recipeSteps;
    }

    public int getServings() {
        return servings;
    }

    public String getImage() {
        return image;
    }
}
