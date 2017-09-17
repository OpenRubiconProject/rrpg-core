package com.openrubicon.core.api.cooldowns;

import com.openrubicon.core.configuration.Configuration;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class CooldownManager {

    private static HashSet<Cooldown> cooldowns = new HashSet<>();

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
        }
    }

    public static HashMap<LivingEntity, EntityCooldowns> getEntities() {
        return entities;
    }

    public static void setEntities(HashMap<LivingEntity, EntityCooldowns> entities) {
        CooldownManager.entities = entities;
    }

    public static HashSet<Cooldown> getSystemCooldowns() {
        return systemCooldowns;
    }

    public static void setSystemCooldowns(LinkedHashSet<Cooldown> systemCooldowns) {
        CooldownManager.systemCooldowns = systemCooldowns;
    }

    public static void addCooldownFromEntity(LivingEntity entity, ItemStack i, Inventory.SlotType slot)
    {
        FullItem item = new FullItem(i);
        CooldownManager.addCooldownFromEntity(entity, item, slot);
    }

    public static void addCooldownFromEntity(LivingEntity entity, FullItem item, Inventory.SlotType slot)
    {
        if(!item.isSpecialItem())
            return;

        for(Socket socket : item.getSockets().getSockets().values())
        {
            if(!socket.isUsingCooldown())
                continue;

            CooldownManager.addCooldownFromEntity(entity, socket.getCooldown(), slot);
        }
    }

    public static void addCooldownFromEntity(LivingEntity entity, Cooldown cooldown, Inventory.SlotType slot)
    {
        //if(cooldown.isCooldown())
        //return;

        CooldownManager.addEntity(entity);


        CooldownManager.getEntities().get(entity).add(cooldown.getId(), cooldown, slot);
    }

    public static void removeCooldownFromEntity(LivingEntity entity, ItemStack i)
    {
        FullItem item = new FullItem(i);
        CooldownManager.removeCooldownFromEntity(entity, item);
    }

    public static void removeCooldownFromEntity(LivingEntity entity, FullItem item)
    {
        if(!item.isSpecialItem())
            return;

        for(Socket socket : item.getSockets().getSockets().values())
        {
            if(!socket.isUsingCooldown())
                continue;

            CooldownManager.removeCooldownFromEntity(entity, socket.getCooldown());
        }
    }

    public static void removeCooldownFromEntity(LivingEntity entity, Cooldown cooldown)
    {
        CooldownManager.addEntity(entity);

        CooldownManager.getEntities().get(entity).remove(cooldown.getId());
    }

    public static void start(LivingEntity entity, Cooldown cooldown, Inventory.SlotType slot, int cdr)
    {
        EntityInventory inventory = new EntityInventory(entity);

        for(FullItem item : inventory.getArmorWithSocket(new CooldownReduction()))
        {
            CooldownReduction socket = (CooldownReduction)item.getSockets().get(new CooldownReduction());
            cdr += socket.getCDR();
        }
        //Bukkit.broadcastMessage("CDR2:" + cdr);
        cooldown.setCooldownReduction(cdr);

        cooldown.start();

        CooldownManager.addEntity(entity);

        EntityCooldowns entitiesCooldowns = CooldownManager.entities.get(entity);

        entitiesCooldowns.add(cooldown.getId(), cooldown, slot);

        //Bukkit.broadcastMessage(entity.getName());
        //Bukkit.broadcastMessage(cooldown.getId());
    }

    /*public static void start(LivingEntity entity, Cooldown cooldown, Inventory.SlotType slot)
    {
        CooldownManager.start(entity, cooldown, slot, 0);
    }*/

    public static void start(LivingEntity entity, Cooldown cooldown, Inventory.SlotType slot)
    {
        EntityInventory inventory = new EntityInventory(entity);

        FullItem item = new FullItem(inventory.getSlot(slot));

        int cdr = 0;

        if(item.isSpecialItem() && item.isValid())
        {
            if(item.getSockets().has(new Spam()))
            {
                Spam socket = (Spam)item.getSockets().get(new Spam());
                cdr += socket.getCDR();
            }
        }

        //Bukkit.broadcastMessage("CDR1:" + cdr);

        CooldownManager.start(entity, cooldown, slot, cdr);
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

    public static void resume(Cooldown cooldown)
    {
        cooldown.setBypass(false);
        cooldown.setLocked(false);
    }

    public static void insta(Cooldown cooldown)
    {
        cooldown.setBypass(true);
    }


    public static void regsiter(Cooldown cooldown)
    {
        CooldownManager.add(cooldown);
    }

    public static void add(Cooldown cooldown)
    {
        CooldownManager.cooldowns.put(cooldown.getId(), cooldown);
    }

    public static Cooldown remove(Cooldown cooldown)
    {
        Cooldown cooldownTemp = CooldownManager.get(cooldown);
        CooldownManager.cooldowns.remove(cooldown.getId(), cooldown);
        return cooldownTemp;
    }

    public static Cooldown get(String id)
    {
        return CooldownManager.cooldowns.get(id);
    }

    public static boolean has(Cooldown cooldown)
    {
        if(cooldown == null)
            return false;
        return CooldownManager.cooldowns.containsKey(cooldown.getId());
    }

    public static Cooldown get(Cooldown cooldown)
    {
        return CooldownManager.cooldowns.get(cooldown.getId());
    }

    public static void addEntity(LivingEntity entity)
    {
        if(CooldownManager.entities.containsKey(entity))
            return;

        if(entity instanceof Player)
        {
            CooldownManager.entities.put(entity, new PlayerCooldowns());
        } else {
            CooldownManager.entities.put(entity, new EntityCooldowns());
        }


    }


}
