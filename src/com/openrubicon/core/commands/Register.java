package com.openrubicon.core.commands;

import com.openrubicon.core.api.account.AccountManagement;
import com.openrubicon.core.api.command.Command;
import com.openrubicon.core.api.interactables.Interactable;
import com.openrubicon.core.api.interactables.enums.InteractableType;

import java.util.ArrayList;

public class Register extends Command {

    @Override
    public String getCommandFormat() {
        return "register $ $";
    }

    @Override
    public ArrayList<InteractableType> getAllowedSenderTypes() {
        ArrayList<InteractableType> senders = new ArrayList<>();
        senders.add(InteractableType.CONSOLE);
        senders.add(InteractableType.DISCORD);
        senders.add(InteractableType.PLAYER);
        return senders;
    }

    @Override
    public void handle(Interactable sender, String[] args) {
        AccountManagement accountManagement = new AccountManagement();
        if(accountManagement.register(args[0], args[1]))
            sender.sendMessage("Registered successfully");
        else
            sender.sendMessage("Registered failed");
    }
}