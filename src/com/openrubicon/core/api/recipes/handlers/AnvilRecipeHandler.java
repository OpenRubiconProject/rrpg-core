package com.openrubicon.core.api.recipes.handlers;

import com.openrubicon.core.RRPGCore;
import com.openrubicon.core.api.inventory.Inventory;
import com.openrubicon.core.api.recipes.RecipeService;
import com.openrubicon.core.api.recipes.interfaces.AnvilRecipe;
import com.openrubicon.core.api.recipes.interfaces.Recipe;


public class AnvilRecipeHandler extends RecipeHandler {

    public AnvilRecipeHandler(Inventory inventory) {
        super(inventory);
    }

    @Override
    public void findRecipe() {
        for(Recipe recipe : RRPGCore.services.getSerivce(RecipeService.class).getRecipes())
        {
            if(!(recipe instanceof AnvilRecipe))
                continue;

            AnvilRecipe anvilRecipe = (AnvilRecipe)recipe;

            anvilRecipe.setInventory(this.getInventory());
            if(anvilRecipe.isRecipe())
            {
                this.setRecipe(anvilRecipe);
                break;
            }
        }
    }

    /*@Override
    public boolean takeResult(Player player) {
        if(!this.isCustomRecipe())
            return false;

        if(!super.takeResult(player))
            return false;

        this.getRecipe().takeResult(player);

        return true;
    }*/
}
