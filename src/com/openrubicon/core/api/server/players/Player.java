package com.openrubicon.core.api.server.players;

import com.openrubicon.core.api.server.players.interfaces.PlayerData;

import java.util.HashSet;

public class Player {

    HashSet<PlayerData> playerDatas = new HashSet<>();

    public HashSet<PlayerData> getPlayerDatas() {
        return playerDatas;
    }

    public void addData(PlayerData data)
    {
        this.playerDatas.add(data);
    }

    public void removeData(PlayerData data)
    {
        this.playerDatas.remove(data);
    }

    public boolean hasData(Class playerDataClass)
    {
        return this.getData(playerDataClass) != null;
    }

    public <T extends PlayerData> T getData(Class<T> playerDataClass)
    {
        for(PlayerData playerData : this.playerDatas)
        {
            if(playerData.getClass() == playerDataClass)
                return (T)playerData;
        }
        return null;
    }

}
