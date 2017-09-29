package com.openrubicon.core.database;

abstract public class QueryBuilder {

    private String query;

    abstract protected String getTableName();

    public QueryBuilder sql(String sql)
    {
        this.query += sql;
        return this;
    }

    public QueryBuilder select(String select)
    {
        this.query = "SELECT " + select + " FROM `" + this.getTableName() + "` ";
        return this;
    }

    public QueryBuilder selectAll()
    {
        this.query = "SELECT * FROM `" + this.getTableName() + "` ";
        return this;
    }
    public QueryBuilder update()
    {
        this.query = "UPDATE `" + this.getTableName() + "` ";
        return this;
    }

    public QueryBuilder insert()
    {
        this.query = "INSERT INTO `" + this.getTableName() + "` ";
        return this;
    }

    public QueryBuilder fields(String fields)
    {
        this.query += "(" + fields + ") ";
        return this;
    }

    public QueryBuilder values(String values)
    {
        this.query += "VALUES (" + values + ") ";
        return this;
    }

    public QueryBuilder delete()
    {
        this.query = "DELETE FROM `" + this.getTableName() + "` ";
        return this;
    }

    public QueryBuilder set(String fieldName, String value)
    {
        this.query += "SET `" + fieldName + "`=" + value + " ";
        return this;
    }

    public QueryBuilder andSet(String fieldName, String value)
    {
        this.query += ", `" + fieldName + "`=" + value + " ";
        return this;
    }

    public QueryBuilder where(String sql)
    {
        this.query += sql + " ";
        return this;
    }

    public String getSql()
    {
        return this.query;
    }

}
