package com.openrubicon.core.api.vault.items;

import org.bukkit.Material;

public class Items extends net.milkbowl.vault.item.Items {

    public static String itemNameByType(Material material)
    {
        return net.milkbowl.vault.item.Items.itemByType(material).getName();
    }


}
