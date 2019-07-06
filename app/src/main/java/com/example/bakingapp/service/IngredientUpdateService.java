package com.example.bakingapp.service;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

import com.example.bakingapp.models.Ingredients;
import com.example.bakingapp.utility.BakingRepository;

import java.util.List;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * helper methods.
 */
public class IngredientUpdateService extends IntentService {
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    public static final String UPDATE_INGREDIENTS = "com.example.bakingapp.service.action.UPDATE_INGREDIENTS";

    public IngredientUpdateService() {
        super("IngredientUpdateService");
    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    public static void startIngredientsWidgetUpdate(Context context) {
        Intent intent = new Intent(context, IngredientUpdateService.class);
        intent.setAction(UPDATE_INGREDIENTS);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (UPDATE_INGREDIENTS.equals(action)) {
                getDesirableRecipeIngredients();
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void getDesirableRecipeIngredients() {
        BakingRepository repository = BakingRepository.getInstance(getApplicationContext());
        List<Ingredients> favIngedientsList = repository.getDesirableRecipeIngredients();
        StringBuffer favIng = new StringBuffer();
        for(int i=0;i<(favIngedientsList.size()-1);i++){
            favIng.append(favIngedientsList.get(i).getIngredient()+", ");
        }
        favIng.append(favIngedientsList.get((favIngedientsList.size()-1)).getIngredient());
        String desirableRecipeIngredient = favIng.toString();
    }
}
