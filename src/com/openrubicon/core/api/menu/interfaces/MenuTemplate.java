package com.openrubicon.core.api.menu.interfaces;

import com.openrubicon.core.api.menu.components.Component;

import java.util.ArrayList;

public interface MenuTemplate {
    String getName();

    ArrayList<Component> getComponents();
}
