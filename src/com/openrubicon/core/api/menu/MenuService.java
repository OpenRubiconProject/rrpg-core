package com.openrubicon.core.api.menu;

import com.openrubicon.core.api.menu.interfaces.MenuTemplate;
import com.openrubicon.core.api.services.interfaces.Service;

import java.util.ArrayList;

public class MenuService implements Service {

    private ArrayList<MenuTemplate> menus = new ArrayList<>();

    public ArrayList<MenuTemplate> getMenus() {
        return menus;
    }

    @Override
    public ArrayList<String> getObservation() {
        ArrayList<String> observation = new ArrayList<>();
        for(MenuTemplate menuTemplate : this.getMenus())
        {
            observation.add(menuTemplate.getName());
        }
        return observation;
    }

}
