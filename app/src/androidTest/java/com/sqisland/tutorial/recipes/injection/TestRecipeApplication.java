package com.sqisland.tutorial.recipes.injection;

import com.sqisland.tutorial.recipes.data.local.Favourites;
import com.sqisland.tutorial.recipes.data.local.InMemoryFavourites;

/**
 * Created by Jeffkungu on 07/03/2018.
 */

public class TestRecipeApplication extends RecipeApplication {
    private final Favourites favourites = new InMemoryFavourites();

    @Override
    public Favourites getFavourites() {
        return favourites;
    }
}
