package com.openrubicon.core.api.chat;

import net.md_5.bungee.api.ChatColor;

public class Colors implements com.openrubicon.core.api.chat.interfaces.Colors {

    public static ChatColor getPrimaryColor() {
        return new Colors().primaryColor();
    }

    public static ChatColor getSecondaryColor() {
        return new Colors().secondaryColor();
    }

    public static ChatColor getTertiaryColor() {
        return new Colors().tertiaryColor();
    }

    public static ChatColor getHeadingColor() {
        return new Colors().headingColor();
    }

    public static ChatColor getMysticPrimaryColor() {
        return new Colors().mysticPrimaryColor();
    }

    public static ChatColor getMysticSecondaryColor() {
        return new Colors().mysticSecondaryColor();
    }

    public static ChatColor getRed() {
        return new Colors().red();
    }

    public static ChatColor getYellow() {
        return new Colors().yellow();
    }

    public static ChatColor getGreen() {
        return new Colors().green();
    }

}
