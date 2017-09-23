package com.openrubicon.core.api.cooldowns;

import org.bukkit.entity.Entity;

public class EntityCooldown extends Cooldown {

    Entity entity;

    public EntityCooldown(String id, String modifier) {
        super(id, modifier);
    }

    public EntityCooldown(String id, String modifier, Entity entity) {
        super(id, modifier);
        this.entity = entity;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }
}
