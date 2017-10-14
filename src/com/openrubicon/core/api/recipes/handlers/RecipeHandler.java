package com.openrubicon.core.api.recipes.handlers;

import com.openrubicon.core.api.costs.Cost;
import com.openrubicon.core.api.interfaces.Costable;
import com.openrubicon.core.api.inventory.Inventory;
import com.openrubicon.core.api.recipes.interfaces.Recipe;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

abstract public class RecipeHandler {

    private Inventory inventory;

    private Recipe recipe;

    public RecipeHandler(Inventory inventory) {
        this.inventory = inventory;
        this.findRecipe();
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    protected void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public boolean isCustomRecipe()
    {
        if(recipe == null)
            return false;

        return true;
    }

    public ItemStack getResult()
    {
        if(this.isCustomRecipe())
        {
            if(this.getRecipe() instanceof Costable)
            {
                for(HumanEntity h : inventory.getViewers())
                {
                    h.getOpenInventory().setProperty(InventoryView.Property.REPAIR_COST, (int)((Costable) this.getRecipe()).getCost().getCost());
                }
            }

            return this.recipe.getResult();
        }

        return null;
    }

    abstract public void findRecipe();

    public void takeResult(Player player)
    {
        if(this.getRecipe() instanceof Costable)
        {
            Cost cost = ((Costable) this.getRecipe()).getCost();

            if(cost != null && cost.has(player))
            {
                cost.withdraw(player);
                this.getRecipe().takeResult(player);
            }
        } else {
            this.getRecipe().takeResult(player);
        }

    }

}
