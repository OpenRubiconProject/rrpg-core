package com.openrubicon.core.api.attributes;

import com.openrubicon.core.api.attributes.enums.AttributeModifierType;
import com.openrubicon.core.api.inventory.entities.enums.EntityInventorySlotType;
import com.openrubicon.core.helpers.Helpers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AttributeFactory {

    public AttributeModifier random()
    {
        return new AttributeModifier(AttributeModifierType.random());
    }

    public static ArrayList<AttributeModifier> generate(int points) {

        int modifierCount = AttributeModifierType.size();

        HashMap<AttributeModifierType, Integer> modifiers = new HashMap<>();
        ArrayList<AttributeModifier> attributes = new ArrayList<>();

        modifiers.put(AttributeModifierType.ATTACK_DAMAGE, 1);
        points--;

        for (int i = 0; i < points; i++)
        {
            AttributeModifierType modifier = AttributeModifierType.random();

            int amount = 1;

            if(modifiers.containsKey(modifier))
                amount += modifiers.get(modifier);

            modifiers.put(modifier, amount);
        }

        for(Map.Entry<AttributeModifierType, Integer> entry : modifiers.entrySet())
        {
            AttributeModifierType modifier = entry.getKey();
            double amount = entry.getValue();

            switch(modifier)
            {
                case MAX_HEALTH:
                    amount *= 2.;
                    break;
                case KNOCKBACK_RESISTANCE:
                    amount = Helpers.scale(amount, 0, 100, 0.0, 1.0);
                    break;
                case MOVEMENT_SPEED:
                    amount = Helpers.scale(amount, 0, 100, 0.0, 1.4);
                    break;
                case ATTACK_DAMAGE:
                    amount = (int)(amount * 1.5);
                    break;
                case ARMOR:
                    amount = Helpers.scale(amount, 0, 100, 0, 30);
                    break;
                case ARMOR_TOUGHNESS:
                    amount = Helpers.scale(amount, 0, 100, 0, 20);
                    break;
                case ATTACK_SPEED:
                    amount = Helpers.scale(amount, 0, 100, 0.5, 10);
                    break;
                case LUCK:
                    amount = Helpers.scale(amount, 0, 100, 0, 512);
            }


            AttributeModifier attributeModifier = new AttributeModifier(modifier, 0, amount);
            // TODO
            attributeModifier.setEntityInventorySlotType(EntityInventorySlotType.MAINHAND);
            attributes.add(attributeModifier);
        }

        return attributes;
    }

}
