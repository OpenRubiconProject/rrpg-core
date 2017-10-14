package com.openrubicon.core;

import com.openrubicon.core.api.command.Command;
import com.openrubicon.core.api.command.CommandService;
import com.openrubicon.core.api.configuration.Configuration;
import com.openrubicon.core.api.configuration.ConfigurationProperty;
import com.openrubicon.core.api.database.interfaces.PostDatabaseLoad;
import com.openrubicon.core.api.discord.Discord;
import com.openrubicon.core.api.discord.DiscordEventTestListener;
import com.openrubicon.core.api.recipes.RecipeService;
import com.openrubicon.core.api.recipes.events.RecipeEventListener;
import com.openrubicon.core.api.reflection.Reflection;
import com.openrubicon.core.api.server.players.Players;
import com.openrubicon.core.api.server.players.interfaces.PlayerData;
import com.openrubicon.core.commands.*;
import com.openrubicon.core.commands.account.Link;
import com.openrubicon.core.commands.account.Login;
import com.openrubicon.core.commands.account.Register;
import com.openrubicon.core.api.connector.ConnectorServer;
import com.openrubicon.core.api.database.Database;
import com.openrubicon.core.api.database.DatabaseMigrator;
import com.openrubicon.core.api.database.interfaces.DatabaseModel;
import com.openrubicon.core.configuration.ConnectorPort;
import com.openrubicon.core.configuration.CooldownReductionCap;
import com.openrubicon.core.configuration.DevMode;
import com.openrubicon.core.configuration.DiscordAppToken;
import com.openrubicon.core.configuration.database.*;
import com.openrubicon.core.database.models.*;
import com.openrubicon.core.events.EventListener;
import com.openrubicon.core.events.FiveMinuteEvent;
import com.openrubicon.core.events.FiveTickEvent;
import com.openrubicon.core.events.OneTickEvent;
import com.openrubicon.core.helpers.Constants;
import com.openrubicon.core.helpers.Helpers;
import com.openrubicon.core.helpers.MaterialGroups;
import com.openrubicon.core.api.vault.economy.Economy;
import com.openrubicon.core.interfaces.Module;
import com.openrubicon.core.api.services.ServiceManager;
import com.openrubicon.core.server.playerdata.MinecraftPlayerModel;
import com.openrubicon.core.server.playerdata.PreviousLocation;
import com.openrubicon.core.server.playerdata.TopSpeed;
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
import java.util.LinkedList;

/**
 * RRPGCore
 * Rubicon RPG Core
 * Created by Shawn Clake
 * Contributors:
 *   - Quinn Bast
 *
 *   TODO:
 *      - Discord Integration (Authentication, communication each way, commands)
 *      - v Make configuration modular (Other modules should see main config)
 *      - v Make a decision on porting over durability, elements, rarity, modification (AnvilInventory)
 *      - v Build authentication API
 *      - v Port over the combat API
 *      - v Potentially port over some events other events: PlayerLandOnGround, PlayerLookingAtEntity etc.
 *      - Create an API bridge for Vault perms and chat
 *      - Make Connector do something (RESTful API which interacts real time with server)
 *      - Make a stat tracking API
 *      - v Add commands for altering configuration values and debug. Also for rebooting the Discord, Database, and Connector configurations.
 *      - Finish Database Query Builder
 *      - Finish HorseInventory
 *      - v Move Enums to better directories
 *      - Create additional extension classes like ExampleEconomy
 *      - Document each class and build a JavaDoc
 *      - Add system for tracking user display name changes, last played time, and total time played
 *      - Add optional register minecraft server with centralized Rubicon system. This will provide a unique server ID and token
 *      - Add system for pirate protection
 *      - Build server API which Discord, Connector, Modules, and Web can hook into. Shows stats like current/max players, whos online, etc.
 *      - Build management API which Discord, Connector, Modules, and Web can hook into
 *      - (Main ones done) Create event hooks for triggering the custom events
 *      - v Database Migrations versioning table
 *
 *
 */
public class RRPGCore extends JavaPlugin implements Module {

    public static Configuration config;        // Configuration instance
    public static ConnectorServer connector;   // Connector server
    public static Discord discord;             // Discord server
    public static Permission permission;       // Vault permissions
    public static Chat chat;                   // Vault Chat
    public static Database database;           // Database connection
    public static Plugin plugin;               // Core plugin instance

    public static ServiceManager services;     // Service Manager

    public static Reflection reflection;       // Reflection API

    public static ModuleManager modules;       // Module manager

    public static Players players;             // Players

    public static boolean fatalError = false;  // Loading fatal error flag

    public static boolean doneLoading = false;  // Done loading

    @Override
    public ArrayList<DatabaseModel> getDatabaseModels() {
        ArrayList<DatabaseModel> models = new ArrayList<>();
        models.add(new DiscordTextChannel());
        //models.add(new Player());
        models.add(new Account());
        models.add(new DiscordAccount());
        models.add(new MinecraftPlayer());
        return models;
    }

    @Override
    public ArrayList<Command> getCommands() {
        ArrayList<Command> commands = new ArrayList<>();
        commands.add(new DiscordRestart());
        commands.add(new ConnectorRestart());
        commands.add(new ConfigGet());
        commands.add(new ConfigSet());
        commands.add(new RRPG());
        commands.add(new Register());
        commands.add(new Login());
        commands.add(new Link());
        commands.add(new TestCommand());
        return commands;
    }

