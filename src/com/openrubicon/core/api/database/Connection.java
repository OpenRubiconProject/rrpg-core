package com.openrubicon.core.api.database;

import org.sql2o.Sql2o;

public class Connection {

    private Sql2o api;

    public Connection(Sql2o sql2o) {
        this.api = sql2o;
    }

    public org.sql2o.Connection get()
    {
        try(org.sql2o.Connection connection = this.api.open())
        {
            return connection;
        } catch(Exception e)
        {
            return null;
        }
    }

    public boolean createTable(String schema)
    {
        this.get().createQuery(schema).executeUpdate();
        return true;
    }

    public boolean dropTable(String tableName)
    {
        this.get().createQuery("DROP TABLE IF EXISTS `"+tableName+"`").executeUpdate();
        return true;
    }
}
