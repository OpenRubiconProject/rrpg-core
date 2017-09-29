package com.openrubicon.core.enums;

import java.util.Random;

public enum AttributeModifierType {
    MAX_HEALTH("generic.maxHealth"),
    MOVEMENT_SPEED("generic.movementSpeed"),
    ATTACK_DAMAGE("generic.attackDamage"),
    ARMOR("generic.armor"),
    ARMOR_TOUGHNESS("generic.armorToughness"),
    ATTACK_SPEED("generic.attackSpeed"),
    LUCK("generic.luck"),
    KNOCKBACK_RESISTANCE("generic.knockbackResistance");

    private final String name;

    AttributeModifierType(final String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return this.name;
    }

    public static AttributeModifierType fromString(String name)
    {
        for (AttributeModifierType n : AttributeModifierType.values()) {
            if (n.name.equalsIgnoreCase(name)) {
                return n;
            }
        }

        return AttributeModifierType.ATTACK_DAMAGE;
    }

    public static AttributeModifierType random()
    {
        AttributeModifierType[] values = AttributeModifierType.values();
        int size = values.length;
        Random r = new Random();
        int index = r.nextInt(size);
        return values[index];
    }

    public static int size()
    {
        AttributeModifierType[] values = AttributeModifierType.values();
        return values.length;
    }
}
