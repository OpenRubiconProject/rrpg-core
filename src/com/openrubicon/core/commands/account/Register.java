package com.openrubicon.core.commands.account;

import com.openrubicon.core.api.account.AccountManagement;
import com.openrubicon.core.api.command.Command;
import com.openrubicon.core.api.interactables.Discord;
import com.openrubicon.core.api.interactables.Player;
import com.openrubicon.core.api.interactables.interfaces.Interactable;
import com.openrubicon.core.api.interactables.enums.InteractableSenderVisibility;
import com.openrubicon.core.api.interactables.enums.InteractableType;

import java.util.ArrayList;
import java.util.Arrays;

public class Register extends Command {

    @Override
    public String getCommandFormat() {
        return "register $ $";
    }

    @Override
    public ArrayList<InteractableType> getAllowedSenderTypes() {
        ArrayList<InteractableType> senders = new ArrayList<>();
        senders.add(InteractableType.DISCORD);
        senders.add(InteractableType.PLAYER);
        return senders;
    }

    @Override
    public ArrayList<InteractableSenderVisibility> getAllowedSenderVisiblity() {
        return new ArrayList<>(Arrays.asList(InteractableSenderVisibility.PRIVATE, InteractableSenderVisibility.NOT_APPLICABLE));
    }

    @Override
    public void handle(Interactable sender, String[] args) {

        AccountManagement accountManagement = new AccountManagement();

        if(accountManagement.register(args[0], args[1]))
        {
            sender.sendMessage("Registered successfully");

            if(sender.getInteractableType() == InteractableType.DISCORD)
            {
                if(accountManagement.linkDiscord(args[0], args[1], sender.getId()))
                {
                    sender.sendMessage("Discord account linked successfully");
                } else {
                    sender.sendMessage("Discord account linking failed");
                }
            } else if (sender.getInteractableType() == InteractableType.PLAYER)
            {
                if(accountManagement.linkMinecraft(args[0], args[1], sender.getId()))
                {
                    sender.sendMessage("Minecraft account linked successfully");
                } else {
                    sender.sendMessage("Minecraft account linking failed");
                }
            }
        }
        else
            sender.sendMessage("Registered failed");
    }
}