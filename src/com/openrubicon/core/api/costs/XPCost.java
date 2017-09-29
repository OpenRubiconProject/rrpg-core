package com.openrubicon.core.api.costs;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class XPCost extends Cost {

    public XPCost(float cost) {
        super(cost);
    }

    public boolean has(Player player)
    {
        return player.getLevel() >= this.getCost() || player.getGameMode() == GameMode.CREATIVE;
    }

    public void withdraw(Player player)
    {
        if(player.getGameMode() == GameMode.CREATIVE)
            return;

        if(player.getLevel() < this.getCost())
            return;

        player.setLevel(player.getLevel() - (int)this.getCost());
    }
}
