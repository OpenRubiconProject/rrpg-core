package com.openrubicon.core.api.account;

import com.openrubicon.core.api.crypto.PBKDF2;
import com.openrubicon.core.database.models.*;
import org.apache.commons.lang.RandomStringUtils;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;

public class AccountManagement {

    public boolean register(String email, String password)
    {
        com.openrubicon.core.database.models.Account player = new com.openrubicon.core.database.models.Account();
        player.setEmail(email);

        if(player.count("id").where("email", ":email").executeCount() > 0)
            return false;

        try {
            player.setPassword(PBKDF2.createHash(password));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return false;
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
            return false;
        }

        player.setToken(RandomStringUtils.randomAlphanumeric(8));

        player.setCreated_at(new Date());
        player.setUpdated_at(new Date());

        player.setUsername("");
        player.setDisplay_name("");

        player.insert("email,password,created_at,updated_at, token, username, display_name", ":email, :password, :created_at, :updated_at, :token, :username, :display_name").executeInsert();

        return true;
    }

    public boolean linkDiscord(String email, String password, String discordId)
    {
        com.openrubicon.core.database.models.Account player = this.loginPlayer(email, password);
        if(player == null)
            return false;

        DiscordAccount discordAccount = new DiscordAccount();
        discordAccount.setDiscord_id(discordId);

        if(discordAccount.count("id").where("discord_id", ":discord_id").executeCount() > 0)
        {
            discordAccount = discordAccount.selectAll().where("discord_id", ":discord_id").executeFetchFirst(DiscordAccount.class);
            discordAccount.setAccount_id(player.getId());
            discordAccount.update().set("account_id", ":account_id").touch().where("discord_id", ":discord_id").executeUpdate();
            return true;
        }


        discordAccount.setUsername("");
        discordAccount.setDisplay_name("");
        discordAccount.setDiscord_id(discordId);
        discordAccount.setAccount_id(player.getId());

        discordAccount.insert("discord_id, account_id, username, display_name", ":discord_id, :account_id, :username, :display_name").executeInsert();

        return true;
    }

    public boolean linkMinecraft(String email, String password, String uuid)
    {
        com.openrubicon.core.database.models.Account player = this.loginPlayer(email, password);
        if(player == null)
            return false;

        MinecraftPlayer minecraftPlayer = new MinecraftPlayer();
        minecraftPlayer.setUuid(uuid);

        if(minecraftPlayer.count("id").where("uuid", ":uuid").executeCount() > 0)
        {
            minecraftPlayer = minecraftPlayer.selectAll().where("uuid", ":uuid").executeFetchFirst(MinecraftPlayer.class);
            minecraftPlayer.setAccount_id(player.getId());
            minecraftPlayer.update().set("account_id", ":account_id").touch().where("uuid", ":uuid").executeUpdate();
            return true;
        }


        minecraftPlayer.setUsername("");
        minecraftPlayer.setDisplay_name("");
        minecraftPlayer.setUuid(uuid);
        minecraftPlayer.setAccount_id(player.getId());

        minecraftPlayer.insert("uuid, account_id, username, display_name", ":uuid, :account_id, :username, :display_name").executeInsert();

        return true;
    }

    public boolean login(String email, String password)
    {
        if(this.loginPlayer(email, password) == null)
            return false;

        return true;
    }

    private com.openrubicon.core.database.models.Account loginPlayer(String email, String password)
    {
        com.openrubicon.core.database.models.Account player = new com.openrubicon.core.database.models.Account();
        player.setEmail(email);

        if(player.count("id").where("email = :email").executeCount() != 1)
            return null;

        player = player.selectAll().where("email = :email").executeFetchFirst(com.openrubicon.core.database.models.Account.class);

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
