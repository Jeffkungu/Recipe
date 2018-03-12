package com.sqisland.tutorial.recipes.ui.recipe;

/**
 * Created by Jeffkungu on 12/03/2018.
 */

public interface RecipeContract {

    interface View {
        void showRecipeNotFoundError();
        void setTitle(String title);
        void setDescription(String description);
        void setFavourites(boolean favourite);
    }

    interface Listener {
        void toggleFavourite();
    }
}
