package com.openrubicon.core.api.cooldowns.events;

import com.openrubicon.core.api.cooldowns.Cooldown;
import com.openrubicon.core.api.events.Event;

public class PrepareCooldownEvent extends Event {
    Cooldown cooldown;

    public PrepareCooldownEvent(Cooldown cooldown) {
        this.cooldown = cooldown;
    }

    public Cooldown getCooldown() {
        return cooldown;
    }
}
