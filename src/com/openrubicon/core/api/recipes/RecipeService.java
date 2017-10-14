package com.openrubicon.core.api.recipes;

import com.openrubicon.core.api.recipes.interfaces.Recipe;
import com.openrubicon.core.api.services.interfaces.Service;

import java.util.ArrayList;

public class RecipeService implements Service {

    private ArrayList<Recipe> recipes = new ArrayList<>();

    public RecipeService(ArrayList<Recipe> recipes) {
        this.recipes = recipes;
    }

    public ArrayList<Recipe> getRecipes() {
        return recipes;
    }

}
