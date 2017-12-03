package com.openrubicon.core.api.menu.reflection;

import com.openrubicon.core.api.reflection.AccessUtil;
import com.openrubicon.core.api.reflection.Reflection;
import org.bukkit.inventory.Inventory;

/**
 * @author InventivetalentDev
 * @link https://github.com/InventivetalentDev/MenuBuilder/blob/master/src/main/java/org/inventivetalent/menubuilder/inventory/InventoryHelper.java
 */
public class InventoryHelper {
    static Class<?> obcCraftInventory;
    static Class<?> obcCraftInventoryCustom;
    static Class<?> obcMinecraftInventory;

    static {
        try {
            obcCraftInventory = Reflection.getOBCClass("inventory.CraftInventory");
            obcCraftInventoryCustom = Reflection.getOBCClass("inventory.CraftInventoryCustom");
            for (Class<?> c : obcCraftInventoryCustom.getDeclaredClasses()) {
                if (c.getSimpleName().equals("MinecraftInventory")) {
                    obcMinecraftInventory = c;
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void changeTitle(Inventory inv, String title) {
        try {
            Object minecrafInventory = AccessUtil.setAccessible(obcCraftInventory.getDeclaredField("inventory")).get(inv);
            AccessUtil.setAccessible(obcMinecraftInventory.getDeclaredField("title")).set(minecrafInventory, title);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
