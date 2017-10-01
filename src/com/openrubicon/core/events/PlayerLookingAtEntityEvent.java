package com.openrubicon.core.events;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerLookingAtEntityEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private Player player;
    private LivingEntity entity;

    public PlayerLookingAtEntityEvent(Player player, LivingEntity entity) {
        this.player = player;
        this.entity = entity;
    }

    public Player getPlayer(){
        return player;
    }

    public LivingEntity getLivingEntity(){
        return this.entity;
    }

    public HandlerList getHandlers(){
        return handlers;
    }

    public static HandlerList getHandlerList(){
        return handlers;
    }

}
