package com.openrubicon.core.api.menu.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;

public class InventoryListener implements Listener {

    private static HashMap<Inventory, InventoryEventHandler> menus = new HashMap<>();

    public static void add(Inventory inventory, InventoryEventHandler inventoryEventHandler)
    {
        menus.put(inventory, inventoryEventHandler);
    }

    @org.bukkit.event.EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onInventoryClick(InventoryClickEvent event) {

        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getClickedInventory();
        ClickType type = event.getClick();

        //Inventory playerInventory = player.getOpenInventory().getTopInventory();

        if(menus.containsKey(inventory))
        {
            if(menus.get(inventory).getSlotEventListeners().get(event.getSlot()) != null)
            {
                menus.get(inventory).getSlotEventListeners().get(event.getSlot()).trigger(player);
            }

            event.setCancelled(true);
            event.setResult(Event.Result.DENY);
        }
    }

    @org.bukkit.event.EventHandler
    public void onInventoryClose(InventoryCloseEvent e)
    {
        if(menus.containsKey(e.getInventory()))
            menus.remove(e.getInventory());
    }

}
