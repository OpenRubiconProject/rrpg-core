package com.openrubicon.core.api.account;

import com.openrubicon.core.api.crypto.PBKDF2;
import com.openrubicon.core.database.models.Player;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;

public class AccountManagement {

    public boolean register(String email, String password)
    {
        Player player = new Player();
        player.setEmail(email);

        try {
            player.setPassword(PBKDF2.createHash(password));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return false;
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
            return false;
        }

        player.setCreated_at(new Date());
        player.setUpdated_at(new Date());

        if(player.count("id").where("email", ":email").executeCount() > 0)
            return false;

        player.insert("email,password,created_at,updated_at", ":email, :password, :created_at, :updated_at").executeInsert();

        return true;
    }

    public boolean linkDiscord(String email, String password, long discordId)
    {
        Player player = this.loginPlayer(email, password);
        if(player == null)
            return false;

        player.setDiscord_id(discordId);
        player.update().set("discord_id", ":discord_id").executeUpdate();

        return true;
    }

    public boolean linkMinecraft(String email, String password, String uuid)
    {
        Player player = this.loginPlayer(email, password);
        if(player == null)
            return false;

        player.setUuid(uuid);
        player.update().set("uuid", ":uuid").executeUpdate();

        return true;
    }

    public boolean login(String email, String password)
    {
        if(this.loginPlayer(email, password) == null)
            return false;

        return true;
    }

    private Player loginPlayer(String email, String password)
    {
        Player player = new Player();
        player.setEmail(email);

        if(player.count("id").where("email = :email").executeCount() != 1)
            return null;

        player = player.selectAll().where("email = :email").executeFetchFirst(Player.class);

        try {
            if(PBKDF2.validatePassword(password, player.getPassword()))
            {
                player.setUpdated_at(new Date());
                player.update().touch().executeUpdate();
                return player;
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return null;
    }

}
