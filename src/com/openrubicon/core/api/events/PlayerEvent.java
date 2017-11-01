package com.openrubicon.core.api.events;

import org.bukkit.entity.Player;

public class PlayerEvent extends LivingEntityEvent {

    public PlayerEvent(Player player) {
        super(player);
    }

    public Player getPlayer()
    {
        return (Player)this.getLivingEntity();
    }
}
