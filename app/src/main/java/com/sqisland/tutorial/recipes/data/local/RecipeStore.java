package com.sqisland.tutorial.recipes.data.local;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jeffkungu on 16/02/2018.
 *
 * RecipeStore contains a list of Recipe objects
 */

public class RecipeStore {
    public RecipeStore(Context context, String directory) {
    }

    private static List<InputStream> getAssetStream(AssetManager manager, String directory) {
        String[] filenames = getFilenames(manager, directory);
        List<InputStream> streams = new ArrayList<>();

        for (String filename : filenames) {
            File file = new File(directory, filename);
            try {
                InputStream stream = manager.open(file.getPath());
                if (stream != null) {
                    streams.add(stream);
                }
            } catch (IOException e) {
            }
        }
    }

    private static String[] getFilenames(AssetManager manager, String directory) {
        if (directory == null) {
            return new String[0];
        }
        try {
            return manager.list(directory);
        } catch (IOException e) {
            return new String[0];
        }
    }
}
