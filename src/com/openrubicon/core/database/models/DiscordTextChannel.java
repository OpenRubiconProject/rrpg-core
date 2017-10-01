package com.openrubicon.core.database.models;

import com.openrubicon.core.api.database.Connection;
import com.openrubicon.core.api.database.DatabaseModel;
import com.openrubicon.core.api.database.interfaces.DatabaseMigration;
import com.openrubicon.core.database.migrations.CreateDiscordTextChannels;

import java.util.*;

public class DiscordTextChannel extends DatabaseModel {

    private long id;
    private long channel_id;
    private boolean disabled;
    private Date created_at;
    private Date updated_at;
    private Date deleted_at;

    private String tableName = "rubicon_core_discord_text_channels";
    private int version = 1;

    public DiscordTextChannel() {
    }

    public DiscordTextChannel(Connection connection) {
        super(connection);
    }

    public DiscordTextChannel(long channel_id) {
        this.channel_id = channel_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(long channel_id) {
        this.channel_id = channel_id;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
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

    public static List<DiscordTextChannel> getChannels(Connection connection)
    {
           return connection.get().createQuery("SELECT channel_id FROM rubicon_core_discord_text_channels WHERE disabled=0").executeAndFetch(DiscordTextChannel.class);
    }

    @Override
    public HashMap<Integer, DatabaseMigration> getMigrations() {
        HashMap<Integer, DatabaseMigration> migrations = new HashMap<>();
        migrations.put(1, new CreateDiscordTextChannels());
        return migrations;
    }

    @Override
    public String getTableName() {
        return this.tableName;
    }

    @Override
    public int getVersion() {
        return this.version;
    }
}
