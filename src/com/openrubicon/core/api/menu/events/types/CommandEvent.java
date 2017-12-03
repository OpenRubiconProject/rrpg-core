package com.openrubicon.core.api.menu.events.types;

import com.openrubicon.core.api.command.Command;
import com.openrubicon.core.api.menu.events.interfaces.EventType;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;

public class CommandEvent implements EventType {

    private Command command;

    private ArrayList<String> parameters = new ArrayList<>();

    public CommandEvent(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }

    public String getCommandString()
    {
        String command = this.getCommand().getExampleCommand();

        for(int i = 0; i < this.getParameters().size(); i++)
        {
            command += " " + this.getParameters().get(i);
        }

        return command;
    }

    @Override
    public void setParameters(String... parameters) {
        this.parameters = new ArrayList<>(Arrays.asList(parameters));
    }

    public ArrayList<String> getParameters() {
        return parameters;
    }

    @Override
    public void trigger(Player player) {
        player.performCommand(this.getCommandString().substring(1));
    }
}
