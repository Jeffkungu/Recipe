package com.sqisland.tutorial.recipes.data.local;

/**
 * Created by Jeffkungu on 07/03/2018.
 */

public interface Favourites {
    boolean get(String id);
    boolean toggle(String id);
}
