package com.openrubicon.core;

import com.openrubicon.core.connector.ConnectorServer;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * RRPGCore
 * Rubicon RPG Core
 * Created by Shawn Clake
 * Contributors:
 *   - Quinn Bast
 */
public class RRPGCore extends JavaPlugin {

    private final boolean devMode = true;      // Dev mode
    //public static Configuration config;        // Configuration instance
    public static ConnectorServer connector;   // Connector server
    public static Permission permission;       // Vault permissions
    public static Connection database;         // Database connection
    //public static DatabaseManager dbManager;   // Database manager
    public static Plugin plugin;               // Core plugin instance

    public static ModuleManager modules;       // Module manager

    @Override
    public void onLoad()
    {
        getLogger().info("Beginning Loading Process..");

        RRPGCore.plugin = this;
        this.establishConfig();

        this.loadDatabase();
        getLogger().info("Database connected");

        MaterialGroups.initialize();
        getLogger().info("Material Groups Loaded..");
    }

    @Override
    public void onDisable()
    {
        RRPGCore.connector.shutdown();
    }

    @Override
    public void onEnable()
    {
        getLogger().info("Beginning Enabling Process..");

        //RRPGCore.config = new Configuration(devMode);
        getLogger().info("Configuration loaded.");

        //this.loadConnector(Configuration.CONNECTOR_PORT);

    }

    /**
     * Loads the Connector server.. Runs async
     * @param connectorPort int port to run the connector server on
     */
    private void loadConnector(int connectorPort) {
        getLogger().info("Establishing Connector Thread...");
        this.getServer().getScheduler().runTaskAsynchronously(this, new Runnable() {
            @Override
            public void run() {
                getLogger().info("Connector Server Starting on port " + connectorPort + "...");
                try {
                    RRPGCore.connector = new ConnectorServer(5369);
                    RRPGCore.connector.run();
                } catch (Exception e) {
                    getLogger().warning("Connector Failed while starting.");
                    e.printStackTrace();
                }
                getLogger().info("Connector Started.");
            }
        });
    }

    /**
     * Loads the configuration
     * Runs in sync as configuration values are required for future loading steps
     * DO NOT CHANGE TO RUN ASYNC.
     */
    private void establishConfig()
    {
        getLogger().info("Establishing Configuration...");
        try {
            if (!getDataFolder().exists()) {
                getDataFolder().mkdirs();
            }
            File file = new File(getDataFolder(), "config.yml");
            if (!file.exists()) {
                getLogger().info("Configuration not found, creating...");
                saveDefaultConfig();
            } else {
                getLogger().info("Configuration found, loading...");
            }
        } catch (Exception e) {
            getLogger().warning("Failed to interact with filesystem to retrieve configuration. Is this a permissions issue?");
            e.printStackTrace();

        }
        getLogger().info("Configuration Established.");
    }

    /**
     * Loads the database connection
     * Runs in async to speed up loading times and play times.
     */
    private void loadDatabase() {
        //final String username = Configuration.DB_USER;
        //final String password = Configuration.DB_PASS;
        //final String url = "jdbc:mysql://localhost:3306/" + Configuration.DB_NAME;

        getLogger().info("Establishing Database Thread...");
        this.getServer().getScheduler().runTaskAsynchronously(this, new Runnable() {

            @Override
            public void run() {

                getLogger().info("Database Connection Starting...");
                try {
                    if (RRPGCore.database != null && !RRPGCore.database.isClosed()) {
                        return;
                    }

                    synchronized (this) {
                        if (RRPGCore.database != null && !RRPGCore.database.isClosed()) {
                            return;
                        }

                        try { //We use a try catch to avoid errors, hopefully we don't get any.
                            Class.forName("com.mysql.jdbc.Driver"); //this accesses Driver in jdbc.
                        } catch (ClassNotFoundException e) {
                            getLogger().warning("JBDC Driver Unavailable. Check your system configuration.");
                            e.printStackTrace();
                            return;
                        }
                        try { //Another try catch to get any SQL errors (for example connections errors)


                            //RRPGCore.database = DriverManager.getConnection(url, username, password);
                            RRPGCore.database = DriverManager.getConnection("", "", "");


                            //with the method getConnection() from DriverManager, we're trying to set
                            //the connection's url, username, password to the variables we made earlier and
                            //trying to get a connection at the same time. JDBC allows us to do this.


                        } catch (SQLException e) { //catching errors)
                            getLogger().info("Database Connection Failed.");
                            e.printStackTrace(); //prints out SQLException errors to the console (if any)
                        }

                    }
                } catch (SQLException e){
                    getLogger().warning("A Database Problem Occurred. Check your system configuration.");
                    e.printStackTrace();
                }

                getLogger().info("Database Connection Established.");

            }

        });

    }

}
