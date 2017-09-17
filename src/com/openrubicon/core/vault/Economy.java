package com.openrubicon.core.vault;

import net.milkbowl.vault.economy.AbstractEconomy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

abstract public class Economy extends AbstractEconomy {

    private Plugin plugin = null;

    public Economy(Plugin plugin) {
        plugin.getLogger().info("Establishing Economy " + this.getName() + "..");

        Plugin p = Bukkit.getPluginManager().getPlugin("Vault");
        if (Bukkit.getPluginManager().isPluginEnabled("Vault")) {
            Bukkit.getLogger().info("Vault found.");
        } else {
            //Bukkit.getLogger().info("Vault is not on " + Bukkit.getPluginManager().getPlugins()[0]);
            //Bukkit.getLogger().info("Vault is not on " + Bukkit.getPluginManager().getPlugins()[1]);
            Bukkit.getLogger().info("Unable to find Vault. Ensure you have the Vault API Plugin.");
        }
        this.plugin = plugin;
    }

}