    @Override
    public ArrayList<PostDatabaseLoad> getPostDatabaseLoads() {
        ArrayList<PostDatabaseLoad> loads = new ArrayList<>();
        loads.add(new DatabaseMigrator(RRPGCore.modules.getDatabaseModels()));
        loads.add(RRPGCore.players);
        return loads;
    }

    @Override
    public LinkedList<ConfigurationProperty> getConfigurationProperties() {
        LinkedList<ConfigurationProperty> properties = new LinkedList<>();
        properties.add(new DatabaseHost());
        properties.add(new DatabaseName());
        properties.add(new DatabasePassword());
        properties.add(new DatabasePort());
        properties.add(new DatabaseUsername());
        properties.add(new ConnectorPort());
        properties.add(new CooldownReductionCap());
        properties.add(new DevMode());
        properties.add(new DiscordAppToken());
        return properties;
    }

    @Override
    public ArrayList<PlayerData> getPlayerDatas() {
        ArrayList<PlayerData> playerDatas = new ArrayList<>();
        playerDatas.add(new MinecraftPlayerModel());
        playerDatas.add(new PreviousLocation());
        playerDatas.add(new TopSpeed());
        return playerDatas;
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
        getLogger().info(Helpers.colorize(Constants.PRIMARY_COLOR + "Beginning Loading Process.."));

        RRPGCore.plugin = this;

        MaterialGroups.initialize();
        getLogger().info("Established Material Groups..");

        RRPGCore.reflection = new Reflection();
        getLogger().info("Established Reflection API.");

        RRPGCore.services = new ServiceManager();
        getLogger().info("Established Service Manager.");

        RRPGCore.modules = new ModuleManager();
        getLogger().info("Established Module Provider.");

        RRPGCore.modules.addModule(this);
    }

    @Override
    public void onDisable()
    {
        RRPGCore.connector.shutdown();
        RRPGCore.discord.shutdown();
        RRPGCore.doneLoading = false;
    }

    @Override
    public void onEnable()
    {
        getLogger().info("Beginning Enabling Process..");

        this.establishConfig();
        RRPGCore.config = new Configuration(this.getConfig());
        RRPGCore.config.add(RRPGCore.modules.getConfigurationProperties());
        RRPGCore.config.load();
        getLogger().info("Configuration loaded.");

        this.loadDatabase();

        RRPGCore.players = new Players();

        this.loadConnector((int)config.get(ConnectorPort.class).getProperty());

        this.loadDiscord((String)config.get(DiscordAppToken.class).getProperty());

        this.loadServices();

        getServer().getPluginManager().registerEvents(new EventListener(), this);
        getLogger().info("Established Core Event Handler.");

        getServer().getPluginManager().registerEvents(new RecipeEventListener(), this);
        getLogger().info("Established Recipe Event Handler.");

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

        getLogger().info("Scheduling 1 Tick Event");
        this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                OneTickEvent event = new OneTickEvent();
                Bukkit.getPluginManager().callEvent(event);
            }
        }, 1, 1);

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
                saveConfig();

                FiveMinuteEvent event = new FiveMinuteEvent();
                Bukkit.getPluginManager().callEvent(event);
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

                RRPGCore.connector = new ConnectorServer(5369);
                RRPGCore.connector.start();

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
                this.saveDefaultConfig();
                Configuration config = new Configuration(this.getConfig());
                config.add(RRPGCore.modules.getConfigurationProperties());
                config.initializeConfigFile();
                this.saveConfig();
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
        final String host = (String)config.get(DatabaseHost.class).getProperty();
        final String port = (String)config.get(DatabasePort.class).getProperty();
        final String username = (String)config.get(DatabaseUsername.class).getProperty();
        final String password = (String)config.get(DatabasePassword.class).getProperty();
        final String name = (String)config.get(DatabaseName.class).getProperty();

        getLogger().info("Establishing Database Thread...");
        this.getServer().getScheduler().runTaskAsynchronously(this, new Runnable() {

            @Override
            public void run() {

                getLogger().info("Database Connection Starting...");
                Database.initialize(host, port, username, password, name);

                RRPGCore.database = new Database();

                if(Database.connection().get() == null)
                {
                    getLogger().warning("===========================");
                    getLogger().warning("Database Connection Failed.");
                    getLogger().warning("===========================");
                    RRPGCore.database.setLoaded(false);
                } else {
                    getLogger().info("Database Connection Established.");
                    RRPGCore.database.setLoaded(true);
                }

                for(PostDatabaseLoad load : RRPGCore.modules.getPostDatabaseLoads())
                {
                    load.run();
                }

                //getLogger().info("Running Database Migrations if Any Exist..");
                //int count = new DatabaseMigrator(RRPGCore.modules.getDatabaseModels()).up(RRPGCore.database.connection());
                //getLogger().info("Completed migrating " + count + " database migrations.");

                for(DiscordTextChannel channel : DiscordTextChannel.getChannels(RRPGCore.database.connection()))
                {
                    MessageChannel ch = Discord.getApi().getTextChannelById(channel.getChannel_id());
                    DiscordEventTestListener.channels.add(ch);
                }

                RRPGCore.doneLoading = true;

            }

        });

    }

    private void loadDiscord(String token)
    {
        RRPGCore.discord = new Discord(token);
    }

    private void loadServices()
    {
        getLogger().info("Establishing Services..");

        RRPGCore.services.create(new CommandService(this, RRPGCore.modules.getCommands()));
        RRPGCore.services.create(new RecipeService(RRPGCore.modules.getRecipes()));

        getLogger().info("Established Services.");
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
