package com.openrubicon.core.api.combat.events;

import com.openrubicon.core.api.events.Event;
import org.bukkit.entity.Player;

abstract public class PlayerCombatEvent extends Event {

    private Player player;

    public PlayerCombatEvent(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

}
