package com.openrubicon.core.helpers;

import org.bukkit.Material;

import java.util.Arrays;
import java.util.HashSet;

public class MaterialGroups {

    public static final HashSet<Material> DIAMOND_TOOLS = new HashSet<>(Arrays.asList(Material.DIAMOND_AXE, Material.DIAMOND_PICKAXE, Material.DIAMOND_SWORD, Material.DIAMOND_SPADE, Material.DIAMOND_HOE));
    public static final HashSet<Material> DIAMOND_ARMOR = new HashSet<>(Arrays.asList(Material.DIAMOND_CHESTPLATE, Material.DIAMOND_HELMET, Material.DIAMOND_LEGGINGS, Material.DIAMOND_BOOTS));

    public static final HashSet<Material> DIAMOND = new HashSet<>();

    public static final HashSet<Material> GOLD_TOOLS = new HashSet<>(Arrays.asList(Material.GOLD_AXE, Material.GOLD_PICKAXE, Material.GOLD_SWORD, Material.GOLD_SPADE, Material.GOLD_HOE));
    public static final HashSet<Material> GOLD_ARMOR = new HashSet<>(Arrays.asList(Material.GOLD_CHESTPLATE, Material.GOLD_HELMET, Material.GOLD_LEGGINGS, Material.GOLD_BOOTS));

    public static final HashSet<Material> GOLD = new HashSet<>();

    public static final HashSet<Material> IRON_TOOLS = new HashSet<>(Arrays.asList(Material.IRON_AXE, Material.IRON_PICKAXE, Material.IRON_SWORD, Material.IRON_SPADE, Material.IRON_HOE));
    public static final HashSet<Material> IRON_ARMOR = new HashSet<>(Arrays.asList(Material.IRON_CHESTPLATE, Material.IRON_HELMET, Material.IRON_LEGGINGS, Material.IRON_BOOTS));

    public static final HashSet<Material> IRON = new HashSet<>();

    public static final HashSet<Material> STONE_TOOLS = new HashSet<>(Arrays.asList(Material.STONE_AXE, Material.STONE_PICKAXE, Material.STONE_SWORD, Material.STONE_SPADE, Material.STONE_HOE));
    public static final HashSet<Material> CHAINMAIL_ARMOR = new HashSet<>(Arrays.asList(Material.CHAINMAIL_CHESTPLATE, Material.CHAINMAIL_HELMET, Material.CHAINMAIL_LEGGINGS, Material.CHAINMAIL_BOOTS));

    public static final HashSet<Material> STONE = new HashSet<>();
    public static final HashSet<Material> CHAINMAIL = new HashSet<>();

    public static final HashSet<Material> WOOD_TOOLS = new HashSet<>(Arrays.asList(Material.WOOD_AXE, Material.WOOD_PICKAXE, Material.WOOD_SWORD, Material.WOOD_SPADE, Material.WOOD_HOE));
    public static final HashSet<Material> LEATHER_ARMOR = new HashSet<>(Arrays.asList(Material.LEATHER_CHESTPLATE, Material.LEATHER_HELMET, Material.LEATHER_LEGGINGS, Material.LEATHER_BOOTS));

    public static final HashSet<Material> WOOD = new HashSet<>();
    public static final HashSet<Material> LEATHER = new HashSet<>();

    public static final HashSet<Material> SWORDS = new HashSet<>(Arrays.asList(Material.DIAMOND_SWORD, Material.GOLD_SWORD, Material.IRON_SWORD, Material.STONE_SWORD, Material.WOOD_SWORD));
    public static final HashSet<Material> PICKAXES = new HashSet<>(Arrays.asList(Material.DIAMOND_PICKAXE, Material.GOLD_PICKAXE, Material.IRON_PICKAXE, Material.STONE_PICKAXE, Material.WOOD_PICKAXE));
    public static final HashSet<Material> AXES = new HashSet<>(Arrays.asList(Material.DIAMOND_AXE, Material.GOLD_AXE, Material.IRON_AXE, Material.STONE_AXE, Material.WOOD_AXE));
    public static final HashSet<Material> SPADES = new HashSet<>(Arrays.asList(Material.DIAMOND_SPADE, Material.GOLD_SPADE, Material.IRON_SPADE, Material.STONE_SPADE, Material.WOOD_SPADE));
    public static final HashSet<Material> HOES = new HashSet<>(Arrays.asList(Material.DIAMOND_HOE, Material.GOLD_HOE, Material.IRON_HOE, Material.STONE_HOE, Material.WOOD_HOE));

    public static final HashSet<Material> HELMETS = new HashSet<>(Arrays.asList(Material.DIAMOND_HELMET, Material.GOLD_HELMET, Material.IRON_HELMET, Material.CHAINMAIL_HELMET, Material.LEATHER_HELMET));
    public static final HashSet<Material> CHESTPLATES = new HashSet<>(Arrays.asList(Material.DIAMOND_CHESTPLATE, Material.GOLD_CHESTPLATE, Material.IRON_CHESTPLATE, Material.CHAINMAIL_CHESTPLATE, Material.LEATHER_CHESTPLATE));
    public static final HashSet<Material> LEGGINGS = new HashSet<>(Arrays.asList(Material.DIAMOND_LEGGINGS, Material.GOLD_LEGGINGS, Material.IRON_LEGGINGS, Material.CHAINMAIL_LEGGINGS, Material.LEATHER_LEGGINGS));
    public static final HashSet<Material> BOOTS = new HashSet<>(Arrays.asList(Material.DIAMOND_BOOTS, Material.GOLD_BOOTS, Material.IRON_BOOTS, Material.CHAINMAIL_BOOTS, Material.LEATHER_BOOTS));

    public static final HashSet<Material> TOOLS = new HashSet<>();
    public static final HashSet<Material> ARMOR = new HashSet<>();

    public static final HashSet<Material> MISC = new HashSet<>(Arrays.asList(Material.BONE, Material.ARROW, Material.STICK));

    public static final HashSet<Material> GENERATABLE = new HashSet<>();

    public static final HashSet<Material> HAND_HELD = new HashSet<>();

    public MaterialGroups()
    {
        MaterialGroups.initialize();
    }

    public static void initialize()
    {
        TOOLS.addAll(SWORDS);
        TOOLS.addAll(PICKAXES);
        TOOLS.addAll(AXES);
        TOOLS.addAll(SPADES);
        TOOLS.addAll(HOES);

        ARMOR.addAll(HELMETS);
        ARMOR.addAll(CHESTPLATES);
        ARMOR.addAll(LEGGINGS);
        ARMOR.addAll(BOOTS);

        HAND_HELD.addAll(TOOLS);
        HAND_HELD.addAll(MISC);

        GENERATABLE.addAll(TOOLS);
        GENERATABLE.addAll(ARMOR);
        GENERATABLE.addAll(MISC);

        DIAMOND.addAll(DIAMOND_ARMOR);
        DIAMOND.addAll(DIAMOND_TOOLS);
        DIAMOND.add(Material.DIAMOND_BARDING);

        GOLD.addAll(GOLD_ARMOR);
        GOLD.addAll(GOLD_TOOLS);
        GOLD.add(Material.GOLD_BARDING);

        IRON.addAll(IRON_ARMOR);
        IRON.addAll(IRON_TOOLS);
        IRON.add(Material.IRON_BARDING);

        STONE.addAll(STONE_TOOLS);

        CHAINMAIL.addAll(CHAINMAIL_ARMOR);

        WOOD.addAll(WOOD_TOOLS);

        LEATHER.addAll(LEATHER_ARMOR);
    }
}
