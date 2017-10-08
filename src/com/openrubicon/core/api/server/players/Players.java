package com.openrubicon.core.api.server.players;

import com.openrubicon.core.RRPGCore;
import com.openrubicon.core.api.database.interfaces.PostDatabaseLoad;
import com.openrubicon.core.api.server.players.interfaces.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

public class Players implements PostDatabaseLoad {

    private static HashMap<OfflinePlayer, com.openrubicon.core.api.server.players.Player> players = new HashMap<>();

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
        com.openrubicon.core.api.server.players.Player player = new com.openrubicon.core.api.server.players.Player();

        for(PlayerData playerDataType : RRPGCore.modules.getPlayerDatas())
        {
            try {
                PlayerData playerData = playerDataType.getClass().newInstance();
                playerData.initialize(p);
                player.addData(playerData);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }

        players.put(p, player);
    }

    public void removePlayer(OfflinePlayer player)
    {
        for(PlayerData playerData : players.get(player).getPlayerDatas())
        {
            playerData.destruct(player);
        }
        players.remove(player);
    }

    public <T extends PlayerData> T getPlayerData(OfflinePlayer player, Class<T> dataType)
    {
        return players.get(player).getData(dataType);
    }

    @Override
    public void run() {
        for(Player player : this.getOnlinePlayers())
        {
            this.addPlayer(player);
        }
    }
}
