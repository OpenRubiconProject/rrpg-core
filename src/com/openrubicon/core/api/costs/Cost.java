package com.openrubicon.core.api.costs;

import org.bukkit.entity.Player;

abstract public class Cost {

    private float cost;

    public Cost() {
        this.cost = 0;
    }

    public Cost(float cost) {
        this.cost = cost;
    }

    abstract public boolean has(Player player);

    abstract public void withdraw(Player player);

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public void scaleCost(float percent)
    {
        this.cost *= percent;
    }

    public void increaseCost(float increase)
    {
        cost += increase;
    }

    public void decreaseCost(float decrease)
    {
        cost -= decrease;
    }



}
