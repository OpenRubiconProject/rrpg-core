package com.openrubicon.core.api.menu.components;

import com.openrubicon.core.api.menu.events.interfaces.MenuEvent;

abstract public class Component<T> {

    private String label;

    private MenuEvent menuEvent = null;

    public MenuEvent getMenuEvent() {
        return menuEvent;
    }

    public T setEvent(MenuEvent menuEvent) {
        this.menuEvent = menuEvent;
        this.initEventParameters();
        return (T)(this);
    }

    public String getLabel() {
        return label;
    }

    public T setLabel(String label) {
        this.label = label;
        return (T)(this);
    }

    abstract public T initEventParameters();

    public boolean hasEvent()
    {
        return this.menuEvent != null;
    }

}
