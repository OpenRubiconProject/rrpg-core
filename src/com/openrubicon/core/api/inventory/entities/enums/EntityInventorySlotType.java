package com.openrubicon.core.api.inventory.entities.enums;

import com.openrubicon.core.api.inventory.interfaces.InventorySlotType;

public enum EntityInventorySlotType implements InventorySlotType {
    MAINHAND("mainhand"),
    OFFHAND("offhand"),
    HELMET("head"),
    CHESTPLATE("chest"),
    LEGGINGS("legs"),
    BOOTS("feet"),
    ARMOR("armor"),
    OTHER("other"),
    ALL("all");

    private final String name;

    EntityInventorySlotType() {
        this.name = "mainhand";
    }

    EntityInventorySlotType(final String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return this.name;
    }

    public static EntityInventorySlotType fromString(String name)
    {
        for (EntityInventorySlotType n : EntityInventorySlotType.values()) {
            if (n.name.equalsIgnoreCase(name)) {
                return n;
            }
        }

        return null;
    }
}
