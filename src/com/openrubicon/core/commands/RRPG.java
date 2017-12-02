package com.openrubicon.core.commands;

import com.openrubicon.core.api.command.Command;
import com.openrubicon.core.api.interactables.interfaces.Interactable;
import com.openrubicon.core.api.interactables.enums.InteractableType;
import com.openrubicon.core.api.utility.DynamicPrimitive;

import java.util.ArrayList;

public class RRPG extends Command {

    @Override
    public String getCommandFormat() {
        return "";
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
        sender.sendMessage("RRPG Core");
    }
}
