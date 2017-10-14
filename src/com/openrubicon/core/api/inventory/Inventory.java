package com.openrubicon.core.api.inventory;

import com.openrubicon.core.api.inventory.interfaces.InventorySlotType;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

abstract public class Inventory {

    public Inventory()
    {

    }

    abstract public void setSlotItem(InventorySlotType slot, ItemStack item);

    abstract public InventorySlotType getItemSlotType(ItemStack item);

    abstract public ItemStack getItemInSlot(InventorySlotType slot);

    public void clear() {}
    public List<HumanEntity> getViewers() { return new ArrayList<>(); }

}
