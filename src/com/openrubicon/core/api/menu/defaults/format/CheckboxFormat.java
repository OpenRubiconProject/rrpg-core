package com.openrubicon.core.api.menu.defaults.format;

import com.openrubicon.core.api.chat.Colors;
import com.openrubicon.core.api.menu.formats.Checkbox;
import com.openrubicon.core.api.menu.interfaces.markers.InventorySupport;
import com.openrubicon.core.api.menu.interfaces.markers.TextSupport;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class CheckboxFormat extends Checkbox implements TextSupport, InventorySupport {
    @Override
    public ChatColor getColor() {
        return Colors.getPrimaryColor();
    }

    @Override
    public String getFormat() {
        return " [%s] ";
    }

    @Override
    public String getYesChar() {
        return "✔";
    }

    @Override
    public String getNoChar() {
        return "✖";
    }

    @Override
    public ItemStack getYesItem() {
        return new ItemStack(Material.WOOL, 1, (short)5);
    }

    @Override
    public ItemStack getNoItem() {
        return new ItemStack(Material.WOOL, 1, (short)14);
    }
}
