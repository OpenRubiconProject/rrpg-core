package com.openrubicon.core.api.inventory;

import org.bukkit.entity.Player;

public class PlayerInventory extends LivingEntityInventory {

    public PlayerInventory(Player player) {
        super(player);
    }

    public Player getPlayer() {
        return (Player)entity;
    }

    public void setPlayer(Player player) {
        this.entity = player;
    }

}
