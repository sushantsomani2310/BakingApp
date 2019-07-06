package com.example.bakingapp.views.widgets;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.RemoteViews;

import com.example.bakingapp.R;
import com.example.bakingapp.datasource.RecipeData;
import com.example.bakingapp.models.Ingredients;
import com.example.bakingapp.service.IngredientUpdateService;
import com.example.bakingapp.utility.BakingRepository;

import java.util.List;

/**
 * Implementation of App Widget functionality.
 */
public class BakingAppWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.baking_app_widget);
        views.setTextViewText(R.id.appwidget_text, widgetText);

        //TODO fetch ingredients from Room DB
        //BakingRepository repository = BakingRepository.getInstance(context);
        //repository.getDesirableRecipeIngredients();

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        /*for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }*/
        IngredientUpdateService.startIngredientsWidgetUpdate(context);
        int sdfs=45;
    }

    @Override
    public void onEnabled(Context context) {
        int sdfs=45;
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        int sdfs=45;
        // Enter relevant functionality for when the last widget is disabled
    }
}

