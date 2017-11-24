package com.openrubicon.core.api.cooldowns;

import com.openrubicon.core.RRPGCore;
import com.openrubicon.core.api.cooldowns.events.CooldownCompletedEvent;
import com.openrubicon.core.api.cooldowns.events.PrepareCooldownEvent;
import com.openrubicon.core.configuration.CooldownReductionCap;
import org.bukkit.Bukkit;

import java.util.ArrayList;

public class CooldownManager {

    private static ArrayList<Cooldown> cooldowns = new ArrayList<>();

    public static ArrayList<Cooldown> getCooldowns() {
        return cooldowns;
    }

    public static void passTime(int time)
    {
        //Bukkit.broadcastMessage("here");

        for(Cooldown cooldown : CooldownManager.cooldowns)
        {
            if(cooldown.isLocked())
                continue;

            if(cooldown.getCurrent() == 0)
                continue;

            cooldown.setCurrent(cooldown.getCurrent() - time);

            //Bukkit.broadcastMessage(cooldown.getCurrent()+"");

            if(cooldown.getCurrent() < 0)
                cooldown.setCurrent(0);

            if(cooldown.getCurrent() == 0)
                Bukkit.getPluginManager().callEvent(new CooldownCompletedEvent(cooldown));

        }
    }

    public static void start(Cooldown cooldown)
    {
        cooldown.setBypass(false);

        Bukkit.getPluginManager().callEvent(new PrepareCooldownEvent(cooldown));

        CooldownManager.reset(cooldown);

        if(!CooldownManager.has(cooldown))
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
        if(reduction > RRPGCore.config.get(CooldownReductionCap.class).getInt())
            reduction = RRPGCore.config.get(CooldownReductionCap.class).getInt();

        cooldown.setCurrent((int)(cooldown.getLength() - ((float)cooldown.getLength() * ((float)reduction / 100f))));
        //Bukkit.broadcastMessage("CDR3:" + this.current);
        if(cooldown.getCurrent() < 0)
            cooldown.setCurrent(0);

        cooldown.setCooldownReduction(0);
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
