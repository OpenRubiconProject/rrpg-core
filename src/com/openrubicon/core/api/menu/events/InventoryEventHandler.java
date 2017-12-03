package com.openrubicon.core.api.menu.events;

import com.openrubicon.core.api.menu.events.interfaces.EventType;

import java.util.HashMap;

public class InventoryEventHandler {

    private HashMap<Integer, EventType> slotEventListeners = new HashMap<>();

    public HashMap<Integer, EventType> getSlotEventListeners() {
        return slotEventListeners;
    }

}
