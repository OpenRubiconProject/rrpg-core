package com.openrubicon.core.commands.scoreboard;

import com.openrubicon.core.RRPGCore;
import com.openrubicon.core.api.command.Command;
import com.openrubicon.core.api.interactables.Player;
import com.openrubicon.core.api.interactables.enums.InteractableType;
import com.openrubicon.core.api.interactables.interfaces.Interactable;
import com.openrubicon.core.api.menu.MenuBuilder;
import com.openrubicon.core.api.menu.defaults.MenuFormat;
import com.openrubicon.core.api.menu.defaults.MenuRender;
import com.openrubicon.core.api.menu.enums.MenuType;
import com.openrubicon.core.api.utility.DynamicPrimitive;

import java.util.ArrayList;

public class ScoreboardSections extends Command {

    @Override
    public String getCommandFormat() {
        return "scoreboard,sb sections |1";
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
        com.openrubicon.core.menu.ScoreboardSections template = new com.openrubicon.core.menu.ScoreboardSections(RRPGCore.players.getPlayerData(((Player)sender).getPlayer(),
                com.openrubicon.core.server.playerdata.ScoreboardSections.class).getSections());

        if(args.size() == 1 && Boolean.parseBoolean(args.get(0).getString()))
            new MenuBuilder(template, MenuType.TEXT).withFormat(new MenuFormat()).withRender(new MenuRender()).withViewer(((Player)sender).getPlayer()).draw();
        else
            new MenuBuilder(template, MenuType.INVENTORY).withFormat(new MenuFormat()).withRender(new MenuRender()).withViewer(((Player)sender).getPlayer()).draw();
    }
}
