package com.openrubicon.core.commands.config;

import com.openrubicon.core.RRPGCore;
import com.openrubicon.core.api.command.Command;
import com.openrubicon.core.api.configuration.Configuration;
import com.openrubicon.core.api.interactables.enums.InteractableType;
import com.openrubicon.core.api.interactables.interfaces.Interactable;
import com.openrubicon.core.api.utility.DynamicPrimitive;

import java.util.ArrayList;

public class ConfigLoad extends Command {

    @Override
    public String getCommandFormat() {
        return "config load";
    }

    @Override
    public ArrayList<InteractableType> getAllowedSenderTypes() {
        ArrayList<InteractableType> senders = new ArrayList<>();
        senders.add(InteractableType.CONSOLE);
        senders.add(InteractableType.PLAYER);
        return senders;
    }

    @Override
    public void handle(Interactable sender, ArrayList<DynamicPrimitive> args) {

        RRPGCore.plugin.reloadConfig();
        RRPGCore.config = new Configuration(RRPGCore.plugin.getConfig());
        RRPGCore.config.add(RRPGCore.modules.getConfigurationProperties());
        RRPGCore.config.load();

        sender.sendMessage("Configuration file was reloaded.");
    }
}
