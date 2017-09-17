package com.openrubicon.core.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.HashMap;

public class FiveTickEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    public FiveTickEvent() {

    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

}
