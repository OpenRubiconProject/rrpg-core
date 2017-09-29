package com.openrubicon.core.api.cooldowns.events;

import com.openrubicon.core.api.cooldowns.Cooldown;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class CooldownCompletedEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private Cooldown cooldown;

    public CooldownCompletedEvent(Cooldown cooldown) {
        this.cooldown = cooldown;
    }

    public Cooldown getCooldown() {
        return cooldown;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}
