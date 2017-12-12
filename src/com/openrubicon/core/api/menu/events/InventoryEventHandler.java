package com.openrubicon.core.api.menu.events;

import com.openrubicon.core.api.menu.components.Component;

import java.util.HashMap;

public class InventoryEventHandler {

    private HashMap<Integer, Component> slotEventListeners = new HashMap<>();

    public HashMap<Integer, Component> getSlotEventListeners() {
        return slotEventListeners;
    }

}
