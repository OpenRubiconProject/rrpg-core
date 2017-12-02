package com.openrubicon.core.commands.tests;

import com.openrubicon.core.api.command.Command;
import com.openrubicon.core.api.interactables.enums.InteractableType;
import com.openrubicon.core.api.interactables.interfaces.Interactable;
import com.openrubicon.core.api.menu.chat.ChatMenu;
import com.openrubicon.core.api.menu.chat.components.CheckboxComponent;
import com.openrubicon.core.api.utility.DynamicPrimitive;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class TestCheckbox extends Command {

    @Override
    public String getCommandFormat() {
        return "test checkbox";
    }

    @Override
    public ArrayList<InteractableType> getAllowedSenderTypes() {
        ArrayList<InteractableType> senders = new ArrayList<>();
        senders.add(InteractableType.PLAYER);
        return senders;
    }

    @Override
    public void handle(Interactable sender, ArrayList<DynamicPrimitive> args) {
        Player player = ((com.openrubicon.core.api.interactables.Player)sender).getPlayer();

        ChatMenu chatMenu = new ChatMenu("checky", player);

        chatMenu.getUserInterface().add(new CheckboxComponent().withLabel("Test the checkbox").withCommand(new TestCommand()).getLine());

        chatMenu.render();
    }
}
