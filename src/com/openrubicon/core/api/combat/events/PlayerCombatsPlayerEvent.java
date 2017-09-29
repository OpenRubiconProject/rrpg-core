package com.openrubicon.core.api.combat.events;

import org.bukkit.entity.Player;

public class PlayerCombatsPlayerEvent extends PlayerCombatEvent {

    private Player recipient;

    public PlayerCombatsPlayerEvent(Player player, Player recipient) {
        super(player);
        this.recipient = recipient;
    }

    public Player getRecipient() {
        return recipient;
    }
}
