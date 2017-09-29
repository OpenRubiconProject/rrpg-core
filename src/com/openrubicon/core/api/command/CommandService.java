package com.openrubicon.core.api.command;

import com.openrubicon.core.api.services.interfaces.Service;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class CommandService implements Service {

    private ArrayList<Command> commands = new ArrayList<>();

    private CommandExecutor commandExecutor;

    public CommandService(JavaPlugin plugin, ArrayList<Command> commands) {
        this.commands = commands;
        this.commandExecutor = new CommandExecuter(plugin);
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
}
