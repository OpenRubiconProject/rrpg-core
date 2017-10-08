package com.openrubicon.core.api.server;

import com.openrubicon.core.api.database.interfaces.PostDatabaseLoad;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

public class Players implements PostDatabaseLoad {

    private static HashMap<OfflinePlayer, com.openrubicon.core.database.models.Player> players = new HashMap<>();

    private static HashMap<OfflinePlayer, Vector> playerTopSpeed = new HashMap<>();
    private static HashMap<OfflinePlayer, Location> playerPreviousLocation = new HashMap<>();

    public OfflinePlayer getPlayer(String uuid)
    {
        return Bukkit.getOfflinePlayer(UUID.fromString(uuid));
    }

    public OfflinePlayer getPlayer(UUID uuid)
    {
        return Bukkit.getOfflinePlayer(uuid);
    }

    public ArrayList<OfflinePlayer> getAllPlayers()
    {
        return new ArrayList<>(Arrays.asList(Bukkit.getOfflinePlayers()));
    }

    public ArrayList<Player> getOnlinePlayers()
    {
        return new ArrayList<>(Bukkit.getOnlinePlayers());
    }

    public void reconcile()
    {
        for(OfflinePlayer player : players.keySet())
        {
            if(!player.isOnline())
            {
                this.removePlayer(player);
            }
        }
    }

    public void addPlayer(Player p)
    {
        com.openrubicon.core.database.models.Player player = new com.openrubicon.core.database.models.Player();
        player.setUuid(p.getUniqueId().toString());

        if(player.exists())
            player = player.getPlayer();
        else
            player = null;

        players.put(p, player);
        playerTopSpeed.put(p, new Vector(0.,0.,0.));
        playerPreviousLocation.put(p, p.getLocation());
    }

    public void removePlayer(OfflinePlayer player)
    {
        if(this.isRegistered(player))
        {
            players.get(player).update().touch().executeUpdate();
        }
        players.remove(player);
        playerTopSpeed.remove(player);
        playerPreviousLocation.remove(player);
    }

    public boolean isRegistered(OfflinePlayer player)
    {
        if(players.get(player) != null)
            return true;
        return false;
    }

    public static HashMap<OfflinePlayer, Vector> getPlayerTopSpeed() {
        return playerTopSpeed;
    }

    public static HashMap<OfflinePlayer, Location> getPlayerPreviousLocation() {
        return playerPreviousLocation;
    }

    @Override
    public void run() {
        for(Player player : this.getOnlinePlayers())
        {
            this.addPlayer(player);
        }
    }
}
