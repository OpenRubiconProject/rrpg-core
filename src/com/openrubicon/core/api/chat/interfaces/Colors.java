package com.openrubicon.core.api.chat.interfaces;

import com.openrubicon.core.helpers.Constants;
import net.md_5.bungee.api.ChatColor;

public interface Colors {

    default ChatColor primaryColor() {
        return ChatColor.getByChar(Constants.PRIMARY_COLOR.charAt(1));
    }

    default ChatColor secondaryColor() {
        return ChatColor.getByChar(Constants.SECONDARY_COLOR.charAt(1));
    }

    default ChatColor tertiaryColor() {
        return ChatColor.getByChar(Constants.TERTIARY_COLOR.charAt(1));
    }

    default ChatColor headingColor() {
        return ChatColor.getByChar(Constants.HEADING_COLOR.charAt(1));
    }

    default ChatColor mysticPrimaryColor() {
        return ChatColor.getByChar(Constants.MYSTIC_PRIMARY_COLOR.charAt(1));
    }

    default ChatColor mysticSecondaryColor() {
        return ChatColor.getByChar(Constants.MYSTIC_SECONDARY_COLOR.charAt(1));
    }

    default ChatColor red() {
        return ChatColor.getByChar(Constants.RED.charAt(1));
    }

    default ChatColor yellow() {
        return ChatColor.getByChar(Constants.YELLOW.charAt(1));
    }

    default ChatColor green() {
        return ChatColor.getByChar(Constants.GREEN.charAt(1));
    }

}