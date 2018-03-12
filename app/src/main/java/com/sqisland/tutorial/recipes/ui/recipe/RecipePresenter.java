package com.sqisland.tutorial.recipes.ui.recipe;

import com.sqisland.tutorial.recipes.data.local.Favourites;
import com.sqisland.tutorial.recipes.data.local.RecipeStore;
import com.sqisland.tutorial.recipes.data.model.Recipe;

/**
 * Created by Jeffkungu on 12/03/2018.
 */

public class RecipePresenter implements RecipeContract.Listener {
    private final RecipeStore store;
    private final RecipeContract.View view;
    private final Favourites favourites;
    private Recipe recipe;

    public RecipePresenter(RecipeStore store, RecipeContract.View view, Favourites favourites) {
        this.store = store;
        this.view = view;
        this.favourites = favourites;
    }

    public void loadRecipe(String id) {
        recipe = store.getRecipe(id);
        if (recipe == null) {
            view.showRecipeNotFoundError();
        } else {
            view.setTitle(recipe.title);
            view.setDescription(recipe.description);
            view.setFavourites(favourites.get(recipe.id));
        }
    }

    @Override
    public void toggleFavourite() {
        boolean result = favourites.toggle(recipe.id);
        view.setFavourites(result);
    }
}
