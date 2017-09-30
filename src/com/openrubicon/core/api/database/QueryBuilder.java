package com.openrubicon.core.api.database;

abstract public class QueryBuilder<T> {

    private String query;

    private String wheres = "";
    private String sqls = "";

    abstract protected String getTableName();

    public T sql(String sql)
    {
        this.sqls += sql;
        return (T)(this);
    }

    public T select(String select)
    {
        this.query = "SELECT " + select + " FROM `" + this.getTableName() + "` ";
        return (T)(this);
    }

    public T selectAll()
    {
        this.query = "SELECT * FROM `" + this.getTableName() + "` ";
        return (T)(this);
    }

    public T count(String field)
    {
        this.query = "SELECT count(" + field + ") FROM `" + this.getTableName() + "` ";
        return (T)(this);
    }

    public T update()
    {
        this.query = "UPDATE `" + this.getTableName() + "` ";
        return (T)(this);
    }

    public T insert()
    {
        this.query = "INSERT INTO `" + this.getTableName() + "` ";
        return (T)(this);
    }

    public T insert(String fields, String values)
    {
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
        this.query = "DELETE FROM `" + this.getTableName() + "` ";
        return (T)(this);
    }

    public T set(String fieldName, String value)
    {
        this.query += "SET `" + fieldName + "`=" + value + " ";
        return (T)(this);
    }

    public T andSet(String fieldName, String value)
    {
        this.query += ", `" + fieldName + "`=" + value + " ";
        return (T)(this);
    }

    public T where(String sql)
    {
        this.addWhere();
        this.wheres += sql + " ";
        return (T)(this);
    }

    public T whereNotDeleted()
    {
        this.addWhere();
        this.wheres += "deleted_at is null ";
        return (T)this;
    }

    private void addWhere()
    {
        if(this.wheres.equals(""))
        {
            this.wheres += "WHERE ";
        }
    }

    public String getSql()
    {
        return this.query + this.wheres + this.sqls;
    }

}
