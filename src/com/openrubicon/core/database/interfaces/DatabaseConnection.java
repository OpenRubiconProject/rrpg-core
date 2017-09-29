package com.openrubicon.core.database.interfaces;

import com.openrubicon.core.database.Connection;

public interface DatabaseConnection {

    Connection getConnection();
    void setConnection(Connection connection);

}
