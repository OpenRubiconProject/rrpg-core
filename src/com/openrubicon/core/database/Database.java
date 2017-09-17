package com.openrubicon.core.database;

import org.sql2o.Sql2o;

public class Database {

    private static Sql2o sql;

    private static String username = "";
    private static String password = "";
    private static String name = "";

    private static String host = "localhost";
    private static String port = "3306";

    public static void initialize(String host, String port, String username, String password, String name) {
        sql = new Sql2o("jdbc:mysql://" + host + ":" + port + "/" + name, username, password);
    }

    public static void initialize(String username, String password, String name) {
        sql = new Sql2o("jdbc:mysql://" + host + ":" + port + "/" + name, username, password);
    }

    public static void initialize() {
        sql = new Sql2o("jdbc:mysql://" + host + ":" + port + "/" + name, username, password);
    }
}
