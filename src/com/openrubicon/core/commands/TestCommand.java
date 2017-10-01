package com.openrubicon.core.commands;

import com.openrubicon.core.api.command.Command;
import com.openrubicon.core.api.interactables.Interactable;
import com.openrubicon.core.api.interactables.enums.InteractableType;

import java.util.ArrayList;

public class TestCommand extends Command {

    @Override
    public String getCommandFormat() {
        return "test command";
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
    public void handle(Interactable sender, String[] args) {
        sender.sendMessage("Command has been tested");
    }
}
