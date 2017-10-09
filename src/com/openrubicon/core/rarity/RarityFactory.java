package com.openrubicon.core.rarity;

public class RarityFactory {

    public static Rarity make(int rarity)
    {
        switch(rarity)
        {
            case 1:
                return new Wood();
            case 2:
                return new Tin();
            case 3:
                return new Copper();
            case 4:
                return new Bronze();
            case 5:
                return new Silver();
            case 6:
                return new Gold();
            case 7:
                return new Platinum();
            case 8:
                return new Emerald();
            case 9:
                return new Diamond();
            case 10:
                return new Mystic();
            case 11:
                return new Spiritic();
            default:
                return null;

        }
    }
}
