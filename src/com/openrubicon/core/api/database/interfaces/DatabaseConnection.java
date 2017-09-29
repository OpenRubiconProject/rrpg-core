package com.openrubicon.core.api.database.interfaces;

import com.openrubicon.core.api.database.Connection;

public interface DatabaseConnection {

    Connection getConnection();
    void setConnection(Connection connection);

}
