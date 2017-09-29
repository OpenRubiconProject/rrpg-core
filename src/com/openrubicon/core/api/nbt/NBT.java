package com.openrubicon.core.api.nbt;

import de.tr7zw.itemnbtapi.NBTItem;
import de.tr7zw.itemnbtapi.NBTType;
import org.bukkit.inventory.ItemStack;

public class NBT extends NBTItem {

    public NBT(ItemStack item) {
        super(item);
    }

    public List getList(String listName)
    {
        return (List)getList(listName, NBTType.NBTTagCompound);
    }

}
