package com.openrubicon.core.api.server.players.interfaces;

import org.bukkit.OfflinePlayer;

public interface PlayerData {

    default void initialize(OfflinePlayer player)
    {

    }

    default void destruct(OfflinePlayer player)
    {

    }

}
