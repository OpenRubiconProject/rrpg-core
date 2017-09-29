package com.openrubicon.core.api.combat.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

abstract public class PlayerCombatEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private Player player;

    public PlayerCombatEvent(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}
