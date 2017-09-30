package com.openrubicon.core.api.database;

import com.openrubicon.core.api.database.interfaces.DatabaseConnection;

import java.util.List;

abstract public class DatabaseModel<T> extends QueryBuilder<T> implements com.openrubicon.core.api.database.interfaces.DatabaseModel, DatabaseConnection {

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

    public int executeInsert(Connection connection)
    {
        return connection.get().createQuery(this.getSql(), true).bind(this).executeUpdate().getKey(Integer.class);
    }

    public void executeUpdate(Connection connection)
    {
        connection.get().createQuery(this.getSql()).bind(this).executeUpdate();
    }

    public <T extends DatabaseModel> List<T> executeFetch(Connection connection, Class<T> modelType)
    {
        return connection.get().createQuery(this.getSql()).bind(this).executeAndFetch(modelType);
    }

    public <T extends DatabaseModel> T executeFetchFirst(Connection connection, Class<T> modelType)
    {
        return connection.get().createQuery(this.getSql()).bind(this).executeAndFetch(modelType).get(0);
    }

    public int executeCount(Connection connection)
    {
        return connection.get().createQuery(this.getSql()).bind(this).executeScalar(Integer.class);
    }

    public int executeInsert()
    {
        return Database.connection().get().createQuery(this.getSql(), true).bind(this).executeUpdate().getKey(Integer.class);
    }

    public void executeUpdate()
    {
        Database.connection().get().createQuery(this.getSql()).bind(this).executeUpdate();
    }

    public <T extends DatabaseModel> List<T> executeFetch(Class<T> modelType)
    {
        return Database.connection().get().createQuery(this.getSql()).bind(this).executeAndFetch(modelType);
    }

    public int executeCount()
    {
        return Database.connection().get().createQuery(this.getSql()).bind(this).executeScalar(Integer.class);
    }

}
