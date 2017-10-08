package com.openrubicon.core.server.playerdata;

import com.openrubicon.core.api.server.players.interfaces.PlayerData;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;

public class PreviousLocation implements PlayerData {

    Location location;

    public PreviousLocation() {

    }

    public PreviousLocation(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public void initialize(OfflinePlayer player) {
        if(!player.isOnline())
            return;
        this.location = player.getPlayer().getLocation();
    }
}
