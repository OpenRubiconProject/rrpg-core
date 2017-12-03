package com.openrubicon.core.api.menu.defaults.format;

import com.openrubicon.core.api.menu.formats.Slider;
import com.openrubicon.core.api.menu.interfaces.markers.InventorySupport;
import com.openrubicon.core.api.menu.interfaces.markers.TextSupport;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.inventory.ItemStack;

public class SliderFormat extends Slider implements TextSupport, InventorySupport {
    @Override
    public ChatColor getColor() {
        return null;
    }

    @Override
    public String getFormat() {
        return null;
    }

    @Override
    public String getIncreaseChar() {
        return null;
    }

    @Override
    public String getDecreaseChar() {
        return null;
    }

    @Override
    public ItemStack getIncreaseItem() {
        return null;
    }

    @Override
    public ItemStack getDecreaseItem() {
        return null;
    }
}
