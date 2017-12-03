package com.openrubicon.core.api.menu.defaults.render;

import com.openrubicon.core.api.menu.chat.Line;
import com.openrubicon.core.api.menu.events.types.CommandEvent;
import com.openrubicon.core.api.menu.interfaces.markers.InventorySupport;
import com.openrubicon.core.api.menu.interfaces.markers.TextSupport;
import com.openrubicon.core.api.menu.renders.Checkbox;
import com.openrubicon.core.helpers.Helpers;
import net.md_5.bungee.api.chat.ClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CheckboxRender extends Checkbox implements TextSupport, InventorySupport {

    @Override
    public ItemStack renderInventory() {
        com.openrubicon.core.api.menu.formats.Checkbox format = this.getFormat();
        com.openrubicon.core.api.menu.components.Checkbox component = this.getComponent();

        ItemStack item = format.getNoItem();

        if(this.getComponent().isChecked())
            item = format.getYesItem();

        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(Helpers.colorize(this.getFormat().getColor() + component.getLabel(), '§'));
        item.setItemMeta(itemMeta);

        return item;

    }

    private ClickEvent getClickEvent() {
        return new ClickEvent(ClickEvent.Action.RUN_COMMAND, ((CommandEvent)this.getComponent().getEvent()).getCommandString());
    }

    @Override
    public Line renderText() {
        com.openrubicon.core.api.menu.formats.Checkbox format = this.getFormat();
        com.openrubicon.core.api.menu.components.Checkbox component = this.getComponent();

        String output = format.getFormat();
        output = String.format(output, component.isChecked() ? format.getYesChar() : format.getNoChar());
        //return new Line().add(this.label + ":").useHeadingColor().event(this.getClickEvent()).add(this.render()).usePrimaryColor();

        Line line = new Line().add(this.getComponent().getLabel() + ":").useHeadingColor().add(output);
        if(this.getComponent().hasEvent())
        {
            line.event(this.getClickEvent());
        }
        return line.useColor(format.getColor());
    }
}
