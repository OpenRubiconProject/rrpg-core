package com.openrubicon.core.api.configuration;

import com.openrubicon.core.api.interfaces.Persistable;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.LinkedHashMap;
import java.util.LinkedList;

public class Configuration implements Persistable {

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


    /**  */


    private LinkedHashMap<String, ConfigurationProperty> properties = new LinkedHashMap<>();
    public FileConfiguration config;

    public Configuration(FileConfiguration config) {
        this.config = config;
    }

    @Override
    public boolean save()
    {
        for(ConfigurationProperty property : this.properties.values())
        {
            if(!property.isSaved())
                continue;

            this.config.set(property.getKey(), property.getProperty());
        }

        return true;
    }

    @Override
    public boolean load()
    {
        for(ConfigurationProperty property : this.properties.values())
        {
            property.setProperty(this.config.get(property.getKey(), property.getDefaultValue()));
        }

        return true;
    }

    public void initializeConfigFile()
    {
        for(ConfigurationProperty property : this.properties.values())
        {
            this.config.set(property.getKey(), property.getDefaultValue());
        }
    }

    public void add(ConfigurationProperty property)
    {
        this.properties.put(property.getKey(), property);
    }

    public void add(LinkedList<ConfigurationProperty> properties)
    {
        for(ConfigurationProperty property : properties)
        {
            this.properties.put(property.getKey(), property);
        }
    }

    public ConfigurationProperty get(String key)
    {
        return this.properties.get(key);
    }

    public ConfigurationProperty get(Class object)
    {
        for(ConfigurationProperty property : this.properties.values())
        {
            if(property.getClass() == object)
                return property;
        }

        return null;
    }

}
