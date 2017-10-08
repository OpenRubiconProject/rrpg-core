package com.openrubicon.core.database.migrations;

import com.openrubicon.core.api.database.Connection;
import com.openrubicon.core.api.database.interfaces.DatabaseMigration;

public class CreateMinecraftPlayers implements DatabaseMigration{

    @Override
    public boolean up(Connection connection) {
        return connection.createTable("CREATE TABLE IF NOT EXISTS `rubicon_core_minecraft_players` (\n" +
                " `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
                " `uuid` varchar(128) NOT NULL,\n" +
                " `username` varchar(64) NOT NULL,\n" +
                " `display_name` varchar(64) NOT NULL,\n" +
                " `last_joined` datetime DEFAULT NULL,\n" +
                " `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,\n" +
                " `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,\n" +
                " `deleted_at` datetime DEFAULT NULL,\n" +
                " `account_id` int(11) NOT NULL DEFAULT '0',\n" +
                " PRIMARY KEY (`id`)\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4");
    }

    @Override
    public boolean down(Connection connection) {
        return connection.dropTable("rubicon_core_minecraft_players");
    }

    @Override
    public int getVersion() {
        return 1;
    }
}
