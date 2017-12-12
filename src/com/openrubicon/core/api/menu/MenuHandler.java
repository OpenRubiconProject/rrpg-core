package com.openrubicon.core.api.menu;

import com.openrubicon.core.api.menu.interfaces.MenuTemplate;
import com.openrubicon.core.api.menu.type.Menu;

import java.util.HashMap;

public class MenuHandler {

    private static HashMap<String, Menu> menus = new HashMap<>();

    public static HashMap<String, Menu> getMenus() {
        return menus;
    }

    public static MenuBuilder make(MenuTemplate menuTemplate)
    {
        return new MenuBuilder(menuTemplate);
    }
}
