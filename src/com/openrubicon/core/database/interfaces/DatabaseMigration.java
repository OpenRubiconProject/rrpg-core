package com.openrubicon.core.database.interfaces;

import com.openrubicon.core.database.Connection;

public interface DatabaseMigration {

    boolean up(Connection connection);
    boolean down(Connection connection);
    int getVersion();

}
