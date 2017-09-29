package com.openrubicon.core.api.combat.events;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class PlayerCombatsEntityEvent extends PlayerCombatEvent {

    private LivingEntity entity;

    public PlayerCombatsEntityEvent(Player player, LivingEntity entity) {
        super(player);
        this.entity = entity;
    }

    public LivingEntity getEntity() {
        return entity;
    }
}
