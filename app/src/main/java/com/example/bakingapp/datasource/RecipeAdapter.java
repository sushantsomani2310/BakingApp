package com.example.bakingapp.datasource;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bakingapp.R;
import com.example.bakingapp.models.Recipe;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private List<Recipe> recipeList;
    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.recipe_rv_item,viewGroup,false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder recipeViewHolder, int i) {
        recipeViewHolder.recipeNameTextView.append(recipeList.get(i).getName());
    }

    @Override
    public int getItemCount() {
        if(null!=recipeList) return recipeList.size();
        return 0;
    }

    class RecipeViewHolder extends RecyclerView.ViewHolder{

        private TextView recipeNameTextView;

        public RecipeViewHolder(View view){
            super(view);
            recipeNameTextView= (TextView) view.findViewById(R.id.recipe_name_textview);
        }
    }

    public void setRecipeList(List<Recipe> recipeList){
        this.recipeList=recipeList;
        notifyDataSetChanged();
    }
}
