package com.openrubicon.core.api.cooldowns;

import org.bukkit.entity.Entity;

import java.util.HashSet;

public class EntityCooldownManager extends CooldownManager {

    public static boolean has(Entity entity)
    {
        for(Cooldown cooldown : CooldownManager.getCooldowns())
        {
            if(!(cooldown instanceof EntityCooldown))
                continue;

            if(entity == ((EntityCooldown) cooldown).getEntity())
                return true;
        }

        return false;
    }


    public static HashSet<EntityCooldown> get(Entity entity)
    {
        HashSet<EntityCooldown> cooldowns = new HashSet<>();

        for(Cooldown cooldown : CooldownManager.getCooldowns())
        {
            if(!(cooldown instanceof EntityCooldown))
                continue;

            if(entity == ((EntityCooldown) cooldown).getEntity())
                cooldowns.add((EntityCooldown) cooldown);

        }

        return cooldowns;
    }

}
