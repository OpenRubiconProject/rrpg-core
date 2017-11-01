package com.openrubicon.core.api.events;

import org.bukkit.entity.LivingEntity;

public class LivingEntityEvent extends Event {

    LivingEntity livingEntity;

    public LivingEntityEvent(LivingEntity livingEntity) {
        this.livingEntity = livingEntity;
    }

    public LivingEntity getLivingEntity() {
        return livingEntity;
    }

}
