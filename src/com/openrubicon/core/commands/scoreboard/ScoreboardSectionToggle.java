package com.openrubicon.core.commands.scoreboard;

import com.openrubicon.core.RRPGCore;
import com.openrubicon.core.api.command.Command;
import com.openrubicon.core.api.interactables.Player;
import com.openrubicon.core.api.interactables.enums.InteractableType;
import com.openrubicon.core.api.interactables.interfaces.Interactable;
import com.openrubicon.core.api.scoreboard.interfaces.ScoreboardSection;
import com.openrubicon.core.api.utility.DynamicPrimitive;

import java.util.ArrayList;
import java.util.Map;

public class ScoreboardSectionToggle extends Command {

    @Override
    public String getCommandFormat() {
        return "scoreboard,sb section toggle $s $b";
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
        for(Map.Entry<ScoreboardSection, Boolean> entry : RRPGCore.players.getPlayerData(((Player)sender).getPlayer(), com.openrubicon.core.server.playerdata.ScoreboardSections.class).getSections().entrySet())
        {
            ScoreboardSection section = entry.getKey();
            if(section.getClass().getSimpleName().equals(args.get(0).getString()))
            {
                RRPGCore.players.getPlayerData(((Player)sender).getPlayer(), com.openrubicon.core.server.playerdata.ScoreboardSections.class).getSections().put(section, args.get(1).getBoolean());
            }
        }
    }
}
