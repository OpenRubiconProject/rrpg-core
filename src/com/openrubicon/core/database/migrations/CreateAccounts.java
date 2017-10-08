package com.openrubicon.core.database.migrations;

import com.openrubicon.core.api.database.Connection;
import com.openrubicon.core.api.database.interfaces.DatabaseMigration;

public class CreateAccounts implements DatabaseMigration {

    @Override
    public boolean up(Connection connection) {
        return connection.createTable("CREATE TABLE IF NOT EXISTS `rubicon_core_accounts` (\n" +
                " `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
                " `email` varchar(128) NOT NULL,\n" +
                " `password` varchar(256) NOT NULL,\n" +
                " `token` varchar(128) NOT NULL,\n" +
                " `verified` tinyint(1) NOT NULL DEFAULT '0',\n" +
                " `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,\n" +
                " `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,\n" +
                " `deleted_at` datetime DEFAULT NULL,\n" +
                " `username` varchar(32) NOT NULL,\n" +
                " `display_name` varchar(16) NOT NULL,\n" +
                " PRIMARY KEY (`id`)\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4");
    }

    @Override
    public boolean down(Connection connection) {
        return connection.dropTable("rubicon_core_accounts");
    }

    @Override
    public int getVersion() {
        return 1;
    }
}
