package com.openrubicon.core.commands.config;

import com.openrubicon.core.RRPGCore;
import com.openrubicon.core.api.command.Command;
import com.openrubicon.core.api.interactables.enums.InteractableType;
import com.openrubicon.core.api.interactables.interfaces.Interactable;

import java.util.ArrayList;

public class ConfigSave extends Command {

    @Override
    public String getCommandFormat() {
        return "config save";
    }

    @Override
    public ArrayList<InteractableType> getAllowedSenderTypes() {
        ArrayList<InteractableType> senders = new ArrayList<>();
        senders.add(InteractableType.CONSOLE);
        senders.add(InteractableType.PLAYER);
        return senders;
    }

    @Override
    public void handle(Interactable sender, String[] args) {
        RRPGCore.config.save();
        RRPGCore.plugin.saveConfig();

        sender.sendMessage("Configuration file was saved.");
    }
}
