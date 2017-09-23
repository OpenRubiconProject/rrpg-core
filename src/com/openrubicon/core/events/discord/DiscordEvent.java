package com.openrubicon.core.events.discord;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

abstract public class DiscordEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}
