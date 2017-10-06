package com.openrubicon.core.commands;

import com.openrubicon.core.RRPGCore;
import com.openrubicon.core.api.command.Command;
import com.openrubicon.core.api.interactables.interfaces.Interactable;
import com.openrubicon.core.api.interactables.enums.InteractableType;

import java.util.ArrayList;

public class ConnectorRestart extends Command {
    @Override
    public String getCommandFormat() {
        return "connector restart";
    }

    @Override
    public ArrayList<InteractableType> getAllowedSenderTypes() {
        ArrayList<InteractableType> senders = new ArrayList<>();
        senders.add(InteractableType.CONSOLE);
        return senders;
    }

    @Override
    public void handle(Interactable sender, String[] args) {
        sender.sendMessage("Restarting Connector..");
        RRPGCore.connector.shutdown();
        RRPGCore.connector.start();
        sender.sendMessage("Connector has been restarted.");
    }
}
