package com.openrubicon.core.api.menu.formats;

import com.openrubicon.core.api.menu.defaults.format.CheckboxFormat;
import com.openrubicon.core.api.menu.interfaces.Format;
import org.bukkit.inventory.ItemStack;

abstract public class Checkbox implements Format {

    abstract public String getFormat();
    abstract public String getYesChar();
    abstract public String getNoChar();
    abstract public ItemStack getYesItem();
    abstract public ItemStack getNoItem();

    @Override
    public Format getDefault() {
        return new CheckboxFormat();
    }
}
