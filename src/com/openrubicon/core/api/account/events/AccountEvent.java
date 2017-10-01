package com.openrubicon.core.api.account.events;

import com.openrubicon.core.database.models.Player;

abstract public class AccountEvent {

    Player player;

    public AccountEvent(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

}
