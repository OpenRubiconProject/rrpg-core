package com.openrubicon.core.api.database;

import org.sql2o.Sql2o;

public class Database {

    private static Connection connection;

    private static String username = "";
    private static String password = "";
    private static String name = "";

    private static String host = "localhost";
    private static String port = "3306";

    private boolean loaded = false;

    public boolean isLoaded() {
        return loaded;
    }

    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }

    public static void initialize(String host, String port, String username, String password, String name) {
        connection = new Connection(new Sql2o("jdbc:mysql://" + host + ":" + port + "/" + name, username, password));
    }

    public static void initialize(String username, String password, String name) {
        connection = new Connection(new Sql2o("jdbc:mysql://" + host + ":" + port + "/" + name, username, password));
    }

    public static void initialize() {
        connection = new Connection(new Sql2o("jdbc:mysql://" + host + ":" + port + "/" + name, username, password));
    }

    public static Connection connection()
    {
        return connection;
    }

}
