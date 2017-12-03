package com.openrubicon.core.api.menu;

import com.openrubicon.core.api.menu.enums.MenuType;
import com.openrubicon.core.api.menu.interfaces.MenuFormat;
import com.openrubicon.core.api.menu.interfaces.MenuRender;
import com.openrubicon.core.api.menu.interfaces.MenuTemplate;
import com.openrubicon.core.api.menu.type.Inventory;
import com.openrubicon.core.api.menu.type.Text;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;

public class MenuBuilder {

    private ArrayList<Player> viewers = new ArrayList<>();
    private MenuTemplate menuTemplate;
    private MenuType menuType;
    private MenuFormat menuFormat = new com.openrubicon.core.api.menu.defaults.MenuFormat();
    private MenuRender menuRender = new com.openrubicon.core.api.menu.defaults.MenuRender();

    public MenuBuilder(MenuTemplate menuTemplate, MenuType menuType) {
        this.menuTemplate = menuTemplate;
        this.menuType = menuType;
    }

    public MenuBuilder withTemplate(MenuTemplate menuTemplate)
    {
        this.menuTemplate = menuTemplate;
        return this;
    }

    public MenuBuilder withFormat(MenuFormat menuFormat)
    {
        this.menuFormat = menuFormat;
        return this;
    }

    public MenuBuilder withRender(MenuRender menuRender)
    {
        this.menuRender = menuRender;
        return this;
    }

    public MenuBuilder useType(MenuType menuType)
    {
        this.menuType = menuType;
        return this;
    }

    public MenuBuilder withViewers(ArrayList<Player> player)
    {
        this.viewers.addAll(player);
        return this;
    }

    public MenuBuilder withViewers(Player... player)
    {
        this.viewers.addAll(Arrays.asList(player));
        return this;
    }

    public MenuBuilder withViewer(Player player)
    {
        this.viewers.add(player);
        return this;
    }

    public void draw()
    {
        if(menuType == MenuType.INVENTORY)
        {
            Inventory inventory = new Inventory(this.menuTemplate, this.menuRender, this.menuFormat);
            inventory.prepare();
            inventory.draw();
            inventory.display(this.viewers);
        }

        if(menuType == MenuType.TEXT)
        {
            Text text = new Text(this.menuTemplate, this.menuRender, this.menuFormat);
            text.prepare();
            text.draw();
            text.display(this.viewers);
        }
    }

}
