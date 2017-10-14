package com.openrubicon.core.api.inventory.entities;

import com.openrubicon.core.api.inventory.Inventory;
import com.openrubicon.core.api.inventory.entities.enums.EntityInventorySlotType;
import com.openrubicon.core.api.inventory.interfaces.InventorySlotType;
import com.openrubicon.core.helpers.MaterialGroups;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;

public class LivingEntityInventory extends Inventory {

    protected LivingEntity entity;

    public LivingEntityInventory(LivingEntity entity)
    {
        this.entity = entity;
    }

    public void setSlotItem(InventorySlotType slot, ItemStack item)
    {
        if(slot == EntityInventorySlotType.MAINHAND)
            this.entity.getEquipment().setItemInMainHand(item);

        if(slot == EntityInventorySlotType.OFFHAND)
            this.entity.getEquipment().setItemInOffHand(item);

        if(slot == EntityInventorySlotType.HELMET)
            this.entity.getEquipment().setHelmet(item);

        if(slot == EntityInventorySlotType.CHESTPLATE)
            this.entity.getEquipment().setChestplate(item);

        if(slot == EntityInventorySlotType.LEGGINGS)
            this.entity.getEquipment().setLeggings(item);

        if(slot == EntityInventorySlotType.BOOTS)
            this.entity.getEquipment().setBoots(item);
    }

    public EntityInventorySlotType getItemSlotType(ItemStack item)
    {
        EntityInventorySlotType slot = EntityInventorySlotType.MAINHAND;

        if(MaterialGroups.HELMETS.contains(item.getType()))
            slot = EntityInventorySlotType.HELMET;

        if(MaterialGroups.CHESTPLATES.contains(item.getType()))
            slot = EntityInventorySlotType.CHESTPLATE;

        if(MaterialGroups.LEGGINGS.contains(item.getType()))
            slot = EntityInventorySlotType.LEGGINGS;

        if(MaterialGroups.BOOTS.contains(item.getType()))
            slot = EntityInventorySlotType.BOOTS;

        if(slot == EntityInventorySlotType.MAINHAND)
        {
            if(this.entity.getEquipment().getItemInOffHand() == item)
            {
                //Bukkit.broadcastMessage("WE SWITCHED TO OFFHAND");
                slot = EntityInventorySlotType.OFFHAND;
            }
        }

        return slot;
    }

    public ItemStack getItemInSlot(InventorySlotType slot)
    {
        ItemStack item = null;

        if(slot == EntityInventorySlotType.MAINHAND)
            item = this.entity.getEquipment().getItemInMainHand();

        if(slot == EntityInventorySlotType.OFFHAND)
            item = this.entity.getEquipment().getItemInOffHand();

        if(slot == EntityInventorySlotType.HELMET)
            item = this.entity.getEquipment().getHelmet();

        if(slot == EntityInventorySlotType.CHESTPLATE)
            item = this.entity.getEquipment().getChestplate();

        if(slot == EntityInventorySlotType.LEGGINGS)
            item = this.entity.getEquipment().getLeggings();

        if(slot == EntityInventorySlotType.BOOTS)
            item = this.entity.getEquipment().getBoots();

        return item;
    }

}
