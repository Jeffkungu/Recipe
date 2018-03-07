package com.sqisland.tutorial.recipes.data.local;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Jeffkungu on 20/02/2018.
 */

public class SharedPreferencesFavourites implements Favourites {
    private final SharedPreferences preferences;

    public SharedPreferencesFavourites(Context context) {
        preferences = context.getSharedPreferences("favourites.xml", Context.MODE_PRIVATE);
    }

    public boolean get(String id) {
        return  preferences.getBoolean(id, false);
    }

    private void put(String id, boolean favourite) {
        SharedPreferences.Editor editor = preferences.edit();
        if (favourite) {
            editor.putBoolean(id, true);
        } else {
            editor.remove(id);
        }
        editor.apply();
    }

    public boolean toggle(String id) {
        boolean favourite = get(id);
        put(id, !favourite);
        return !favourite;
    }
}
