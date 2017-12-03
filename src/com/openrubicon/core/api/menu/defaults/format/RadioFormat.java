package com.openrubicon.core.api.menu.defaults.format;

import com.openrubicon.core.api.menu.formats.Radio;
import com.openrubicon.core.api.menu.interfaces.markers.InventorySupport;
import com.openrubicon.core.api.menu.interfaces.markers.TextSupport;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.inventory.ItemStack;

public class RadioFormat extends Radio implements TextSupport, InventorySupport {
    @Override
    public ChatColor getColor() {
        return null;
    }

    @Override
    public String getFormat() {
        return null;
    }

    @Override
    public String getSelectedChar() {
        return null;
    }

    @Override
    public String getUnselectedChar() {
        return null;
    }

    @Override
    public ItemStack getSelectedItem() {
        return null;
    }

    @Override
    public ItemStack getUnselectedItem() {
        return null;
    }
}
