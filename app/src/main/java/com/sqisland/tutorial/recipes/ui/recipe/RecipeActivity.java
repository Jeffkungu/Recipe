package com.sqisland.tutorial.recipes.ui.recipe;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.sqisland.tutorial.recipes.R;
import com.sqisland.tutorial.recipes.data.local.Favourites;
import com.sqisland.tutorial.recipes.data.local.RecipeStore;
import com.sqisland.tutorial.recipes.injection.RecipeApplication;

/**
 * Created by Jeffkungu on 19/02/2018.
 */

public class RecipeActivity extends AppCompatActivity implements RecipeContract.View {
    public static final String KEY_ID = "id";
    private TextView title, description;
    RecipeContract.Listener listener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        // Step 1: Set up the UI
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        title = (TextView) findViewById(R.id.tvTitlle);
        description = (TextView) findViewById(R.id.tvDescription);

        // Step 2: Load recipe from RecipeStore
        RecipeStore store = new RecipeStore(this, "recipes");
        String id = getIntent().getStringExtra(KEY_ID);
        RecipeApplication app = (RecipeApplication) getApplication();
        final Favourites favourites = app.getFavourites();
        final RecipePresenter presenter = new RecipePresenter(store, this, favourites); // Has replaced the logic for loading recipe from RecipeStore.
        presenter.loadRecipe(id);

        // Step 3: If recipe is null, show error. (Its handled in the RecipePresenter.loadRecipe method)

        // Step 4: If recipe not null, show recipe (Its handled in the RecipePresenter.loadRecipe method)

        // Step 5: When title is clicked, toggle favourites
        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.toggleFavourite();
            }
        });
    }

    @Override
    public void showRecipeNotFoundError() {
        title.setVisibility(View.GONE);
        description.setText(R.string.recipe_not_found);
    }

    @Override
    public void setTitle(String text) {
        title.setText(text);
    }

    @Override
    public void setDescription(String text) {
        description.setText(text);
    }

    @Override
    public void setFavourites(boolean favourite) {
        title.setSelected(favourite);
    }
}
