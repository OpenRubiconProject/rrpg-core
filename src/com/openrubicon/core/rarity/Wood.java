package com.openrubicon.core.rarity;

public class Wood extends Rarity {

    public Wood() {
        super();
        this.rarity = 1;
        this.grade = 1;
        this.costModifier = 1.0f;
        this.color = "&f";
        this.name = "Wood";
        this.description = "Wood tier is basic. Easily broken with attribute points makes Wood the least desirable tier.";
    }

}
