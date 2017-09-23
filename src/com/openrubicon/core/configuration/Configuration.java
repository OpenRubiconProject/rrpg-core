package com.openrubicon.core.configuration;

import com.openrubicon.core.RRPGCore;
import org.bukkit.configuration.file.FileConfiguration;

public class Configuration {
    public static float GENERATION_SPREAD = 1.2f; // Exponential growth. 1.0f - 2.0f is the valid range. Going larger may result in many combinations of items not ever generated.
    public static int SOCKET_ORB_SPAWN_CHANCE = 128; // (1 / SOCKET_ORB_SPAWN_CHANCE) * 100 = % chance of spawning an orb when breaking potatoes with special item.
    public static float ITEM_GENERATION_COST = 50.0f;
    public static float BASE_COST_MODIFIER = 1.2f;
    public static int COOLDOWN_REDUCTION_CAP = 75;

    public static String PRIMARY_COLOR = "&2";
    public static String SECONDARY_COLOR = "&a";
    public static String TERTIARY_COLOR = "&b";

    public static String HEADING_COLOR = "&6";

    public static String MYSTIC_PRIMARY_COLOR = "&d";
    public static String MYSTIC_SECONDARY_COLOR = "&5";

    public static String BOLD = "&l";
    public static String RESET_FORMAT = "&r";

    public static String RED = "&c";
    public static String YELLOW = "&e";
    public static String GREEN = "&a";

    public static boolean devMode = false;

    public static long LATEST_ITEM_ID = 0;

    public static String DB_HOST = "localhost";
    public static String DB_PORT = "3306";
    public static String DB_USER = "";
    public static String DB_PASS = "";
    public static String DB_NAME = "";

    public static String DISCORD_APP_TOKEN = "";

    public static int CONNECTOR_PORT = 5369;

    public static FileConfiguration config = null;
    private boolean configBuildChanged  = false;

    public Configuration()
    {
        Configuration.devMode = false;
        this.initialize();
    }

    public Configuration(boolean devMode)
    {
        Configuration.devMode = devMode;

        this.initialize();
    }

    public void save()
    {
        Configuration.config.set("latest-item-id", Configuration.LATEST_ITEM_ID);
        RRPGCore.plugin.saveConfig();
    }

    private void initialize()
    {
        Configuration.config = RRPGCore.plugin.getConfig();

        /*Configuration.LATEST_ITEM_ID = Configuration.config.getLong("latest-item-id", 0);

        if(Configuration.LATEST_ITEM_ID == 0)
        {
            Configuration.config.set("latest-item-id", 0);
        }*/

        //Economics.plugin.saveConfig();

        Configuration.LATEST_ITEM_ID = this.get("latest-item-id", LATEST_ITEM_ID);
        Configuration.DB_HOST = this.get("db-host", DB_HOST);
        Configuration.DB_PORT = this.get("db-port", DB_PORT);
        Configuration.DB_USER = this.get("db-username", DB_USER);
        Configuration.DB_PASS = this.get("db-password", DB_PASS);
        Configuration.DB_NAME = this.get("db-name", DB_NAME);
        Configuration.CONNECTOR_PORT = this.get("connector-port", CONNECTOR_PORT);
        Configuration.DISCORD_APP_TOKEN = this.get("discord-app-token", DISCORD_APP_TOKEN);

        // In order to add the entries to the config if it hasn't been built before
        if(this.configBuildChanged)
        {
            RRPGCore.plugin.saveConfig();
            this.configBuildChanged = false;
        }


        if(!Configuration.devMode)
            return;

        /*
         * Dev mode configuration values
         */
        Configuration.GENERATION_SPREAD = 1.0f;
        Configuration.SOCKET_ORB_SPAWN_CHANCE = 3;
        Configuration.ITEM_GENERATION_COST = 0.0f;
        Configuration.BASE_COST_MODIFIER = 1.0f;
    }

    @SuppressWarnings("unchecked")
    private <T> T get(String key, T defaultValue)
    {
        T val = (T)(Configuration.config.get(key, defaultValue));
        if(!Configuration.config.contains(key))
        {
            Configuration.config.set(key, val);
            this.configBuildChanged = true;
        }
        return val;
    }

    private String get(String key, String defaultValue)
    {
        String val = Configuration.config.getString(key, defaultValue);
        if(!Configuration.config.contains(key))
        {
            Configuration.config.set(key, val);
            this.configBuildChanged = true;
        }
        return val;
    }

    private int get(String key, int defaultValue)
    {
        int val = Configuration.config.getInt(key, defaultValue);
        if(!Configuration.config.contains(key))
        {
            Configuration.config.set(key, val);
            this.configBuildChanged = true;
        }
        return val;
    }

    private long get(String key, long defaultValue)
    {
        long val = Configuration.config.getLong(key, defaultValue);
        if(!Configuration.config.contains(key))
        {
            Configuration.config.set(key, val);
            this.configBuildChanged = true;
        }
        return val;
    }

    public static long getNextLatestItemId()
    {
        long latest = Configuration.LATEST_ITEM_ID;
        Configuration.LATEST_ITEM_ID++;
        return latest;
    }
}
