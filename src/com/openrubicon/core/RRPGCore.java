package com.openrubicon.core;

import com.openrubicon.core.connector.ConnectorServer;
import com.openrubicon.core.database.Database;
import com.openrubicon.core.events.EventListener;
import com.openrubicon.core.events.FiveTickEvent;
import com.openrubicon.core.vault.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.ServicePriority;
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
    public static Configuration config;        // Configuration instance
    public static ConnectorServer connector;   // Connector server
    public static Permission permission;       // Vault permissions
    public static Connection database;         // Database connection
    //public static DatabaseManager dbManager;   // Database manager
    public static Plugin plugin;               // Core plugin instance

    public static ModuleManager modules;       // Module manager

    public static boolean fatalError = false;

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

        RRPGCore.config = new Configuration(devMode);
        getLogger().info("Configuration loaded.");

        this.loadConnector(Configuration.CONNECTOR_PORT);

        getServer().getPluginManager().registerEvents(new EventListener(), this);
        getLogger().info("Registered Events");


        if (!Bukkit.getPluginManager().isPluginEnabled("Vault"))
        {
            getLogger().severe(String.format("[%s] - RRPG Disabled because the Vault API has not been added.", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            RRPGCore.fatalError = true;
            Bukkit.shutdown();
        } else {
            getLogger().info("Economics has loaded and Vault has hooked in");
        }

        this.setupPermissions();
        getLogger().info("Permissions setup");

        getLogger().info("Scheduling 5 tick listener");
        this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                FiveTickEvent event = new FiveTickEvent();
                Bukkit.getPluginManager().callEvent(event);
            }
        }, 1, 5);

        getLogger().info("Scheduling configuration autosave (5 min)");
        this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                RRPGCore.config.save();
            }
        }, 6000, 6000);


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
        final String host = Configuration.DB_HOST;
        final String port = Configuration.DB_PORT;
        final String username = Configuration.DB_USER;
        final String password = Configuration.DB_PASS;
        final String name = Configuration.DB_NAME;
        //final String url = "jdbc:mysql://localhost:3306/" + Configuration.DB_NAME;

        getLogger().info("Establishing Database Thread...");
        this.getServer().getScheduler().runTaskAsynchronously(this, new Runnable() {

            @Override
            public void run() {

                getLogger().info("Database Connection Starting...");

                Database.initialize(host, port, username, password, name);

                /*try {
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


                            RRPGCore.database = DriverManager.getConnection(url, username, password);
                            //RRPGCore.database = DriverManager.getConnection("", "", "");


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
                }*/

                getLogger().info("Database Connection Established.");

            }

        });

    }

    public net.milkbowl.vault.economy.Economy setupVaultEconomy(Economy econ)
    {
        try {
            Plugin p = Bukkit.getPluginManager().getPlugin("Vault");
            if (!Bukkit.getPluginManager().isPluginEnabled("Vault"))
                return null;

            getServer().getServicesManager().register(net.milkbowl.vault.economy.Economy.class, econ, p, ServicePriority.Highest);

            getLogger().info(String.format("[Economy] %s found: %s", econ.getName(), this.isEnabled() ? "Loaded" : "Waiting"));
        } catch (Exception e) {
            getLogger().severe(String.format("[Economy] There was an error hooking %s - check to make sure you're using a compatible version!", econ.getName()));
            e.printStackTrace();
        }

        RegisteredServiceProvider<net.milkbowl.vault.economy.Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        getLogger().info(getServer().getServicesManager().toString());

        return economyProvider.getProvider();

    }

    private void setupPermissions()
    {
        RegisteredServiceProvider<Permission> permissionProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);

        if(permissionProvider == null)
        {
            getLogger().severe("Vault is missing permission provider. Ensure you have the Vault API installed.");
            return;
        }

        permission = permissionProvider.getProvider();
    }


}
