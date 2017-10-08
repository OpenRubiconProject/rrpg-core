package com.openrubicon.core.server.playerdata;

import com.openrubicon.core.api.server.players.interfaces.PlayerData;
import org.bukkit.util.Vector;

public class TopSpeed implements PlayerData {

    Vector speed;

    public TopSpeed() {
        this.speed = new Vector(0.,0.,0.);
    }

    public TopSpeed(Vector speed) {
        this.speed = speed;
    }

    public Vector getSpeed() {
        return speed;
    }

    public void setSpeed(Vector speed) {
        this.speed = speed;
    }
}
