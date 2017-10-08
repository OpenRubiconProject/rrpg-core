package com.openrubicon.core.events;

import com.openrubicon.core.RRPGCore;
import com.openrubicon.core.api.actionbar.ActionBarManager;
import com.openrubicon.core.api.cooldowns.CooldownManager;
import com.openrubicon.core.api.server.Players;
import com.openrubicon.core.helpers.Helpers;
import com.openrubicon.core.helpers.PlayerHelpers;
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

            if (p.isOnGround() && Players.getPlayerTopSpeed().get(p).getY() < -0.1)
            {
                PlayerLandOnGroundEvent playerLandOnGroundEvent = new PlayerLandOnGroundEvent(p, Players.getPlayerTopSpeed().get(p).getY());
                Bukkit.getPluginManager().callEvent(playerLandOnGroundEvent);
                Players.getPlayerTopSpeed().get(p).setY(0.);
            }

            if (yVel < Players.getPlayerTopSpeed().get(p).getY())
            {
                Players.getPlayerTopSpeed().get(p).setY(yVel);
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


            if(Players.getPlayerPreviousLocation().get(p).getX() != p.getLocation().getX() || Players.getPlayerPreviousLocation().get(p).getY() != p.getLocation().getY() || Players.getPlayerPreviousLocation().get(p).getZ() != p.getLocation().getZ())
            {
                PlayerMovedLocationEvent playerMovedLocationEvent = new PlayerMovedLocationEvent(p, Players.getPlayerPreviousLocation().get(p), p.getLocation());
                Bukkit.getPluginManager().callEvent(playerMovedLocationEvent);
                Players.getPlayerPreviousLocation().put(p, p.getLocation());
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
