package com.example.bakingapp.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RecipeSteps implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("shortDescription")
    private String shortDescription;

    @SerializedName("description")
    private String description;

    @SerializedName("videoURL")
    private String videoURL;

    @SerializedName("thumbnailURL")
    private String thumbnailURL;

    public RecipeSteps(int id,String shortDescription,String description,String videoURL,String thumbnailURL){
        this.id=id;
        this.shortDescription=shortDescription;
        this.description=description;
        this.videoURL=videoURL;
        this.thumbnailURL=thumbnailURL;
    }

    public int getId() {
        return id;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

}
