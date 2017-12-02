package com.openrubicon.core.commands.config;

import com.openrubicon.core.RRPGCore;
import com.openrubicon.core.api.command.Command;
import com.openrubicon.core.api.configuration.ConfigurationProperty;
import com.openrubicon.core.api.interactables.enums.InteractableType;
import com.openrubicon.core.api.interactables.interfaces.Interactable;
import com.openrubicon.core.api.utility.DynamicPrimitive;
import com.openrubicon.core.helpers.Constants;

import java.util.ArrayList;

public class ConfigSet extends Command {

    @Override
    public String getCommandFormat() {
        return "config set $ $";
    }

    @Override
    public ArrayList<InteractableType> getAllowedSenderTypes() {
        ArrayList<InteractableType> senders = new ArrayList<>();
        senders.add(InteractableType.CONSOLE);
        senders.add(InteractableType.PLAYER);
        senders.add(InteractableType.DISCORD);
        return senders;
    }

    @Override
    public void handle(Interactable sender, ArrayList<DynamicPrimitive> args)
    {
        ConfigurationProperty property = RRPGCore.config.get(args.get(0).getString());

        if(property == null)
        {
            sender.sendMessage("This configuration property does not exist.");
            return;
        }


        property.setProperty(args.get(1).getString());

        sender.sendMessage(Constants.HEADING_COLOR + "Set: " + property.getObservation().get(0));

        if(!property.isSaved())
            sender.sendMessage(Constants.YELLOW + "This update will not be replicated to the config file. Update the config file manually to make a permanent change.");
    }
}