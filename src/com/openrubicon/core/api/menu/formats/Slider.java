package com.openrubicon.core.api.menu.formats;

import com.openrubicon.core.api.menu.defaults.format.SliderFormat;
import com.openrubicon.core.api.menu.interfaces.Format;
import org.bukkit.inventory.ItemStack;

abstract public class Slider implements Format {

    abstract public String getFormat();
    abstract public String getIncreaseChar();
    abstract public String getDecreaseChar();
    abstract public ItemStack getIncreaseItem();
    abstract public ItemStack getDecreaseItem();

    @Override
    public Format getDefault() {
        return new SliderFormat();
    }
}
