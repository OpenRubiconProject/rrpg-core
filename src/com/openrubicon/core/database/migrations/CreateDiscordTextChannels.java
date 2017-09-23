package com.openrubicon.core.database.migrations;

import com.openrubicon.core.database.Connection;
import com.openrubicon.core.database.interfaces.DatabaseMigration;

public class CreateDiscordTextChannels implements DatabaseMigration {

    @Override
    public boolean up(Connection connection) {
        return connection.createTable("CREATE TABLE IF NOT EXISTS `rubicon_discord_text_channels_test` (\n" +
                " `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
                " `channel_id` bigint(20) NOT NULL,\n" +
                " `disabled` tinyint(1) NOT NULL DEFAULT '0',\n" +
                " `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,\n" +
                " `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,\n" +
                " `deleted_at` datetime DEFAULT NULL,\n" +
                " PRIMARY KEY (`id`)\n" +
                ") ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4");
    }

    @Override
    public boolean down(Connection connection) {
        return connection.dropTable("rubicon_discord_text_channels_test");
    }

    @Override
    public int getVersion() {
        return 1;
    }
}
