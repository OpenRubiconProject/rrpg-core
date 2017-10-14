package com.openrubicon.core.api.recipes.events;

import com.openrubicon.core.api.inventory.blocks.AnvilInventory;
import com.openrubicon.core.api.recipes.handlers.AnvilRecipeHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.Inventory;

public class RecipeEventListener implements Listener {

    @EventHandler
    public void onPrepareAnvil(PrepareAnvilEvent e)
    {
        AnvilInventory anvilInventory = new AnvilInventory(e.getInventory());
        AnvilRecipeHandler anvil = new AnvilRecipeHandler(anvilInventory);
        anvil.findRecipe();
        e.setResult(anvil.getResult());
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e)
    {
        Inventory inventory = e.getClickedInventory();

        if(inventory == null)
            return;

        if(inventory.getType() == InventoryType.ANVIL)
        {
            if(e.getRawSlot() == 2 && (e.getWhoClicked() instanceof Player))
            {
                AnvilInventory anvilInventory = new AnvilInventory((org.bukkit.inventory.AnvilInventory)inventory);
                AnvilRecipeHandler anvil = new AnvilRecipeHandler(anvilInventory);
                anvil.findRecipe();
                anvil.takeResult((Player)e.getWhoClicked());
            }
        }

    }

}
