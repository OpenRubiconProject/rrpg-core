package com.openrubicon.core.api.database.interfaces;

import com.openrubicon.core.api.database.Connection;

public interface DatabaseMigration {

    boolean up(Connection connection);
    boolean down(Connection connection);
    int getVersion();

}
