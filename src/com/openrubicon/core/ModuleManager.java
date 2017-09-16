package com.openrubicon.core;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * RRPG Core
 * Rubicon RPG Core
 * Created by Shawn Clake
 * Contributors:
 *   - Quinn Bast
 *
 * ModuleManager
 * Manages RRPG modules from 1st and 3rd party providers
 */
public class ModuleManager {

    private LinkedHashMap<String, JavaPlugin> modules = new LinkedHashMap<>();

    /**
     * Returns added modules
     * @return Modules
     */
    public HashMap<String, JavaPlugin> getModules() {
        return modules;
    }

    /**
     * Sets the internal module hashmap
     * @param modules LinkedHashMap of modules
     */
    public void setModules(LinkedHashMap<String, JavaPlugin> modules) {
        this.modules = modules;
    }

    /**
     * Adds a module to the internal hashmap if it conforms to the module standard
     * @param module JavaPlugin to add
     */
    public void addModule(JavaPlugin module)
    {
        if(!(module instanceof iModule))
        {
            Bukkit.getLogger().warning("Module " + module.getName() + " was not loaded because it does not conform to iModule. Please report this to the module's plugin developer.");
            return;
        }

        if(this.modules.containsKey(((iModule)module).getKey()))
        {
            Bukkit.getLogger().warning("Module " + module.getName() + " was not loaded because it conflicts with an existing module. Please report this to the module's plugin developer.");
            return;
        }

        this.modules.put(((iModule)module).getKey(), module);
        Bukkit.getLogger().warning("Module " + module.getName() + " was added.");
    }
}
