package com.openrubicon.core.api.vault.economy;

import net.milkbowl.vault.economy.AbstractEconomy;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.Plugin;

abstract public class Economy extends AbstractEconomy {

    private Plugin plugin = null;

    public Economy(Plugin plugin) {
        plugin.getLogger().info("Establishing Economy " + this.getName() + "..");

        Plugin p = Bukkit.getPluginManager().getPlugin("Vault");
        if (Bukkit.getPluginManager().isPluginEnabled("Vault")) {
            Bukkit.getLogger().info("Vault found.");
        } else {
            Bukkit.getLogger().info("Unable to find Vault. Ensure you have the Vault API Plugin.");
        }
        this.plugin = plugin;
    }

    @Override
    public abstract boolean createPlayerAccount(OfflinePlayer player);
    @Override
    public abstract EconomyResponse withdrawPlayer(OfflinePlayer player, double amount);
    @Override
    public abstract EconomyResponse depositPlayer(OfflinePlayer player, double amount);
    @Override
    public abstract double getBalance(OfflinePlayer player);
    @Override
    public abstract boolean has(OfflinePlayer player, double amount);
    @Override
    public abstract boolean hasAccount(OfflinePlayer player);

    /**
     * Creates an account for a player in the specified world.
     * @param player
     * @param worldName
     * @return
     */
    @Override
    public boolean createPlayerAccount(OfflinePlayer player, String worldName) {
        return createPlayerAccount(player);
    }

    public EconomyResponse createBank(String name, String player) {
        return EconomyResponse.NotImplemented();
    }

    public EconomyResponse deleteBank(String name) {
        return EconomyResponse.NotImplemented();
    }

    public EconomyResponse bankHas(String name, double amount) {
        return EconomyResponse.NotImplemented();
    }

    public EconomyResponse bankWithdraw(String name, double amount) {
        return EconomyResponse.NotImplemented();
    }

    public EconomyResponse bankDeposit(String name, double amount) {
        return EconomyResponse.NotImplemented();
    }

    public EconomyResponse isBankOwner(String name, String playerName) {
        return EconomyResponse.NotImplemented();
    }

    public EconomyResponse isBankMember(String name, String playerName) {
        return EconomyResponse.NotImplemented();
    }

    public EconomyResponse bankBalance(String name) {
        return EconomyResponse.NotImplemented();
    }

    /**
     * DON'T USE THIS FUNCTION
     * @param s
     * @param v
     * @return
     */
    @Override
    public EconomyResponse depositPlayer(String s, double v) {
        return EconomyResponse.NotImplemented();
    }

    /**
     * FUNCTION SIGNATURE FOR VAULT INHERITANCE ONLY
     * @param s
     * @param s1
     * @param v
     * @return
     */
    @Override
    public EconomyResponse withdrawPlayer(String s, String s1, double v) {
        return EconomyResponse.NotImplemented();
    }
    /**
     * FUNCTION SIGNATURE FOR VAULT INHERITANCE ONLY
     * @param s
     * @param v
     * @return
     */
    @Override
    public EconomyResponse withdrawPlayer(String s, double v) {
        return EconomyResponse.NotImplemented();
    }

    @Override
    public net.milkbowl.vault.economy.EconomyResponse depositPlayer(OfflinePlayer player, String worldName, double amount) {
        return this.depositPlayer(player, amount);
    }

    @Override
    public net.milkbowl.vault.economy.EconomyResponse withdrawPlayer(OfflinePlayer player, String worldName, double amount) {
        return this.withdrawPlayer(player, amount);
    }

    @Override
    public net.milkbowl.vault.economy.EconomyResponse depositPlayer(String s, String s1, double v) {
        return null;
    }

    /**
     * DONT USE THIS FUNCTION
     * @param s
     * @return
     */
    @Override
    public double getBalance(String s) {
        return 0;
    }

    /**
     * DONT USE THIS FUNCTION
     * @param player
     * @return
     */
    @Override
    public boolean hasAccount(String player)
    {
        return false;
    }

    /**
     * DONT USE THIS FUNCTION
     * @param player
     * @param worldName
     * @return
     */
    @Override
    public boolean hasAccount(String player, String worldName)
    {
        return false;
    }

    /**
     * DONT USE THIS FUNCTION
     * @param s
     * @param s1
     * @return
     */
    @Override
    public boolean createPlayerAccount(String s, String s1) {
        return false;
    }

    /**
     * FUNCTION SIGNATURE FOR VAULT INHERITANCE ONLY
     * @param s
     * @param s1
     * @param v
     * @return
     */
    @Override
    public boolean has(String s, String s1, double v) {
        return false;
    }

    /**
     * FUNCTION SIGNATURE FOR VAULT INHERITANCE ONLY
     * @param s
     * @param v
     * @return
     */
    @Override
    public boolean has(String s, double v) {
        return false;
    }

    /**
     * DO NOT USE THIS FUNCTION
     * @param s
     * @param s1
     * @return
     */
    @Override
    public double getBalance(String s, String s1) {
        return 0;
    }

    @Override
    public boolean createPlayerAccount(String s) {
        return false;
    }
}
