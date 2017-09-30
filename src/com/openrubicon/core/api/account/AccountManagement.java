package com.openrubicon.core.api.account;

import com.openrubicon.core.RRPGCore;
import com.openrubicon.core.database.models.Player;

public class AccountManagement {

    public boolean register(String email, String password)
    {
        Player player = new Player(RRPGCore.database.connection());
        player.setEmail(email);
        player.setPassword(password);

        if(player.getConnection().get().createQuery("SELECT count(id) FROM rubicon_players WHERE deleted_at is null AND email = :email").bind(player).executeScalar(Integer.class) > 0)
            return false;

        player.getConnection().get().createQuery(player.insert().fields("email,password").values(":email, :password").getSql(), true).bind(player).executeUpdate().getKey();
        return true;
    }

}
