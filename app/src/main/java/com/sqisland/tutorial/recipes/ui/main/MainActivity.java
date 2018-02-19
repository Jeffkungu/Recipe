package com.sqisland.tutorial.recipes.ui.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sqisland.tutorial.recipes.R;
import com.sqisland.tutorial.recipes.data.local.RecipeStore;

public class MainActivity extends AppCompatActivity {

  private RecipeStore store;
  private RecipeAdapter adapter;
  private RecyclerView recyclerView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    initRecyclerview();
  }

  public void initRecyclerview() {
    recyclerView = (RecyclerView) findViewById(R.id.rvRecipes);
    store = new RecipeStore(this, "recipes");
    adapter = new RecipeAdapter(store);
    recyclerView.setAdapter(adapter);
    recyclerView.setHasFixedSize(true);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
  }
}