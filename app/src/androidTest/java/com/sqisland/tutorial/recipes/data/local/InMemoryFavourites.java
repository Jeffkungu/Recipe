package com.sqisland.tutorial.recipes.data.local;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jeffkungu on 07/03/2018.
 */

public class InMemoryFavourites implements Favourites {
    private final Map<String, Boolean> map = new HashMap<>();

    @Override
    public boolean get(String id) {
        Boolean value = map.get(id);
        return value == null ? false : value;
    }

    @Override
    public boolean toggle(String id) {
        boolean value = get(id);
        map.put(id, !value);
        return !value;
    }

    public void put(String id, boolean value) {
        map.put(id, value);
    }
}
