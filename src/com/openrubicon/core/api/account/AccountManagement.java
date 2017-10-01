package com.openrubicon.core.api.account;

import com.openrubicon.core.database.models.Player;

import java.util.Date;

public class AccountManagement {

    public boolean register(String email, String password)
    {
        Player player = new Player();
        player.setEmail(email);
        player.setPassword(password);

        player.setCreated_at(new Date());
        player.setUpdated_at(new Date());

        if(player.count("id").where("email", ":email").whereNotDeleted().executeCount() > 0)
            return false;

        player.insert("email,password,created_at,updated_at", ":email, :password, :created_at, :updated_at").executeInsert();

        return true;
    }

    public boolean linkDiscord(String email, String password, long discordId)
    {
        Player player = new Player();
        player.setEmail(email);
        player.setPassword(password);

        if(player.count("id").where("email", ":email").where("password", ":password").executeCount() != 1)
            return false;

        player = player.selectAll().where("email = :email").where("password = :password").executeFetchFirst(Player.class);
        player.setDiscord_id(discordId);
        player.setUpdated_at(new Date());
        player.update().set("discord_id", ":discord_id").touch().executeUpdate();

        return true;
    }

    public boolean linkMinecraft(String email, String password, String uuid)
    {
        Player player = new Player();
        player.setEmail(email);
        player.setPassword(password);

        if(player.count("id").where("email = :email").where("password = :password").executeCount() != 1)
            return false;

        player = player.selectAll().where("email = :email").where("password = :password").executeFetchFirst(Player.class);
        player.setUuid(uuid);
        player.setUpdated_at(new Date());
        player.update().set("uuid", ":uuid").touch().executeUpdate();

        return true;
    }

    public boolean login(String email, String password)
    {
        Player player = new Player();
        player.setEmail(email);
        player.setPassword(password);

        if(player.count("id").where("email = :email").where("password = :password").executeCount() != 1)
            return false;

        player = player.selectAll().where("email = :email").where("password = :password").executeFetchFirst(Player.class);
        player.setUpdated_at(new Date());
        player.update().touch().executeUpdate();

        return true;
    }

}
