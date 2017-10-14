package com.openrubicon.core.api.inventory.blocks;

import com.openrubicon.core.api.inventory.Inventory;
import com.openrubicon.core.api.inventory.blocks.enums.AnvilSlotType;
import com.openrubicon.core.api.inventory.interfaces.InventorySlotType;

import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class AnvilInventory extends Inventory {

    org.bukkit.inventory.AnvilInventory anvilInventory;

    public AnvilInventory(org.bukkit.inventory.AnvilInventory anvilInventory) {
        this.anvilInventory = anvilInventory;
    }

    @Override
    public void setSlotItem(InventorySlotType slot, ItemStack item) {
        if(slot == AnvilSlotType.LEFT_INGREDIENT)
            anvilInventory.setItem(0, item);

        if(slot == AnvilSlotType.RIGHT_INGREDIENT)
            anvilInventory.setItem(1, item);

        if(slot == AnvilSlotType.RESULT)
            anvilInventory.setItem(2, item);
    }

    @Override
    public InventorySlotType getItemSlotType(ItemStack item) {
        if(anvilInventory.getItem(0) == item)
            return AnvilSlotType.LEFT_INGREDIENT;

        if(anvilInventory.getItem(1) == item)
            return AnvilSlotType.RIGHT_INGREDIENT;

        if(anvilInventory.getItem(2) == item)
            return AnvilSlotType.RESULT;

        return null;
    }

    @Override
    public ItemStack getItemInSlot(InventorySlotType slot) {
        if(slot == AnvilSlotType.LEFT_INGREDIENT)
            return anvilInventory.getItem(0);

        if(slot == AnvilSlotType.RIGHT_INGREDIENT)
            return anvilInventory.getItem(1);

        if(slot == AnvilSlotType.RESULT)
            return anvilInventory.getItem(2);

        return null;
    }

    @Override
    public void clear() {
        anvilInventory.clear();
    }

    @Override
    public List<HumanEntity> getViewers() {
        return anvilInventory.getViewers();
    }

    public org.bukkit.inventory.AnvilInventory getAnvilInventory()
    {
        return this.anvilInventory;
    }
}
