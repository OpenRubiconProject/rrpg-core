package com.openrubicon.core.api.menu.commands;

import com.openrubicon.core.api.command.Command;
import com.openrubicon.core.api.interactables.Player;
import com.openrubicon.core.api.interactables.enums.InteractableType;
import com.openrubicon.core.api.interactables.interfaces.Interactable;
import com.openrubicon.core.api.menu.MenuHandler;
import com.openrubicon.core.api.menu.enums.EventType;
import com.openrubicon.core.api.utility.DynamicPrimitive;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.UUID;

public class MenuAction extends Command {

    @Override
    public String getCommandFormat() {
        return "menu action $ $ |1"; // menu action {uuid} {type} | {"Optional string parameter"}
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
        Bukkit.broadcastMessage("test");

        UUID menuUuid = UUID.fromString(args.get(0).getString());
        EventType eventType = EventType.fromString(args.get(1).getString());

        String optional = "";
        if(args.size() > 2)
            optional = args.get(2).getString();

        if(eventType == EventType.COMMAND)
        {
            if(optional != null && !optional.equals(""))
                ((Player)sender).getPlayer().performCommand(optional.substring(1));
        }

        Bukkit.broadcastMessage(optional);

        MenuHandler.getMenus().get(menuUuid.toString()).update();
    }
}
