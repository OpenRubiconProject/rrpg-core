package com.openrubicon.core.api.menu.renders;

import com.openrubicon.core.api.menu.chat.Line;
import com.openrubicon.core.api.menu.components.Component;
import com.openrubicon.core.api.menu.interfaces.Format;
import org.bukkit.inventory.ItemStack;

abstract public class Render {

    private Format format;
    private Component component;

    public Format getFormat() {
        return format;
    }

    public void setFormat(Format format) {
        this.format = format;
    }

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }

    abstract public Render getDefault();

    public ItemStack renderInventory() { return null; }
    public Line renderText() { return null; }

}
