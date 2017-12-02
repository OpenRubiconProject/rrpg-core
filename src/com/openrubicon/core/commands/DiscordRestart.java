package com.openrubicon.core.commands;

import com.openrubicon.core.RRPGCore;
import com.openrubicon.core.api.command.Command;
import com.openrubicon.core.api.interactables.interfaces.Interactable;
import com.openrubicon.core.api.interactables.enums.InteractableType;
import com.openrubicon.core.api.utility.DynamicPrimitive;

import java.util.ArrayList;

public class DiscordRestart extends Command {

    @Override
    public String getCommandFormat() {
        return "discord restart";
    }

    @Override
    public ArrayList<InteractableType> getAllowedSenderTypes() {
        ArrayList<InteractableType> senders = new ArrayList<>();
        senders.add(InteractableType.CONSOLE);
        senders.add(InteractableType.DISCORD);
        return senders;
    }

    @Override
    public void handle(Interactable sender, ArrayList<DynamicPrimitive> args) {
        sender.sendMessage("Restarting Discord..");
        RRPGCore.discord.shutdown();
        RRPGCore.discord.start();
        sender.sendMessage("Discord has been restarted.");
    }
}
