package com.openrubicon.core.api.menu.type;

import com.openrubicon.core.api.menu.components.Checkbox;
import com.openrubicon.core.api.menu.components.Component;
import com.openrubicon.core.api.menu.components.Radio;
import com.openrubicon.core.api.menu.components.Slider;
import com.openrubicon.core.api.menu.events.InventoryEventHandler;
import com.openrubicon.core.api.menu.events.InventoryListener;
import com.openrubicon.core.api.menu.interfaces.MenuFormat;
import com.openrubicon.core.api.menu.interfaces.MenuRender;
import com.openrubicon.core.api.menu.interfaces.MenuTemplate;
import com.openrubicon.core.api.menu.interfaces.markers.InventorySupport;
import com.openrubicon.core.api.menu.renders.Render;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Inventory extends Menu implements InventorySupport {

    private org.bukkit.inventory.Inventory inventory;

    private InventoryEventHandler inventoryEventHandler = new InventoryEventHandler();

    private int size = 27;

    public Inventory(MenuTemplate template, MenuRender render, MenuFormat format) {
        super(template, render, format);
    }

    public org.bukkit.inventory.Inventory getInventory() {
        return inventory;
    }

    @Override
    public void prepare() {
        super.prepare();

        this.inventory = Bukkit.createInventory(null, size, this.getTemplate().getName());
    }

    @Override
    public void draw() {
        //InventoryHelper.changeTitle(this.getInventory(), this.getTemplate().getName());
        double columns = 9;
        double rows = (int)(size / columns);

        double count = this.getTemplate().getComponents().size();

        double perRow = (int)Math.ceil(count / rows);
        if(perRow < 1)
            perRow = 1;

        int usedRows = (int)Math.ceil(count / perRow);

        //Bukkit.broadcastMessage(count + ":" + columns + ":" + rows + ":" + perRow + ":" + usedRows);

        int index = 0;

        for(int i = 0; i < usedRows; i++)
        {
            perRow = (int)Math.ceil((count - index) / (rows - i));
            int spacing = (int)Math.floor(columns / perRow);
            int offset = (int)Math.floor(spacing / 2);

            for(int j = 0; j < perRow; j++)
            {
                if(index + 1 > this.getTemplate().getComponents().size())
                    break;

                if(this.getTemplate().getComponents().get(index) == null)
                {
                    index++;
                    continue;
                }

                Component component = this.getTemplate().getComponents().get(index);

                Render render = null;

                if(component instanceof Checkbox)
                    render = this.getRender().getCheckboxRender();

                if(component instanceof Radio)
                    render = this.getRender().getRadioRender();

                if(component instanceof Slider)
                    render = this.getRender().getSliderRender();

                if(component instanceof com.openrubicon.core.api.menu.components.Text)
                    render = this.getRender().getTextRender();

                if(render != null)
                {
                    render.setComponent(component);
                    int placementIndex = (int)((i * columns) + (j * spacing) + offset);
                    this.inventory.setItem(placementIndex, render.renderInventory());
                    this.inventoryEventHandler.getSlotEventListeners().put(placementIndex, component.getEvent());
                }

                index++;
            }

        }
    }

    @Override
    public void display(ArrayList<Player> viewers) {
        for(Player player : viewers)
        {
            player.closeInventory();
            player.openInventory(this.getInventory());
            InventoryListener.add(this.getInventory(), this.inventoryEventHandler);
        }
    }
}
