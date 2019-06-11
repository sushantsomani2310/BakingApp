package com.example.bakingapp.datasource;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bakingapp.R;
import com.example.bakingapp.models.RecipeSteps;

import java.util.List;

public class RecipeStepsAdapter extends RecyclerView.Adapter<RecipeStepsAdapter.RecipeStepsViewHolder> {

    private StepSelectItemListener selectItemListener;
    private boolean isMultiPane;

    public interface StepSelectItemListener{
        void onStepSelectedListener(List<RecipeSteps> recipeSteps, int stepIndex);
    }

   public RecipeStepsAdapter(StepSelectItemListener selectItemListener,boolean isMultiPane) {
        this.selectItemListener = selectItemListener;
        this.isMultiPane=isMultiPane;
    }

    private List<RecipeSteps> recipeSteps;
    @NonNull
    @Override
    public RecipeStepsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.recipe_step_item,viewGroup,false);
        return new RecipeStepsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeStepsViewHolder recipeStepsViewHolder, int i) {
        recipeStepsViewHolder.stepDescriptionTextView.append(recipeSteps.get(i).getShortDescription());
    }

    @Override
    public int getItemCount() {
        if(null!=recipeSteps) return recipeSteps.size();
        return 0;
    }

    class RecipeStepsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView stepDescriptionTextView;
        private RecipeStepsViewHolder(View view){
            super(view);
            stepDescriptionTextView = (TextView)view.findViewById(R.id.recipe_step_desc_textview);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int selectedStep = getAdapterPosition();
            selectItemListener.onStepSelectedListener(recipeSteps,selectedStep);
        }
    }

    public void setRecipeSteps(List<RecipeSteps> recipeSteps){
        this.recipeSteps=recipeSteps;
        notifyDataSetChanged();
    }
}
