package com.openrubicon.core.events;

import com.openrubicon.core.RRPGCore;
import com.openrubicon.core.api.actionbar.ActionBarManager;
import com.openrubicon.core.api.cooldowns.CooldownManager;
import com.openrubicon.core.api.server.players.Players;
import com.openrubicon.core.helpers.PlayerHelpers;
import com.openrubicon.core.server.playerdata.PreviousLocation;
import com.openrubicon.core.server.playerdata.TopSpeed;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class EventListener implements Listener {

    @EventHandler
    public void onOneTick(OneTickEvent e)
    {
        if(!RRPGCore.doneLoading)
            return;

        for(Player p : RRPGCore.players.getOnlinePlayers()) {
            double yVel = p.getVelocity().getY();

            if (p.isOnGround() && RRPGCore.players.getPlayerData(p, TopSpeed.class).getSpeed().getY() < -0.1)
            {
                PlayerLandOnGroundEvent playerLandOnGroundEvent = new PlayerLandOnGroundEvent(p, RRPGCore.players.getPlayerData(p, TopSpeed.class).getSpeed().getY());
                Bukkit.getPluginManager().callEvent(playerLandOnGroundEvent);
                RRPGCore.players.getPlayerData(p, TopSpeed.class).getSpeed().setY(0.);
            }

            if (yVel < RRPGCore.players.getPlayerData(p, TopSpeed.class).getSpeed().getY())
            {
                RRPGCore.players.getPlayerData(p, TopSpeed.class).getSpeed().setY(yVel);
                Bukkit.broadcastMessage(RRPGCore.players.getPlayerData(p, TopSpeed.class).getSpeed().getY()+"");
            }

        }
    }

    @EventHandler
    public void onFiveTick(FiveTickEvent e)
    {
        if(!RRPGCore.doneLoading)
            return;

        for(Player p : RRPGCore.players.getOnlinePlayers()) {

            LivingEntity livingEntity = PlayerHelpers.getTarget(p);
            PlayerLookingAtEntityEvent playerLookingAtEntityEvent = new PlayerLookingAtEntityEvent(p, livingEntity);
            Bukkit.getPluginManager().callEvent(playerLookingAtEntityEvent);


            if(RRPGCore.players.getPlayerData(p, PreviousLocation.class).getLocation().getX() != p.getLocation().getX() || RRPGCore.players.getPlayerData(p, PreviousLocation.class).getLocation().getY() != p.getLocation().getY() || RRPGCore.players.getPlayerData(p, PreviousLocation.class).getLocation().getZ() != p.getLocation().getZ())
            {
                PlayerMovedLocationEvent playerMovedLocationEvent = new PlayerMovedLocationEvent(p, RRPGCore.players.getPlayerData(p, PreviousLocation.class).getLocation(), p.getLocation());
                Bukkit.getPluginManager().callEvent(playerMovedLocationEvent);
                RRPGCore.players.getPlayerData(p, PreviousLocation.class).setLocation(p.getLocation());
            } else {
                PlayerStandingStillEvent playerStandingStillEvent = new PlayerStandingStillEvent(p, p.getLocation());
                Bukkit.getPluginManager().callEvent(playerStandingStillEvent);
            }

        }

        CooldownManager.passTime(5);
        ActionBarManager.process();
    }

    @EventHandler
    public void onFiveMinute(FiveMinuteEvent e)
    {
        RRPGCore.players.reconcile();
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        RRPGCore.players.addPlayer(e.getPlayer());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e)
    {
        //Bukkit.broadcastMessage("quit");
        RRPGCore.players.removePlayer(e.getPlayer());
    }

    @EventHandler
    public void onPlayerKick(PlayerKickEvent e)
    {
        //Bukkit.broadcastMessage("kick");
    }


}
