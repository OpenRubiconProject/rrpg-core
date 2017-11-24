package com.openrubicon.core.api.attributes;

import com.openrubicon.core.api.nbt.NBT;
import com.openrubicon.core.api.attributes.enums.AttributeModifierType;
import com.openrubicon.core.api.inventory.entities.enums.EntityInventorySlotType;
import com.openrubicon.core.helpers.Helpers;
import com.openrubicon.core.api.interfaces.Observeable;
import de.tr7zw.itemnbtapi.NBTList;
import de.tr7zw.itemnbtapi.NBTListCompound;
import de.tr7zw.itemnbtapi.NBTType;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;

public class AttributeModifiers implements Observeable {

    private HashMap<AttributeModifierType, AttributeModifier> modifiers = new HashMap<>();

    public AttributeModifiers() {

    }

    public AttributeModifiers(ItemStack item)
    {
        NBT i = new NBT(item);
        this.load(i);
    }

    public AttributeModifiers(NBT i)
    {
        this.load(i);
    }

    public HashMap<AttributeModifierType, AttributeModifier> getModifiers()
    {
        return this.modifiers;
    }

    public boolean load(NBT i)
    {
        NBTList attributes = i.getList("AttributeModifiers");
        int size = attributes.size();
        if(size == 0 || size == -1)
            return true;

        for(int z = 0; z < size; z++)
        {
            NBTListCompound modifier = attributes.getCompound(z);
            AttributeModifierType name = AttributeModifierType.fromString(modifier.getString("AttributeName"));
            EntityInventorySlotType slot = EntityInventorySlotType.fromString(modifier.getString("Slot"));

            modifiers.put(name, new AttributeModifier(name, modifier.getInteger("Operation"), modifier.getDouble("Amount")));
        }

        return true;
    }

    public boolean save(ItemStack item)
    {
        NBT i = new NBT(item);

        return this.save(i);
    }

    public boolean save(NBT i)
    {
        //this.removeAll(i);

        NBTList attributes = i.getList("AttributeModifiers", NBTType.NBTTagCompound);

        for(AttributeModifier attribute : this.modifiers.values())
        {
            NBTListCompound attrib = attributes.addCompound();
            attrib.setDouble("Amount", attribute.getAmount());
            attrib.setString("AttributeName", attribute.getName().toString());
            attrib.setString("Name", attribute.getName().toString());
            attrib.setInteger("Operation", attribute.getOperation());
            attrib.setInteger("UUIDLeast", 59764);
            attrib.setInteger("UUIDMost", 31483);

            EntityInventorySlotType slot = attribute.getEntityInventorySlotType();
            if(slot != EntityInventorySlotType.ALL)
                attrib.setString("Slot", slot.toString());
        }
        return true;
    }

    public boolean removeAll(NBT i)
    {
        NBTList attributes = i.getList("AttributeModifiers", NBTType.NBTTagCompound);
        int size = attributes.size();
        if(size == 0 || size == -1)
            return true;

        for(int z = 0; z < size; z++)
        {
            NBTListCompound modifier = attributes.getCompound(z);
            //modifier.
            //attributes.
            //AttributeModifierType name = AttributeModifierType.fromString(modifier.getString("AttributeName"));
            //Bukkit.broadcastMessage(name.toString());
        }

        return true;
    }

    public AttributeModifier getAttribute(AttributeModifierType attributeName)
    {
        if(this.modifiers.containsKey(attributeName))
        {
            return this.modifiers.get(attributeName);
        }
        return null;
    }

    public boolean addAttribute(AttributeModifier attribute)
    {
        this.modifiers.put(attribute.getName(), attribute);
        return true;
    }

    public AttributeModifier remove(AttributeModifierType name)
    {
        if(!this.modifiers.containsKey(name))
            return null;

        AttributeModifier attribute = this.modifiers.get(name);
        this.modifiers.remove(name);
        return attribute;
    }

    @Override
    public ArrayList<String> getObservation() {
        ArrayList<String> view = new ArrayList<>();
        for(AttributeModifier attribute : modifiers.values())
        {
            String modName = Helpers.camelCaseToReadable(attribute.getName().toString().substring(8));
            modName = modName.substring(0, 1).toUpperCase() + modName.substring(1);
            String amount = "";
            double numberAmount = Helpers.round(attribute.getAmount(), 4);
            if(numberAmount > 0)
                amount = "&a+&b" + numberAmount;
            else
                amount = "&c-&b" + numberAmount;
            view.add(Helpers.colorize("&2"+modName+"&f: " + amount));
        }
        return view;
    }

}
