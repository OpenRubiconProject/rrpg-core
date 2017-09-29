package com.openrubicon.core.commands;

import com.openrubicon.core.api.command.Command;
import com.openrubicon.core.api.interactables.Interactable;
import com.openrubicon.core.api.interactables.enums.InteractableType;
import org.bukkit.Bukkit;

import java.util.ArrayList;

public class ConfigGet extends Command {

    @Override
    public String getCommandFormat() {
        return "config get $";
    }

    @Override
    public ArrayList<InteractableType> getAllowedSenderTypes() {
        ArrayList<InteractableType> senders = new ArrayList<>();
        senders.add(InteractableType.CONSOLE);
        return senders;
    }

    @Override
    public void handle(Interactable sender, String[] args) {
        sender.sendMessage("Configuration doesn't support retrieving: " + args[0] + " yet");
    }
}