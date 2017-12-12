package com.openrubicon.core.api.menu.defaults.render;

import com.openrubicon.core.api.menu.chat.Line;
import com.openrubicon.core.api.menu.components.Component;
import com.openrubicon.core.api.menu.interfaces.Format;
import com.openrubicon.core.api.menu.interfaces.markers.InventorySupport;
import com.openrubicon.core.api.menu.interfaces.markers.TextSupport;
import com.openrubicon.core.api.menu.renders.Slider;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class SliderRender extends Slider implements TextSupport, InventorySupport {
    @Override
    public ItemStack renderInventory(UUID menuUuid) {
        return null;
    }

    @Override
    public Line renderText(UUID menuUuid) {
        return null;
    }
}
