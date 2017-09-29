package com.openrubicon.core.api.inventory;

import com.openrubicon.core.enums.InventorySlotType;
import org.bukkit.inventory.ItemStack;

abstract public class Inventory {

    public Inventory()
    {

    }

    abstract public void setSlotItem(InventorySlotType slot, ItemStack item);

    abstract public InventorySlotType getItemSlotType(ItemStack item);

    abstract public ItemStack getItemInSlot(InventorySlotType slot);

}
