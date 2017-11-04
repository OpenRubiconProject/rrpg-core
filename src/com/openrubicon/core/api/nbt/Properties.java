package com.openrubicon.core.api.nbt;

import com.openrubicon.core.api.interfaces.Persistable;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.Map;

public class Properties implements Persistable {

    public static final String NBT_PROPERTIES = "NBT_PROPERTIES";

    private HashMap<String, String> properties = new HashMap<>();

    private String persistenceString;

    public HashMap<String, String> getProperties() {
        return properties;
    }

    public String getPersistenceString() {
        return persistenceString;
    }

    public void setPersistenceString(String persistenceString) {
        this.persistenceString = persistenceString;
    }

    public String get(String key)
    {
        if(!properties.containsKey(key))
            return null;
        return properties.get(key);
    }

    public boolean has(String key)
    {
        return properties.containsKey(key);
    }

    public void add(String key, String value)
    {
        properties.put(key, value);
    }

    @Override
    public boolean save()
    {
        String save = "";

        if(properties.size() > 0)
        {
            for(Map.Entry<String, String> property : properties.entrySet())
            {
                save += property.getKey() + ":" + property.getValue() + ",";
            }

            save = save.substring(0 , save.length() - 1);
        }

        this.setPersistenceString(save);

        return true;
    }

    @Override
    public boolean load()
    {
        //Bukkit.broadcastMessage(this.persistenceString);
        String[] arr = this.persistenceString.split(",");

        for(int i = 0; i < arr.length; i++)
        {
            String[] keyval = arr[i].split(":");
            this.properties.put(keyval[0], keyval[1]);
        }

        return true;
    }

    public void addDouble(String key, double value)
    {
        this.add(key, String.valueOf(value));
    }

    public double getDouble(String key)
    {
        return Double.parseDouble(this.properties.get(key));
    }

    public void addInteger(String key, int value)
    {
        this.add(key, String.valueOf(value));
    }

    public int getInteger(String key)
    {
        return Integer.parseInt(this.properties.get(key));
    }

    public void addFloat(String key, float value)
    {
        this.add(key, String.valueOf(value));
    }

    public float getFloat(String key)
    {
        return Float.parseFloat(this.properties.get(key));
    }

    public void addBoolean(String key, boolean value)
    {
        this.add(key, String.valueOf(value));
    }

    public boolean getBoolean(String key)
    {
        return Boolean.parseBoolean(this.properties.get(key));
    }

}
