package com.openrubicon.core.api.menu.formats;

import com.openrubicon.core.api.menu.defaults.format.RadioFormat;
import com.openrubicon.core.api.menu.interfaces.Format;
import org.bukkit.inventory.ItemStack;

abstract public class Radio implements Format {

    abstract public String getFormat();
    abstract public String getSelectedChar();
    abstract public String getUnselectedChar();
    abstract public ItemStack getSelectedItem();
    abstract public ItemStack getUnselectedItem();

    @Override
    public Format getDefault() {
        return new RadioFormat();
    }
}
