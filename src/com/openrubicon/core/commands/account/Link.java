package com.openrubicon.core.commands.account;

import com.openrubicon.core.api.account.AccountManagement;
import com.openrubicon.core.api.command.Command;
import com.openrubicon.core.api.interactables.Discord;
import com.openrubicon.core.api.interactables.Interactable;
import com.openrubicon.core.api.interactables.Player;
import com.openrubicon.core.api.interactables.enums.InteractableSenderVisibility;
import com.openrubicon.core.api.interactables.enums.InteractableType;

import java.util.ArrayList;
import java.util.Arrays;

public class Link extends Command {
    @Override
    public String getCommandFormat() {
        return "link $ $";
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

        if(sender.getInteractableType() == InteractableType.PLAYER)
        {
            Player player = (Player) sender;
            if(accountManagement.linkMinecraft(args[0], args[1], player.getPlayer().getUniqueId().toString()))
                sender.sendMessage("Linked successfully");
            else
                sender.sendMessage("Linking failed");
        }

        if(sender.getInteractableType() == InteractableType.DISCORD)
        {
            Discord discord = (Discord) sender;
            if(accountManagement.linkDiscord(args[0], args[1], discord.getAuthor().getIdLong()))
                sender.sendMessage("Linked successfully");
            else
                sender.sendMessage("Linking failed");
        }

    }
}
