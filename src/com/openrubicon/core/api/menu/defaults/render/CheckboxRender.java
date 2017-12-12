package com.openrubicon.core.api.menu.defaults.render;

import com.openrubicon.core.api.menu.chat.Line;
import com.openrubicon.core.api.menu.enums.EventType;
import com.openrubicon.core.api.menu.events.types.CommandMenuEvent;
import com.openrubicon.core.api.menu.interfaces.markers.InventorySupport;
import com.openrubicon.core.api.menu.interfaces.markers.TextSupport;
import com.openrubicon.core.api.menu.renders.Checkbox;
import com.openrubicon.core.helpers.Helpers;
import net.md_5.bungee.api.chat.ClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.UUID;

public class CheckboxRender extends Checkbox implements TextSupport, InventorySupport {

    @Override
    public ItemStack renderInventory(UUID menuUuid) {
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

    private ClickEvent getClickEvent(UUID menuUuid) {

        String cmd = "/rrpg menu action " + menuUuid.toString() + " " + EventType.COMMAND.toString() + " ";

        cmd += "\"" + ((CommandMenuEvent)this.getComponent().getMenuEvent()).getCommandString() + "\"";

        return new ClickEvent(ClickEvent.Action.RUN_COMMAND, cmd);
    }

    @Override
    public Line renderText(UUID menuUuid) {
        com.openrubicon.core.api.menu.formats.Checkbox format = this.getFormat();
        com.openrubicon.core.api.menu.components.Checkbox component = this.getComponent();

        String output = format.getFormat();
        output = String.format(output, component.isChecked() ? format.getYesChar() : format.getNoChar());
        //return new Line().add(this.label + ":").useHeadingColor().event(this.getClickEvent()).add(this.render()).usePrimaryColor();

        Line line = new Line().add(this.getComponent().getLabel() + ":").useHeadingColor().add(output);
        if(this.getComponent().hasEvent())
        {
            line.event(this.getClickEvent(menuUuid));
        }
        return line.useColor(format.getColor());
    }
}
