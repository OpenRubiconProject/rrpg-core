package com.openrubicon.core.api.server;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class Players {

    private static ArrayList<com.openrubicon.core.database.models.Player> players = new ArrayList<>();
    private static HashMap<Player, Double> onMoveTopSpeed = new HashMap<>();
    private static HashMap<Player, Location> onMovedLocation = new HashMap<>();

    public ArrayList<Player> getOnlinePlayers()
    {
        return new ArrayList<>(Bukkit.getOnlinePlayers());
    }

    public OfflinePlayer getPlayer(String uuid)
    {
        return new Bukkit.getOfflinePlayer(uuid);
    }

}
