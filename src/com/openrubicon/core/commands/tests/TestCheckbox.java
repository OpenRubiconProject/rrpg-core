package com.openrubicon.core.commands.tests;

import com.openrubicon.core.api.command.Command;
import com.openrubicon.core.api.interactables.enums.InteractableType;
import com.openrubicon.core.api.interactables.interfaces.Interactable;
import com.openrubicon.core.api.menu.MenuBuilder;
import com.openrubicon.core.api.menu.components.Checkbox;
import com.openrubicon.core.api.menu.components.Component;
import com.openrubicon.core.api.menu.defaults.MenuFormat;
import com.openrubicon.core.api.menu.defaults.MenuRender;
import com.openrubicon.core.api.menu.enums.MenuType;
import com.openrubicon.core.api.menu.events.types.CommandEvent;
import com.openrubicon.core.api.menu.interfaces.MenuTemplate;
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

        MenuTemplate template = new MenuTemplate() {
            @Override
            public String getName() {
                return "checky test";
            }

            @Override
            public ArrayList<Component> getComponents() {
                ArrayList<Component> components = new ArrayList<>();
                components.add(new Checkbox(false).setLabel("this is a test0").setEvent(new CommandEvent(new TestCommand())));
                components.add(new Checkbox(true).setLabel("this is a test1"));
                components.add(new Checkbox(true).setLabel("this is a test2"));
                components.add(new Checkbox(false).setLabel("this is a test3"));
                components.add(new Checkbox(true).setLabel("this is a test4"));
                components.add(new Checkbox(true).setLabel("this is a test5").setEvent(new CommandEvent(new TestCommand())));
                components.add(new Checkbox(false).setLabel("this is a test6"));
                components.add(new Checkbox(false).setLabel("this is a test7"));
                components.add(new Checkbox(false).setLabel("this is a test8"));
                components.add(new Checkbox(true).setLabel("this is a test9"));
                components.add(new Checkbox(false).setLabel("this is a test10"));
                return components;
            }
        };

        new MenuBuilder(template, MenuType.INVENTORY).withFormat(new MenuFormat()).withRender(new MenuRender()).withViewer(player).draw();
    }
}
