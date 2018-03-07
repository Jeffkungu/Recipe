package com.sqisland.tutorial.recipes.injection;

import android.app.Application;

import com.sqisland.tutorial.recipes.data.local.Favourites;
import com.sqisland.tutorial.recipes.data.local.SharedPreferencesFavourites;

/**
 * Created by Jeffkungu on 07/03/2018.
 */

public class RecipeApplication extends Application {
    private Favourites favourites = null;

    public Favourites getFavourites() {
       if (favourites == null) {
           favourites = new SharedPreferencesFavourites(this);
       }
       return favourites;
    }
}