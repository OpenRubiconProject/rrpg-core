package com.openrubicon.core.server.playerdata;

import com.openrubicon.core.api.server.players.interfaces.PlayerData;
import com.openrubicon.core.database.models.Player;
import org.bukkit.OfflinePlayer;

import java.util.UUID;

public class PlayerModel implements PlayerData {

    Player playerModel = new Player();

    public PlayerModel() {
    }

    public PlayerModel(String uuid)
    {
        this.load(uuid);
    }

    public PlayerModel(UUID uuid)
    {
        this.load(uuid.toString());
    }

    public Player getPlayerModel()
    {
        return playerModel;
    }

    @Override
    public void initialize(OfflinePlayer player) {
        this.load(player.getUniqueId().toString());
    }

    private void load(String uuid)
    {
        this.playerModel.setUuid(uuid);

        if(playerModel.exists())
            playerModel = playerModel.getPlayer();
        else
            playerModel = null;
    }

}
