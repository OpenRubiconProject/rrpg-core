package com.openrubicon.core.api.cooldowns;

import com.openrubicon.core.api.cooldowns.events.CooldownCompletedEvent;
import com.openrubicon.core.configuration.Configuration;
import org.bukkit.Bukkit;

import java.util.HashSet;

public class CooldownManager {

    private static HashSet<Cooldown> cooldowns = new HashSet<>();

    public static HashSet<Cooldown> getCooldowns() {
        return cooldowns;
    }

    public static void passTime(int time)
    {
        for(Cooldown cooldown : CooldownManager.cooldowns)
        {
            if(cooldown.isLocked())
                return;

            if(cooldown.getCurrent() == 0)
                return;

            cooldown.setCurrent(cooldown.getCurrent() - time);

            if(cooldown.getCurrent() < 0)
                cooldown.setCurrent(0);

            if(cooldown.getCurrent() == 0)
                Bukkit.getPluginManager().callEvent(new CooldownCompletedEvent(cooldown));

        }
    }

    public static void start(Cooldown cooldown)
    {
        cooldown.setBypass(false);
        CooldownManager.reset(cooldown);

        if(!CooldownManager.cooldowns.contains(cooldown))
        {
            CooldownManager.cooldowns.add(cooldown);
        }
    }

    public static void stop(Cooldown cooldown)
    {
        cooldown.setLocked(true);
    }

    public static void reset(Cooldown cooldown)
    {
        int reduction = cooldown.getCooldownReduction();
        if(reduction > Configuration.COOLDOWN_REDUCTION_CAP)
            reduction = Configuration.COOLDOWN_REDUCTION_CAP;

        cooldown.setCurrent((int)(cooldown.getLength() - ((float)cooldown.getLength() * ((float)reduction / 100f))));
        //Bukkit.broadcastMessage("CDR3:" + this.current);
        if(cooldown.getCurrent() < 0)
            cooldown.setCurrent(0);
    }

    public static void skip(Cooldown cooldown)
    {
        CooldownManager.reset(cooldown);
    }

    public static void resume(Cooldown cooldown)
    {
        cooldown.setBypass(false);
        cooldown.setLocked(false);
    }

    public static void insta(Cooldown cooldown)
    {
        cooldown.setBypass(true);
    }

    public static void add(Cooldown cooldown)
    {
        CooldownManager.cooldowns.add(cooldown);
    }

    public static Cooldown remove(Cooldown cooldown)
    {
        Cooldown cooldownTemp = CooldownManager.get(cooldown);
        CooldownManager.cooldowns.remove(cooldown);
        return cooldownTemp;
    }

    public static Cooldown get(String id)
    {
        for(Cooldown cooldown : CooldownManager.cooldowns)
        {
            if(id.equals(cooldown.getId()))
                return cooldown;
        }
        return null;
    }

    public static boolean has(Cooldown cooldown)
    {
        if(cooldown == null)
            return false;
        for(Cooldown cdown : CooldownManager.cooldowns)
        {
            if(cdown.getId().equals(cooldown.getId()))
                return true;
        }
        return false;
    }

    public static Cooldown get(Cooldown cooldown)
    {
        for(Cooldown cdown : CooldownManager.cooldowns)
        {
            if(cdown.getId().equals(cooldown.getId()))
                return cooldown;
        }
        return null;
    }

}
