package com.openrubicon.core.helpers;

import com.openrubicon.core.RRPGCore;
import com.openrubicon.core.server.playerdata.TopSpeed;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class PlayerHelpers {

    public static List<Entity> get(Player player){
        List<Entity> entitys = new ArrayList<>();
        for(Entity e : player.getNearbyEntities(10, 10, 10)){
            if(e instanceof LivingEntity){
                if(PlayerHelpers.isLookingAt(player, (LivingEntity) e)){
                    entitys.add(e);
                }
            }
        }

        return entitys;
    }

    public static boolean isLookingAt(Player player, LivingEntity livingEntity){
        Location eye = player.getEyeLocation();
        Vector toEntity = livingEntity.getEyeLocation().toVector().subtract(eye.toVector());
        double dot = toEntity.normalize().dot(eye.getDirection());

        return dot > 0.99D;
    }

    /*public static Player getTargetPlayer(final Player player) {
        return getTarget(player, player.getWorld().getEntities());
    }

    public static Entity getTargetEntity(final Entity entity) {
        return getTarget(entity, entity.getWorld().getEntities());
    }*/

    public static LivingEntity getTarget(final Player player)
    {
        if (player == null)
            return null;

        LivingEntity target = null;
        final double threshold = 1;

        for (final Entity entity : player.getNearbyEntities(25, 25, 25))
        {
            if(!(entity instanceof LivingEntity))
                continue;

            LivingEntity livingEntity = (LivingEntity) entity;

            final Vector n = livingEntity.getLocation().toVector().subtract(player.getLocation().toVector());

            if (player.getLocation().getDirection().normalize().crossProduct(n).lengthSquared() < threshold && n.normalize().dot(player.getLocation().getDirection().normalize()) >= 0)
            {
                if (target == null || target.getLocation().distanceSquared(player.getLocation()) > livingEntity.getLocation().distanceSquared(player.getLocation()))
                    target = livingEntity;
            }
        }

        return target;
    }

    public static boolean isPlayerFalling(Player player)
    {
        return !player.isOnGround() && player.getVelocity().getY() < 0.1;
    }

    public static void trueDamage(LivingEntity livingEntity, double damage)
    {
        livingEntity.setHealth(livingEntity.getHealth() - damage);
    }


}
