package com.openrubicon.core.api.account.events;

import com.openrubicon.core.database.models.Player;

public class LoginEvent extends AccountEvent {

    public LoginEvent(Player player) {
        super(player);
    }
}
