package com.openrubicon.core.api.recipes.interfaces;

import com.openrubicon.core.api.inventory.Inventory;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface Recipe {
    void setInventory(Inventory inventory);

    boolean isRecipe();

    ItemStack getResult();

    ItemStack takeResult(Player player);
}
