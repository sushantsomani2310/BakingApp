package com.example.bakingapp;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.anything;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.hasToString;
import static org.hamcrest.Matchers.startsWith;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.assertEquals;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewAction.*;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.bakingapp.views.MainActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class RecipeStepsLauncherTest {

    //@Rule
    //public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    private IdlingResource idlingResource;

    @Before
    public void registerIdlingResource(){
        ActivityScenario activityScenario = ActivityScenario.launch(MainActivity.class);
        activityScenario.onActivity(new ActivityScenario.ActivityAction<MainActivity>() {
            @Override
            public void perform(MainActivity activity) {
                idlingResource = activity.getIdlingResource();
                IdlingRegistry.getInstance().register(idlingResource);
            }
        });
    }

    @Test
    public void tapRecipeItem(){
        try {
            //onView(allOf(withId(R.id.recipe_name_textview), withText("Yellow Cake"))).check(matches(isDisplayed()));
            onView(allOf(withId(R.id.recipe_name_textview), withText("Brownies"))).check(matches(isDisplayed())).perform(click());
            int sdf=45;
            // onData(anything()).inAdapterView(withId(R.id.tea_grid_view)).atPosition(1).perform(click());
        }
        catch (Exception ex){
            int sdf=45;
        }
    }

    @After
    public void unregisterIdlingResource(){
        if(idlingResource!=null) IdlingRegistry.getInstance().unregister(idlingResource);
    }
}
