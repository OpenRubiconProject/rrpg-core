package com.openrubicon.core.api.attributes;

import com.openrubicon.core.api.attributes.enums.AttributeModifierType;
import com.openrubicon.core.api.inventory.entities.enums.EntityInventorySlotType;

import java.util.UUID;

public class AttributeModifier
{
    private AttributeModifierType name;
    private int operation;
    private double amount;
    private UUID uuidMost;
    private UUID uuidLeast;

    private EntityInventorySlotType entityInventorySlotType = EntityInventorySlotType.MAINHAND;

    public AttributeModifier(AttributeModifierType attributeName)
    {
        name = attributeName;
    }

    public AttributeModifier(AttributeModifierType type, int operation, double amount)
    {
        this.name = type;
        this.operation = operation;
        this.amount = amount;
        this.uuidMost = UUID.randomUUID();
        this.uuidLeast = UUID.randomUUID();
    }

    public EntityInventorySlotType getEntityInventorySlotType() {
        return entityInventorySlotType;
    }

    public void setEntityInventorySlotType(EntityInventorySlotType entityInventorySlotType) {
        this.entityInventorySlotType = entityInventorySlotType;
    }

    public AttributeModifierType getName() {
        return name;
    }

    public void setName(AttributeModifierType name) {
        this.name = name;
    }

    public int getOperation() {
        return operation;
    }

    public void setOperation(int operation) {
        this.operation = operation;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public UUID getUuidMost() {
        return uuidMost;
    }

    public void setUuidMost(UUID uuidMost) {
        this.uuidMost = uuidMost;
    }

    public UUID getUuidLeast() {
        return uuidLeast;
    }

    public void setUuidLeast(UUID uuidLeast) {
        this.uuidLeast = uuidLeast;
    }
}
