package com.openrubicon.core.api.inventory;

import com.openrubicon.core.enums.InventorySlotType;
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
        if(slot == InventorySlotType.MAINHAND)
            this.entity.getEquipment().setItemInMainHand(item);

        if(slot == InventorySlotType.OFFHAND)
            this.entity.getEquipment().setItemInOffHand(item);

        if(slot == InventorySlotType.HELMET)
            this.entity.getEquipment().setHelmet(item);

        if(slot == InventorySlotType.CHESTPLATE)
            this.entity.getEquipment().setChestplate(item);

        if(slot == InventorySlotType.LEGGINGS)
            this.entity.getEquipment().setLeggings(item);

        if(slot == InventorySlotType.BOOTS)
            this.entity.getEquipment().setBoots(item);
    }

    public InventorySlotType getItemSlotType(ItemStack item)
    {
        InventorySlotType slot = InventorySlotType.MAINHAND;

        if(MaterialGroups.HELMETS.contains(item.getType()))
            slot = InventorySlotType.HELMET;

        if(MaterialGroups.CHESTPLATES.contains(item.getType()))
            slot = InventorySlotType.CHESTPLATE;

        if(MaterialGroups.LEGGINGS.contains(item.getType()))
            slot = InventorySlotType.LEGGINGS;

        if(MaterialGroups.BOOTS.contains(item.getType()))
            slot = InventorySlotType.BOOTS;

        if(slot == InventorySlotType.MAINHAND)
        {
            if(this.entity.getEquipment().getItemInOffHand() == item)
            {
                //Bukkit.broadcastMessage("WE SWITCHED TO OFFHAND");
                slot = InventorySlotType.OFFHAND;
            }
        }

        return slot;
    }

    public ItemStack getItemInSlot(InventorySlotType slot)
    {
        ItemStack item = null;

        if(slot == InventorySlotType.MAINHAND)
            item = this.entity.getEquipment().getItemInMainHand();

        if(slot == InventorySlotType.OFFHAND)
            item = this.entity.getEquipment().getItemInOffHand();

        if(slot == InventorySlotType.HELMET)
            item = this.entity.getEquipment().getHelmet();

        if(slot == InventorySlotType.CHESTPLATE)
            item = this.entity.getEquipment().getChestplate();

        if(slot == InventorySlotType.LEGGINGS)
            item = this.entity.getEquipment().getLeggings();

        if(slot == InventorySlotType.BOOTS)
            item = this.entity.getEquipment().getBoots();

        return item;
    }

}
