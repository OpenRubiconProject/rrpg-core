package com.openrubicon.core.api.database;

import com.openrubicon.core.api.database.interfaces.DatabaseConnection;

abstract public class DatabaseModel extends QueryBuilder implements com.openrubicon.core.api.database.interfaces.DatabaseModel, DatabaseConnection {

    Connection connection;

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public DatabaseModel(Connection connection) {
        this.setConnection(connection);
    }

    public DatabaseModel() {
    }

    public int create(String fields, String binds)
    {
        return (int)(this.connection.get().createQuery(this.insert().fields(fields).values(binds).getSql(), true).bind(this).executeUpdate().getKey());
    }

}
