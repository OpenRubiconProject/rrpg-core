package com.openrubicon.core.api.database;

import java.util.Date;

abstract public class QueryBuilder<T> {

    private String query;

    private String wheres = "";
    private String sqls = "";

    private boolean isSet = false;

    abstract protected String getTableName();

    private void reset()
    {
        this.query = "";
        this.wheres = "";
        this.sqls = "";
    }

    public T sql(String sql)
    {
        this.sqls += sql;
        return (T)(this);
    }

    public T select(String select)
    {
        this.reset();
        this.query = "SELECT " + select + " FROM `" + this.getTableName() + "` ";
        this.whereNotDeleted();
        return (T)(this);
    }

    public T selectAll()
    {
        this.reset();
        this.query = "SELECT * FROM `" + this.getTableName() + "` ";
        this.whereNotDeleted();
        return (T)(this);
    }

    public T count(String field)
    {
        this.reset();
        this.query = "SELECT count(" + field + ") FROM `" + this.getTableName() + "` ";
        this.whereNotDeleted();
        return (T)(this);
    }

    public T update()
    {
        this.reset();
        this.query = "UPDATE `" + this.getTableName() + "` ";
        this.wheres = "WHERE `id`=:id ";
        this.whereNotDeleted();
        return (T)(this);
    }

    public T update(String index, String indexValue)
    {
        this.reset();
        this.query = "UPDATE `" + this.getTableName() + "` ";
        this.wheres = "WHERE `" + index + "`=" + indexValue + " ";
        this.whereNotDeleted();
        return (T)(this);
    }

    public T insert()
    {
        this.reset();
        this.query = "INSERT INTO `" + this.getTableName() + "` ";
        return (T)(this);
    }

    public T insert(String fields, String values)
    {
        this.reset();
        this.query = "INSERT INTO `" + this.getTableName() + "` ";
        this.fields(fields);
        this.values(values);
        return (T)(this);
    }

    public T fields(String fields)
    {
        this.query += "(" + fields + ") ";
        return (T)(this);
    }

    public T values(String values)
    {
        this.query += "VALUES (" + values + ") ";
        return (T)(this);
    }

    public T delete()
    {
        this.reset();
        this.query = "DELETE FROM `" + this.getTableName() + "` ";
        this.wheres = "WHERE `id`=:id ";
        return (T)(this);
    }

    public T delete(String index, String indexValue)
    {
        this.reset();
        this.query = "DELETE FROM `" + this.getTableName() + "` ";
        this.wheres = "WHERE `" + index + "`=" + indexValue + " ";
        return (T)(this);
    }

    public T touch()
    {
        this.set("updated_at", ":updated_at");
        return (T)(this);
    }

    public T softDelete()
    {
        this.set("deleted_at", ":deleted_at");
        return (T)(this);
    }

    public T set(String fieldName)
    {
        if(!this.isSet)
            this.query += "SET `" + fieldName + "`=:" + fieldName + " ";
        else
            this.query += ", `" + fieldName + "`=:" + fieldName + " ";
        this.isSet = true;
        return (T)(this);
    }

    public T set(String fieldName, String value)
    {
        if(!this.isSet)
            this.query += "SET `" + fieldName + "`=" + value + " ";
        else
            this.query += ", `" + fieldName + "`=" + value + " ";
        this.isSet = true;
        return (T)(this);
    }

    public T andSet(String fieldName, String value)
    {
        this.set(fieldName, value);
        return (T)(this);
    }

    public T where(String sql)
    {
        if(this.addWhere())
            this.wheres += sql + " ";
        else
            this.wheres += "AND " + sql + " ";

        return (T)(this);
    }

    public T where(String field, String value)
    {
        if(this.addWhere())
            this.wheres += field + "=" + value + " ";
        else
            this.wheres += "AND " + field + "=" + value + " ";

        return (T)(this);
    }

    public T where(String field, String operator, String value)
    {
        if(this.addWhere())
            this.wheres += field + operator + value + " ";
        else
            this.wheres += "AND " + field + operator + value + " ";

        return (T)(this);
    }

    public T whereNotDeleted()
    {
        if(this.addWhere())
            this.wheres += "deleted_at is null ";
        else
            this.wheres += "AND deleted_at is null ";
        return (T)this;
    }

    private boolean addWhere()
    {
        if(this.wheres.equals(""))
        {
            this.wheres = "WHERE ";
            return true;
        }
        return false;
    }

    public String getSql()
    {
        return this.query + this.wheres + this.sqls;
    }

}
