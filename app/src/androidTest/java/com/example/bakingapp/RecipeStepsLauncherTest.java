package com.example.bakingapp;

import android.support.v7.widget.RecyclerView;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;

import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.bakingapp.views.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;

@RunWith(AndroidJUnit4.class)
public class RecipeStepsLauncherTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void tapRecipeItem(){
        try {
            //onView(ViewMatchers.withId(R.id.recipe_recycler_view))
              //      .perform(RecyclerViewActions.actionOnItemAtPosition(2, click()));
            ViewInteraction vi = onView(ViewMatchers.withId(R.id.recipe_recycler_view));
            vi.perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));

            int sdf = 45;
        }
        catch (Exception ex){
            int sfsd= 34;
        }
    }
}
