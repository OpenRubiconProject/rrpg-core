package com.openrubicon.core.api.account;

import com.openrubicon.core.database.models.Player;

import java.util.Date;

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

    public boolean linkDiscord(String email, String password, long discordId)
    {
        Player player = new Player();
        player.setEmail(email);
        player.setPassword(password);

        if(player.count("id").where("email = :email AND password = :password").executeCount() != 1)
            return false;

        player = player.selectAll().where("email = :email").where("password = :password").whereNotDeleted().whereNotDeleted().executeFetch(Player.class).get(0);
        player.setDiscord_id(discordId);
        player.setUpdated_at(new Date());
        player.update().set("discord_id", ":discord_id").andSet("updated_at", ":updated_at").where("id", ":id").whereNotDeleted().executeUpdate();

        return true;
    }

    public boolean linkMinecraft(String email, String password, String uuid)
    {
        Player player = new Player();
        player.setEmail(email);
        player.setPassword(password);

        if(player.count("id").where("email = :email AND password = :password").whereNotDeleted().executeCount() != 1)
            return false;

        player = player.selectAll().where("email = :email").where("password = :password").whereNotDeleted().executeFetch(Player.class).get(0);
        player.setUuid(uuid);
        player.setUpdated_at(new Date());
        player.update().set("uuid", ":uuid").andSet("updated_at", ":updated_at").where("id", ":id").whereNotDeleted().executeUpdate();

        return true;
    }

    public boolean login(String email, String password)
    {
        Player player = new Player();
        player.setEmail(email);
        player.setPassword(password);

        if(player.count("id").where("email = :email AND password = :password").whereNotDeleted().executeCount() != 1)
            return false;

        return true;
    }

}
