package com.openrubicon.core.api.combat;

import org.bukkit.entity.Player;

public class PlayerInCombat {
    private Player player;
    private PlayerInCombatCooldown cooldown;

    public PlayerInCombat(Player player, PlayerInCombatCooldown cooldown) {
        this.player = player;
        this.cooldown = cooldown;
    }

    public Player getPlayer() {
        return player;
    }

    public PlayerInCombatCooldown getCooldown() {
        return cooldown;
    }

    public boolean isInCombat()
    {
        return this.cooldown.isOnCooldown();
    }
}
