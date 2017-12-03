package com.openrubicon.core.api.reflection;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author Michel_0
 * @author Shawn Clake
 */
public class Reflection {

    private String version;
    private boolean enabled;

    public Reflection() {
        Bukkit.getLogger().info("Establishing Reflection...");

        this.version = getVersion();


        Class test = Reflection.getOBCClass("inventory.CraftInventory");
        if(test != null)
        {
            Bukkit.getLogger().info("Reflection enabled.");
            this.enabled = true;
        } else {
            Bukkit.getLogger().warning("Cannot load Reflection due to versioning limitations. Contact RRPG to have this error resolved. Disabling Reflection Features..");
            this.enabled = false;
        }

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
    /*public Class<?> getNMSClass (String name) {
        try {
            return Class.forName("net.minecraft.server." + this.version + "." + name);
        } catch (ClassNotFoundException e) {
            Bukkit.getLogger().info("[Reflection] Can't find NMS Class! (" + "net.minecraft.server." + this.version + "." + name + ")");
            return null;
        }
    }*/
    /**
     * Get a class out of org.bukkit.craftbukkit.vX_X_RX
     *
     * @param name Name of the class
     * @return The class itself
     */
    /*public Class<?> getCBClass(String name) {
        try {
            return Class.forName("org.bukkit.craftbukkit." + this.version + "." + name);
        } catch (ClassNotFoundException e) {
            Bukkit.getLogger().info("[Reflection] Can't find CB Class! (" + "org.bukkit.craftbukkit." + this.version + "." + name + ")");
            return null;
        }
    }*/


    /**
     * Everything below this point was created by
     * @author InventivetalentDev
     * @link https://github.com/InventivetalentDev/MenuBuilder/blob/master/src/main/java/org/inventivetalent/menubuilder/util/Reflection.java
     *
     * With modifications by
     * @author Shawn Clake
     */

    public static String getVersion() {
        String name = Bukkit.getServer().getClass().getPackage().getName();
        String version = name.substring(name.lastIndexOf('.') + 1) + ".";
        return version;
    }

    public static Class<?> getNMSClass(String className) {
        String fullName = "net.minecraft.server." + getVersion() + className;
        Class<?> clazz = null;
        try {
            clazz = Class.forName(fullName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clazz;
    }

    public static Class<?> getOBCClass(String className) {
        String fullName = "org.bukkit.craftbukkit." + getVersion() + className;
        Class<?> clazz = null;
        try {
            clazz = Class.forName(fullName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clazz;
    }

    public static Class<?> getOBCClass(String className, String subPackage) {
        String fullName = "org.bukkit.craftbukkit." + getVersion() + subPackage + "." + className;
        Class<?> clazz = null;
        try {
            clazz = Class.forName(fullName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clazz;
    }

    public static Object getHandle(Object obj) {
        try {
            return getMethod(obj.getClass(), "getHandle", new Class[0]).invoke(obj, new Object[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Field getField(Class<?> clazz, String name) {
        try {
            Field field = clazz.getDeclaredField(name);
            field.setAccessible(true);
            return field;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Method getMethod(Class<?> clazz, String name, Class<?>... args) {
        for (Method m : clazz.getMethods()) {
            if (m.getName().equals(name) && (args.length == 0 || ClassListEqual(args, m.getParameterTypes()))) {
                m.setAccessible(true);
                return m;
            }
        }
        return null;
    }

    public static boolean ClassListEqual(Class<?>[] l1, Class<?>[] l2) {
        boolean equal = true;
        if (l1.length != l2.length) { return false; }
        for (int i = 0; i < l1.length; i++) {
            if (l1[i] != l2[i]) {
                equal = false;
                break;
            }
        }
        return equal;
    }
}
