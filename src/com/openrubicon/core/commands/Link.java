package com.openrubicon.core.commands;

import com.openrubicon.core.api.account.AccountManagement;
import com.openrubicon.core.api.command.Command;
import com.openrubicon.core.api.interactables.Discord;
import com.openrubicon.core.api.interactables.Interactable;
import com.openrubicon.core.api.interactables.Player;
import com.openrubicon.core.api.interactables.enums.InteractableType;

import java.util.ArrayList;

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
    public void handle(Interactable sender, String[] args) {
        AccountManagement accountManagement = new AccountManagement();

        if(sender instanceof Player)
        {
            Player player = (Player) sender;
            if(accountManagement.linkMinecraft(args[0], args[1], player.getPlayer().getUniqueId().toString()))
                sender.sendMessage("Linked successfully");
            else
                sender.sendMessage("Linking failed");
        }

        if(sender instanceof Discord)
        {
            Discord discord = (Discord) sender;
            if(accountManagement.linkDiscord(args[0], args[1], discord.getAuthor().getIdLong()))
                sender.sendMessage("Linked successfully");
            else
                sender.sendMessage("Linking failed");
        }

    }
}
