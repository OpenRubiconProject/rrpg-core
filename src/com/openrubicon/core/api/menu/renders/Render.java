package com.openrubicon.core.api.menu.renders;

import com.openrubicon.core.api.menu.chat.Line;
import com.openrubicon.core.api.menu.components.Component;
import com.openrubicon.core.api.menu.interfaces.Format;
import org.bukkit.inventory.ItemStack;

public class Render {

    com.openrubicon.core.api.menu.interfaces.Render getDefault();
    Format getFormat();
    Component getComponent();
    void setFormat(Format format);
    void setComponent(Component component);
    default ItemStack renderInventory() { return null; }
    default Line renderText() { return null; }
}
