package com.openrubicon.core.api.account;

import com.openrubicon.core.database.models.Player;

public class AccountManagement {

    public boolean register(String email, String password)
    {
        Player player = new Player();
        player.setEmail(email);
        player.setPassword(password);

        if(player.count("id").where("deleted_at is null AND email = :email").executeCount() > 0)
            return false;

        player.insert("email,password", ":email, :password").executeInsert();

        return true;
    }

   // public boolean linkDiscord()

}
