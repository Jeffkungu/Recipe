package com.sqisland.tutorial.recipes.ui.recipe;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.sqisland.tutorial.recipes.R;
import com.sqisland.tutorial.recipes.data.local.RecipeStore;
import com.sqisland.tutorial.recipes.data.local.SharedPreferencesFavourites;
import com.sqisland.tutorial.recipes.data.model.Recipe;

/**
 * Created by Jeffkungu on 19/02/2018.
 */

public class RecipeActivity extends AppCompatActivity {
    public static final String KEY_ID = "id";

    private TextView title, description;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        title = (TextView) findViewById(R.id.tvTitlle);
        description = (TextView) findViewById(R.id.tvDescription);

        RecipeStore store = new RecipeStore(this, "recipes");
        String id = getIntent().getStringExtra(KEY_ID);
        final Recipe recipe = store.getRecipe(id);

        if (id == null) {
            title.setVisibility(View.GONE);
            description.setText(R.string.recipe_not_found);
            return;
        }

        final SharedPreferencesFavourites favourites = new SharedPreferencesFavourites(this);
        boolean favourite = favourites.get(recipe.id);

        title.setText(recipe.title);
        title.setSelected(favourite);
        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean result = favourites.toggle(recipe.id);
                title.setSelected(result);
                if (result) {
                    Toast.makeText(getApplicationContext(), "You have selected " + recipe.title + " as favourite", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "You have un-selected " + recipe.title, Toast.LENGTH_LONG).show();
                }
            }
        });
        description.setText(recipe.description);
    }
}
