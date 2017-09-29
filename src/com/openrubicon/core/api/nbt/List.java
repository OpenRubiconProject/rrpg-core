package com.openrubicon.core.api.nbt;

import de.tr7zw.itemnbtapi.NBTCompound;
import de.tr7zw.itemnbtapi.NBTType;

public class List extends de.tr7zw.itemnbtapi.NBTList {
    public List(NBTCompound owner, String name, NBTType type, Object list) {
        super(owner, name, type, list);
    }

    public ListCompound getListCompound(int index)
    {
        return (ListCompound)this.getCompound(index);
    }
}
