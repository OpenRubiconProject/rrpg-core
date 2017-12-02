package com.openrubicon.core.api.menu.interfaces;

import com.openrubicon.core.api.menu.chat.Line;
import com.openrubicon.core.api.menu.components.Component;
import org.bukkit.inventory.ItemStack;

public interface Render {
    Render getDefault();
    Format getFormat();
    Component getComponent();
    void setFormat(Format format);
    void setComponent(Component component);
    default ItemStack renderInventory() { return null; }
    default Line renderText() { return null; }
}
