package com.openrubicon.core.api.account.events;

import com.openrubicon.core.database.models.Player;

public class LinkedDiscordEvent extends AccountEvent {

    public LinkedDiscordEvent(Player player) {
        super(player);
    }
}
