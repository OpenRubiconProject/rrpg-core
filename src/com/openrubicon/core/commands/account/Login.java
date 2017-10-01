package com.openrubicon.core.commands.account;

import com.openrubicon.core.api.account.AccountManagement;
import com.openrubicon.core.api.command.Command;
import com.openrubicon.core.api.interactables.Interactable;
import com.openrubicon.core.api.interactables.enums.InteractableSenderVisibility;
import com.openrubicon.core.api.interactables.enums.InteractableType;

import java.util.ArrayList;
import java.util.Arrays;

public class Login extends Command {
    @Override
    public String getCommandFormat() {
        return "login $ $";
    }

    @Override
    public ArrayList<InteractableType> getAllowedSenderTypes() {
        ArrayList<InteractableType> senders = new ArrayList<>();
        senders.add(InteractableType.PLAYER);
        senders.add(InteractableType.DISCORD);
        return senders;
    }

    @Override
    public ArrayList<InteractableSenderVisibility> getAllowedSenderVisiblity() {
        return new ArrayList<>(Arrays.asList(InteractableSenderVisibility.PRIVATE, InteractableSenderVisibility.NOT_APPLICABLE));
    }

    @Override
    public void handle(Interactable sender, String[] args) {
        AccountManagement accountManagement = new AccountManagement();
        if(accountManagement.login(args[0], args[1]))
            sender.sendMessage("Logged in successfully");
        else
            sender.sendMessage("Login failed");
    }
}