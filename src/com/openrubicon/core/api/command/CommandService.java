package com.openrubicon.core.api.command;

import com.openrubicon.core.api.services.interfaces.Service;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class CommandService implements Service {

    private ArrayList<Command> commands = new ArrayList<>();

    private CommandExecutor commandExecutor;

    public CommandService(JavaPlugin plugin, ArrayList<Command> commands) {
        this.commands = commands;
        this.commandExecutor = new CommandExecutor(plugin);
    }

    public CommandExecutor getCommandExecutor() {
        return commandExecutor;
    }

    public ArrayList<Command> getCommands() {
        return commands;
    }

    public void addCommand(Command command)
    {
        this.commands.add(command);
    }

    @Override
    public ArrayList<String> getObservation() {
        ArrayList<String> observation = new ArrayList<>();
        for(Command command : this.getCommands())
        {
            observation.add(command.getCommandFormat());
        }
        return observation;
    }
}
