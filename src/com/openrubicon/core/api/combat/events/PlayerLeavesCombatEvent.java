package com.openrubicon.core.api.combat.events;

import org.bukkit.entity.Player;

public class PlayerLeavesCombatEvent extends PlayerCombatEvent {
    public PlayerLeavesCombatEvent(Player player) {
        super(player);
    }
}
