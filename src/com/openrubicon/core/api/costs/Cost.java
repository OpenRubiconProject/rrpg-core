package com.openrubicon.core.api.costs;

abstract public class Cost {

    private float cost;

    public Cost() {
        this.cost = 0;
    }

    public Cost(float cost) {
        this.cost = cost;
    }

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
