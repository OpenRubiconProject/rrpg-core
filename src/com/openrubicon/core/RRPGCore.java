package com.openrubicon.core;

import com.openrubicon.core.api.discord.Discord;
import com.openrubicon.core.api.discord.DiscordEventTestListener;
import com.openrubicon.core.configuration.Configuration;
import com.openrubicon.core.connector.ConnectorServer;
import com.openrubicon.core.database.Database;
import com.openrubicon.core.database.DatabaseMigrator;
import com.openrubicon.core.database.interfaces.DatabaseModel;
import com.openrubicon.core.database.models.DiscordTextChannel;
import com.openrubicon.core.database.models.Player;
import com.openrubicon.core.events.EventListener;
import com.openrubicon.core.events.FiveTickEvent;
import com.openrubicon.core.helpers.Helpers;
import com.openrubicon.core.helpers.MaterialGroups;
import com.openrubicon.core.api.vault.economy.Economy;
import com.openrubicon.core.interfaces.Module;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;

/**
 * RRPGCore
 * Rubicon RPG Core
 * Created by Shawn Clake
 * Contributors:
 *   - Quinn Bast
 */
public class RRPGCore extends JavaPlugin implements Module {

    private final boolean devMode = true;      // Dev mode
    public static Configuration config;        // Configuration instance
    public static ConnectorServer connector;   // Connector server
    public static Discord discord;             // Discord server
    public static Permission permission;       // Vault permissions
    public static Chat chat;                   // Vault Chat
    public static Database database;           // Database connection
    //public static DatabaseManager dbManager; // Database manager
    public static Plugin plugin;               // Core plugin instance

    public static ModuleManager modules;       // Module manager

    public static boolean fatalError = false;

    @Override
    public ArrayList<DatabaseModel> getDatabaseModels() {
        ArrayList<DatabaseModel> models = new ArrayList<>();
        models.add(new DiscordTextChannel());
        models.add(new Player());
        return models;
    }

    @Override
    public String getKey() {
        return "rrpg-core";
    }

    @Override
    public String getOverview() {
        return "The core of RRPG";
    }

    @Override
    public String getConfiguration() {
        return this.getDataFolder().getAbsolutePath();
    }

    @Override
    public void onLoad()
    {
        getLogger().info(Helpers.colorize(Configuration.PRIMARY_COLOR + "Beginning Loading Process.."));

        RRPGCore.plugin = this;
        this.establishConfig();

        MaterialGroups.initialize();
        getLogger().info("Material Groups Loaded..");

        RRPGCore.modules = new ModuleManager();
        getLogger().info("Established Module Provider.");

        RRPGCore.modules.addModule(this);
    }

    @Override
    public void onDisable()
    {
        RRPGCore.connector.shutdown();
        RRPGCore.discord.shutdown();
    }

    @Override
    public void onEnable()
    {
        getLogger().info("Beginning Enabling Process..");

        RRPGCore.config = new Configuration(devMode);
        getLogger().info("Configuration loaded.");

        this.loadDatabase();
        //getLogger().info("Database connected");

        this.loadConnector(Configuration.CONNECTOR_PORT);

        this.loadDiscord(Configuration.DISCORD_APP_TOKEN);


        getServer().getPluginManager().registerEvents(new EventListener(), this);
        getLogger().info("Established Event Handler.");


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
        getLogger().info("Established Permissions");

        this.setupChat();
        getLogger().info("Established Chat");

        getLogger().info("Scheduling 5 Tick Event");
        this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                FiveTickEvent event = new FiveTickEvent();
                Bukkit.getPluginManager().callEvent(event);
            }
        }, 1, 5);

        getLogger().info("Scheduling Configuration Autosave (5 min)");
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

                RRPGCore.database = new Database();



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

                RRPGCore.database.setLoaded(true);

                getLogger().info("Completing Database Migrations if Any Exist..");
                int count = new DatabaseMigrator(RRPGCore.modules.getDatabaseModels()).up(RRPGCore.database.connection());
                getLogger().info("Completed migrating " + count + " database migrations.");

                for(DiscordTextChannel channel : DiscordTextChannel.getChannels(RRPGCore.database.connection().get()))
                {
                    MessageChannel ch = Discord.getApi().getTextChannelById(channel.getChannel_id());
                    DiscordEventTestListener.channels.add(ch);
                }

            }

        });

    }

    private void loadDiscord(String token)
    {
        RRPGCore.discord = new Discord(token);
    }

    public net.milkbowl.vault.economy.Economy registerEconomy(Economy econ)
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

    private void setupChat()
    {
        RegisteredServiceProvider<Chat> chatProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.chat.Chat.class);

        if(chatProvider == null)
        {
            getLogger().severe("Vault is missing chat provider. Ensure you have the Vault API installed.");
            return;
        }

        chat = chatProvider.getProvider();
    }


}
