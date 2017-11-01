package com.openrubicon.core.api.database.migrations;

import com.openrubicon.core.api.database.Connection;
import com.openrubicon.core.api.database.interfaces.DatabaseMigration;

public class CreateModelVersions implements DatabaseMigration {
    @Override
    public boolean up(Connection connection) {
        connection.createTable("CREATE TABLE IF NOT EXISTS `rubicon_core_model_versions` (\n" +
                " `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
                " `table_name` varchar(128) NOT NULL,\n" +
                " `migration_version` int(11) NOT NULL,\n" +
                " `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,\n" +
                " `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,\n" +
                " PRIMARY KEY (`id`)\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4");
        return true;
    }

    @Override
    public boolean down(Connection connection) {
        connection.dropTable("rubicon_core_model_versions");
        return true;
    }

    @Override
    public int getVersion() {
        return 1;
    }
}
