package com.sqisland.tutorial.recipes.ui.recipe;

import com.sqisland.tutorial.recipes.data.local.Favourites;
import com.sqisland.tutorial.recipes.data.local.RecipeStore;
import com.sqisland.tutorial.recipes.data.model.Recipe;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.io.InputStream;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Created by Jeffkungu on 13/03/2018.
 */
public class RecipePresenterTest {

    private  RecipeStore store;
    private  RecipeContract.View view;
    private  Favourites favourites;
    private RecipePresenter presenter;
    private Recipe recipe;

    @Before
    public void setUp() throws Exception {
        store = Mockito.mock(RecipeStore.class);
        view = Mockito.mock(RecipeContract.View.class);
        favourites = Mockito.mock(Favourites.class);
        presenter = new RecipePresenter(store, view, favourites);
    }

    @Test
    public void loadNullRecipe() {
        Mockito.when(store.getRecipe(Mockito.anyString())).thenReturn(null);
        presenter.loadRecipe("test_id");
        Mockito.verify(view, Mockito.times(1)).showRecipeNotFoundError();
    }

    @Test
    public void loadWaterRecipe() {
        InputStream stream = RecipePresenterTest.class.getResourceAsStream("/recipes/water.txt");
        recipe = Recipe.readFromSrtream(stream);
        Mockito.when(store.getRecipe(Mockito.anyString())).thenReturn(recipe);

        presenter.loadRecipe("water");
        Mockito.verify(view, Mockito.times(1)).setTitle("Water");
        Mockito.verify(view, Mockito.times(1)).
                setDescription("Put glass under tap. Open tap. Close tap. Drink.");

        ArgumentCaptor<Boolean> captor = ArgumentCaptor.forClass(Boolean.class);
        Mockito.verify(view, Mockito.times(1)).setFavourites(captor.capture());
    }

    @Test(expected = IllegalStateException.class)
    public void toggleFavouriteWithNullRecipe() {
        presenter.toggleFavourite();
    }

    @Test
    public void toggleFavouriteWithRecipeNotNull() {
        InputStream stream = RecipePresenterTest.class.getResourceAsStream("/recipes/water.txt");
        recipe = Recipe.readFromSrtream(stream);
        Mockito.when(store.getRecipe(Mockito.anyString())).thenReturn(recipe);
        Mockito.when(favourites.toggle(Mockito.anyString())).thenReturn(true);

        presenter.loadRecipe("water");
        presenter.toggleFavourite();

        ArgumentCaptor<Boolean> captor = ArgumentCaptor.forClass(Boolean.class);
        Mockito.verify(view, Mockito.times(2)).setFavourites(captor.capture());
        assertFalse(captor.getAllValues().get(0));
        assertTrue(captor.getAllValues().get(1));
    }
}