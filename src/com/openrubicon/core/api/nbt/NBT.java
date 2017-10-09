package com.openrubicon.core.api.nbt;

import de.tr7zw.itemnbtapi.NBTItem;
import de.tr7zw.itemnbtapi.NBTType;
import org.bukkit.inventory.ItemStack;

public class NBT extends NBTItem {

    public NBT(ItemStack item) {
        super(item);
    }

    public String getString(String key)
    {
        return super.getString(key);
    }

    public void setString(String key, String value)
    {
        super.setString(key, value);
    }

    public Double getDouble(String key)
    {
        return super.getDouble(key);
    }

    public void setDouble(String key, double value)
    {
        super.setDouble(key, value);
    }

    public Boolean getBoolean(String key)
    {
        return super.getBoolean(key);
    }

    public void setBoolean(String key, boolean value)
    {
        super.setBoolean(key, value);
    }

    public Integer getInteger(String key)
    {
        return super.getInteger(key);
    }

    public void setInteger(String key, int value)
    {
        super.setInteger(key, value);
    }

    public Long getLong(String key)
    {
        return super.getLong(key);
    }

    public void setLong(String key, long value)
    {
        super.setLong(key, value);
    }

    public <T> T getObject(String key, Class<T> type)
    {
        return super.getObject(key, type);
    }

    public void setObject(String key, Object value)
    {
        super.setObject(key, value);
    }

    public ItemStack getItem()
    {
        return super.getItem();
    }

    public Compound addCompound(String key)
    {
        return (Compound)super.addCompound(key);
    }

    public Compound getCompound(String key)
    {
        return (Compound)super.getCompound(key);
    }

    public List getList(String listName)
    {
        return (List)getList(listName, NBTType.NBTTagCompound);
    }

}
