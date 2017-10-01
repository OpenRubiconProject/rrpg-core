package com.openrubicon.core.events;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerStandingStillEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private Player player;
    private Location currentLocation;

    public PlayerStandingStillEvent(Player player, Location currentLocation)
    {
        this.player = player;
        this.currentLocation = currentLocation;
    }

    public Player getPlayer() {
        return player;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public HandlerList getHandlers(){
        return handlers;
    }

    public static HandlerList getHandlerList(){
        return handlers;
    }
}
