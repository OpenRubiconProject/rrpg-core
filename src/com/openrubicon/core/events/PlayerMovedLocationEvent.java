package com.openrubicon.core.events;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerMovedLocationEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private Player player;
    private Location previousLocation;
    private Location newLocation;

    public PlayerMovedLocationEvent(Player player, Location previousLocation, Location currentLocation)
    {
        this.player = player;
        this.previousLocation = previousLocation;
        this.newLocation = currentLocation;
    }

    public Player getPlayer() {
        return player;
    }

    public Location getPreviousLocation() {
        return previousLocation;
    }

    public Location getNewLocation() {
        return newLocation;
    }

    public HandlerList getHandlers(){
        return handlers;
    }

    public static HandlerList getHandlerList(){
        return handlers;
    }

}
