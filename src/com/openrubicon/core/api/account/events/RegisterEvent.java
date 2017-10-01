package com.openrubicon.core.api.account.events;

import com.openrubicon.core.database.models.Player;

public class RegisterEvent extends AccountEvent {

    public RegisterEvent(Player player) {
        super(player);
    }
}
