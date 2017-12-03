package com.openrubicon.core.commands.scoreboard;

import com.openrubicon.core.api.command.Command;
import com.openrubicon.core.api.interactables.enums.InteractableType;
import com.openrubicon.core.api.interactables.interfaces.Interactable;
import com.openrubicon.core.api.utility.DynamicPrimitive;

import java.util.ArrayList;

public class Scoreboard extends Command {

    @Override
    public String getCommandFormat() {
        return "scoreboard,sb";
    }

    @Override
    public ArrayList<InteractableType> getAllowedSenderTypes() {
        ArrayList<InteractableType> senders = new ArrayList<>();
        senders.add(InteractableType.PLAYER);
        return senders;
    }

    @Override
    public void handle(Interactable sender, ArrayList<DynamicPrimitive> args)
    {
        sender.sendMessage("/rrpg sb sections | {text:true}");
    }
}
