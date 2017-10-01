package com.openrubicon.core.database.migrations;

import com.openrubicon.core.api.database.Connection;
import com.openrubicon.core.api.database.interfaces.DatabaseMigration;

public class CreatePlayers implements DatabaseMigration {

    @Override
    public boolean up(Connection connection) {
        connection.createTable("CREATE TABLE IF NOT EXISTS `rubicon_core_players` (\n" +
                " `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
                " `uuid` varchar(128) NOT NULL DEFAULT '',\n" +
                " `username` varchar(32) NOT NULL DEFAULT '',\n" +
                " `display_name` varchar(24) NOT NULL DEFAULT '',\n" +
                " `email` varchar(128) NOT NULL DEFAULT '',\n" +
                " `password` varchar(64) NOT NULL DEFAULT '',\n" +
                " `discord_id` bigint(20) DEFAULT NULL,\n" +
                " `verified` int(11) NOT NULL DEFAULT '0',\n" +
                " `token` varchar(16) NOT NULL DEFAULT '',\n" +
                " `last_joined` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,\n" +
                " `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,\n" +
                " `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,\n" +
                " `deleted_at` datetime DEFAULT NULL,\n" +
                " PRIMARY KEY (`id`)\n" +
                ") ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4");
        return true;
    }

    @Override
    public boolean down(Connection connection) {
        connection.dropTable("rubicon_players");
        return true;
    }

    @Override
    public int getVersion() {
        return 1;
    }

}
