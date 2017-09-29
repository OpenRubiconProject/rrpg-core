package com.openrubicon.core.api.combat;

import com.openrubicon.core.api.cooldowns.Cooldown;

public class PlayerInCombatCooldown extends Cooldown {

    public PlayerInCombatCooldown(String id, String modifier) {
        super(id, modifier);
        this.setLength(100);
        this.setModuleName("playerincombat");
        this.setId(id, modifier);
    }

}
