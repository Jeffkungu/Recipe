package com.sqisland.tutorial.recipes.ui.recipe;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;

import com.sqisland.tutorial.recipes.R;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isSelected;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

/**
 * Created by Jeffkungu on 20/02/2018.
 */
public class RecipeActivityTest {

    @Rule
    public ActivityTestRule<RecipeActivity> activityRule
            = new ActivityTestRule<>(RecipeActivity.class, true, false);


    // Method to test when the Activity is launched without any id passed to the intent.
    @Test
    public void recipeNotFound() {
        activityRule.launchActivity(null);

        onView(withId(R.id.tvDescription))
                .check(matches(withText((R.string.recipe_not_found))));
        onView(withId(R.id.tvTitlle))
                .check(matches(not(isDisplayed())));
    }

    // Method to test to test content of the RecipeActivity views when launched with an id.
    // also tests onClick event handler on the recipe title.
    @Test
    public void clickToFavourite() {
        Intent intent = new Intent();
        intent.putExtra(RecipeActivity.KEY_ID, "creamed_carrots");
        activityRule.launchActivity(intent);

        onView(withId(R.id.tvTitlle))
                .check(matches(withText(("Creamed Carrots"))))
                .check(matches(not(isSelected())))
                .perform(click())
                .check(matches(isSelected()));
    }
}