package com.openrubicon.core.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerLandOnGroundEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private Player player;
    private double topSpeed = 0;

    public PlayerLandOnGroundEvent(Player player) {
        this.player = player;
    }

    public PlayerLandOnGroundEvent(Player player, double topSpeed) {
        this.player = player;
        this.topSpeed = topSpeed;
    }

    public Player getPlayer(){
        return player;
    }

    public double getTopSpeed(){
        return this.topSpeed;
    }

    public HandlerList getHandlers(){
        return handlers;
    }

    public static HandlerList getHandlerList(){
        return handlers;
    }

}
