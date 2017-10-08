package com.openrubicon.core.database.models;

import com.openrubicon.core.api.database.DatabaseModel;
import com.openrubicon.core.api.database.interfaces.DatabaseMigration;
import com.openrubicon.core.database.migrations.CreateDiscordAccounts;

import java.util.Date;
import java.util.HashMap;

public class DiscordAccount extends DatabaseModel<DiscordAccount> {

    private long id;
    private long account_id;
    private String username = "";
    private String display_name = "";
    private String discord_id;
    private Date last_joined;
    private Date created_at;
    private Date updated_at;
    private Date deleted_at;

    private String tableName = "rubicon_core_discord_accounts";
    private int version = 1;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAccount_id() {
        return account_id;
    }

    public void setAccount_id(long account_id) {
        this.account_id = account_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public String getDiscord_id() {
        return discord_id;
    }

    public void setDiscord_id(String discord_id) {
        this.discord_id = discord_id;
    }

    public Date getLast_joined() {
        return last_joined;
    }

    public void setLast_joined(Date last_joined) {
        this.last_joined = last_joined;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public Date getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(Date deleted_at) {
        this.deleted_at = deleted_at;
    }

    @Override
    public HashMap<Integer, DatabaseMigration> getMigrations() {
        HashMap<Integer, DatabaseMigration> migrations = new HashMap<>();
        migrations.put(1, new CreateDiscordAccounts());
        return migrations;
    }

    @Override
    public String getTableName() {
        return this.tableName;
    }

    @Override
    public int getVersion() {
        return 1;
    }
}
