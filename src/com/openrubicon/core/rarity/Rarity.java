package com.openrubicon.core.rarity;

import com.openrubicon.core.api.configuration.Configuration;
import com.openrubicon.core.api.interfaces.Loreable;
import com.openrubicon.core.api.interfaces.Observeable;
import com.openrubicon.core.helpers.Helpers;

import java.io.Serializable;
import java.util.ArrayList;

abstract public class Rarity implements Observeable, Loreable, Serializable {

    public int rarity = 0;

    public float costModifier = 1.0f;

    public int grade = 0;

    public String color = "";

    public String name = "Rarity";

    public String description = "Rarities are modifiers";

    public int getRarity() {
        return rarity;
    }

    public void setRarity(int rarity) {
        this.rarity = rarity;
    }

    public float getCostModifier() {
        return costModifier;
    }

    public void setCostModifier(float costModifier) {
        this.costModifier = costModifier;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColoredName()
    {
        return Helpers.colorize(this.color+this.name);
    }

    public String getGradeName()
    {
        return this.getGradeName(this.grade);
    }

    public String getGradeName(int grade)
    {

        switch (grade) {
            case 0:
                return "Default";
            case 1:
                return "Low Grade";
            case 2:
                return "Medium Grade";
            case 3:
                return "High Grade";
            case 4:
                return "Rare Grade";
            default:
                return "Unknown Grade";
        }

    }

    public ArrayList<String> getObservation()
    {
        ArrayList<String> view = new ArrayList<>();

        view.add(Helpers.colorize("&2Rarity&f: &a"+this.getColoredName()));
        view.add(Helpers.colorize("&2"+this.description));
        view.add(Helpers.colorize("&2Grade&f: &a"+this.getGradeName()));

        return view;
    }

    @Override
    public ArrayList<String> getLore() {
        ArrayList<String> lore = new ArrayList<>();
        lore.add(Configuration.PRIMARY_COLOR + "Rarity: " + this.getColoredName());
        return lore;
    }
}
