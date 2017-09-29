package com.openrubicon.core.api.reflection;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * @author Michel_0
 * @author Shawn Clake
 */
public class Reflection {

    private String version;
    private boolean enabled;

    public Reflection() {
        Bukkit.getLogger().info("Establishing Reflection...");

        this.version = Bukkit.getServer().getClass().getName().split("\\.")[3];

        try {
            Class.forName("net.minecraft.server." + this.version + "." + Player.class);
            Bukkit.getLogger().info("Reflection enabled.");
            this.enabled = true;
        } catch (ClassNotFoundException e) {
            Bukkit.getLogger().warning("Cannot load Reflection due to versioning limitations. Contact RRPG to have this error resolved. Disabling Reflection Features..");
            this.enabled = false;
        }
    }

    public String getVersion() {
        return this.version;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    /**
     * Get a class out of net.minecraft.server.vX_X_RX
     *
     * @param name Name of the class
     * @return The class itself
     */
    public Class<?> getNMSClass (String name) {
        try {
            return Class.forName("net.minecraft.server." + this.version + "." + name);
        } catch (ClassNotFoundException e) {
            Bukkit.getLogger().info("[Reflection] Can't find NMS Class! (" + "net.minecraft.server." + this.version + "." + name + ")");
            return null;
        }
    }
    /**
     * Get a class out of org.bukkit.craftbukkit.vX_X_RX
     *
     * @param name Name of the class
     * @return The class itself
     */
    public Class<?> getCBClass(String name) {
        try {
            return Class.forName("org.bukkit.craftbukkit." + this.version + "." + name);
        } catch (ClassNotFoundException e) {
            Bukkit.getLogger().info("[Reflection] Can't find CB Class! (" + "org.bukkit.craftbukkit." + this.version + "." + name + ")");
            return null;
        }
    }
}
