package com.openrubicon.core.api.actionbar;

import com.openrubicon.core.api.cooldowns.Cooldown;
import com.openrubicon.core.api.cooldowns.EntityCooldownManager;
import org.bukkit.entity.Entity;

public class ActionBarCooldownManager extends EntityCooldownManager {

    public static boolean has(Entity entity)
    {
        if(ActionBarCooldownManager.getActionBar(entity) == null)
            return false;

        return true;
    }

    public static ActionBarCooldown getActionBar(Entity entity)
    {
        for(Cooldown cooldown : ActionBarCooldownManager.getCooldowns())
        {
            if(!(cooldown instanceof ActionBarCooldown))
                continue;

            if(entity == ((ActionBarCooldown) cooldown).getEntity())
                return (ActionBarCooldown) cooldown;
        }

        return null;
    }

    public static void add(Cooldown cooldown)
    {
        if(ActionBarCooldownManager.has(cooldown))
            return;

        ActionBarCooldownManager.getCooldowns().add(cooldown);
    }


}
