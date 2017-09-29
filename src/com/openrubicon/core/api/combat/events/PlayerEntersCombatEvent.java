package com.openrubicon.core.api.combat.events;

import com.openrubicon.core.api.combat.PlayerInCombatCooldown;
import org.bukkit.entity.Player;

public class PlayerEntersCombatEvent extends PlayerCombatEvent {

    private PlayerInCombatCooldown cooldown;

    public PlayerEntersCombatEvent(Player player) {
        super(player);
        this.cooldown = new PlayerInCombatCooldown(player.getUniqueId().toString(), "playerincombat");
    }

    public PlayerInCombatCooldown getCooldown() {
        return this.cooldown;
    }
}
