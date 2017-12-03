package com.openrubicon.core.api.menu.defaults.format;

import com.openrubicon.core.api.menu.formats.Text;
import com.openrubicon.core.api.menu.interfaces.markers.InventorySupport;
import com.openrubicon.core.api.menu.interfaces.markers.TextSupport;
import net.md_5.bungee.api.ChatColor;

public class TextFormat extends Text implements TextSupport, InventorySupport {
    @Override
    public ChatColor getColor() {
        return null;
    }

    @Override
    public String getFormat() {
        return null;
    }
}
