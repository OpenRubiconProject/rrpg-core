package com.openrubicon.core.api.menu;

import com.openrubicon.core.api.menu.interfaces.MenuFormat;
import com.openrubicon.core.api.menu.interfaces.MenuRender;
import com.openrubicon.core.api.menu.interfaces.MenuTemplate;
import com.openrubicon.core.api.menu.interfaces.markers.Support;
import com.openrubicon.core.api.menu.type.Inventory;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class MenuBuilder {

    private ArrayList<Player> views = new ArrayList<>();
    private MenuTemplate menuTemplate;
    private MenuFormat menuFormat = new com.openrubicon.core.api.menu.defaults.MenuFormat();
    private MenuRender menuRender = new com.openrubicon.core.api.menu.defaults.MenuRender();
    private Support menuType = new Inventory();

    public MenuBuilder(MenuTemplate menuTemplate) {
        this.menuTemplate = menuTemplate;
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

    public MenuBuilder useType(Support menuType)
    {
        this.menuType = menuType;
        return this;
    }

    public void draw()
    {

    }

    public void redraw()
    {

    }

}
