package com.openrubicon.core.api.events;

import org.bukkit.event.HandlerList;

abstract public class Event extends org.bukkit.event.Event {

    private static final HandlerList handlers = new HandlerList();

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList(){
        return handlers;
    }
}
