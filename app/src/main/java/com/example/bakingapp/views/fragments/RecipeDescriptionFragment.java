package com.example.bakingapp.views.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bakingapp.R;

public class RecipeDescriptionFragment extends Fragment {
    private TextView descriptionTextView;
    public RecipeDescriptionFragment(){}
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle bundle){
        View view = inflater.inflate(R.layout.recipe_description_solo,container,false);
        descriptionTextView = view.findViewById(R.id.step_instruction_textview);
        String stepDesc = getArguments().getString("StepDesc");
        descriptionTextView.setText(stepDesc);
        return view;
    }
}
