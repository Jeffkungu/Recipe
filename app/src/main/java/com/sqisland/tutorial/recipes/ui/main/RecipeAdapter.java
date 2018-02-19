package com.sqisland.tutorial.recipes.ui.main;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sqisland.tutorial.recipes.R;
import com.sqisland.tutorial.recipes.data.local.RecipeStore;
import com.sqisland.tutorial.recipes.data.model.Recipe;
import com.sqisland.tutorial.recipes.ui.recipe.RecipeActivity;

/**
 * Created by Jeffkungu on 19/02/2018.
 */

public class RecipeAdapter extends RecyclerView.Adapter<RecipeViewHolder> {
    private final RecipeStore store;

    public RecipeAdapter(RecipeStore store) {
        this.store = store;
    }
    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.list_item, parent, false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecipeViewHolder holder, int position) {
        final Recipe recipe = store.recipes.get(position);
        holder.textView.setText(recipe.title);
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = holder.textView.getContext();
                Intent intent = new Intent(context, RecipeActivity.class);
                intent.putExtra(RecipeActivity.KEY_ID, recipe.id);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        Log.d("No of recipes: ", String.valueOf(store.recipes.size()));
        return store.recipes.size();
    }
}
