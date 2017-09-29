package com.openrubicon.core.enums;

public enum InventorySlotType {
    MAINHAND("mainhand"),
    OFFHAND("offhand"),
    HELMET("helmet"),
    CHESTPLATE("chestplate"),
    LEGGINGS("leggings"),
    BOOTS("boots"),
    ARMOR("armor"),
    OTHER("other");

    private final String name;

    InventorySlotType() {
        this.name = "mainhand";
    }

    InventorySlotType(final String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return this.name;
    }

    public static InventorySlotType fromString(String name)
    {
        for (InventorySlotType n : InventorySlotType.values()) {
            if (n.name.equalsIgnoreCase(name)) {
                return n;
            }
        }

        return InventorySlotType.MAINHAND;
    }
}
