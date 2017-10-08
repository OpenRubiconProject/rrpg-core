package com.openrubicon.core.server.playerdata;

import com.openrubicon.core.api.server.players.interfaces.PlayerData;
import com.openrubicon.core.database.models.MinecraftPlayer;
import org.bukkit.OfflinePlayer;

import java.util.Date;

public class MinecraftPlayerModel implements PlayerData {

    MinecraftPlayer playerModel = new MinecraftPlayer();

    public MinecraftPlayerModel() {
    }

    public MinecraftPlayer getPlayerModel()
    {
        return playerModel;
    }

    @Override
    public void initialize(OfflinePlayer player) {
        this.load(player);
    }

    private void load(OfflinePlayer player)
    {
        this.playerModel.setUuid(player.getUniqueId().toString());

        if(playerModel.count("id").where("uuid", ":uuid").executeCount() == 1)
        {
            playerModel = playerModel.selectAll().where("uuid", ":uuid").executeFetchFirst(MinecraftPlayer.class);
            playerModel.setLast_joined(new Date());
            playerModel.update().set("last_joined").executeUpdate();
        }
        else
        {
            if(player.isOnline())
            {
                this.playerModel.setDisplay_name(player.getPlayer().getDisplayName());
            } else {
                this.playerModel.setDisplay_name(player.getName());
            }

            this.playerModel.setUsername(player.getName());
            playerModel.setLast_joined(new Date());

            playerModel.insert("uuid, username, display_name, last_joined", ":uuid, :username, :display_name, :last_joined").executeInsert();
        }

    }

}
