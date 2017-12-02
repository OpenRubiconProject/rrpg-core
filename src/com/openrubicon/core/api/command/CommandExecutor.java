package com.openrubicon.core.api.command;

import com.openrubicon.core.RRPGCore;
import com.openrubicon.core.api.interactables.Console;
import com.openrubicon.core.api.interactables.interfaces.Interactable;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public class CommandExecutor implements org.bukkit.command.CommandExecutor {

    public static final String commandPrefix = "rrpg";

    public CommandExecutor(JavaPlugin plugin) {
        plugin.getCommand(commandPrefix).setExecutor(this);
    }

    public final boolean onCommand(Interactable sender, String command)
    {
        String[] parts = command.split(" ");
        if(!parts[0].equals(commandPrefix))
            return false;

        String[] arguments = {};

        if(parts.length > 1)
            arguments = Arrays.copyOfRange(parts, 1, parts.length);

        return this.onCommand(sender, parts[0], arguments);
    }

    public final boolean onCommand(Interactable sender, String command, String[] args)
    {
        if(!command.equals(commandPrefix))
            return false;

        String commandLabel = "";
        String[] arguments = {};
        if(args.length > 0)
        {
            commandLabel = args[0];
            arguments = Arrays.copyOfRange(args, 1, args.length);
        }

        for(Command commandHandle : RRPGCore.services.getSerivce(CommandService.class).getCommands())
        {
            if(commandHandle.onCommand(sender, commandLabel, arguments))
                return true;
        }

        return false;
    }

    @Override
    public final boolean onCommand(CommandSender commandSender, org.bukkit.command.Command command, String label, String[] args)
    {
        Interactable interactable;
        if(commandSender instanceof Player)
            interactable = new com.openrubicon.core.api.interactables.Player((Player)commandSender);
        else
            interactable = new Console();

        return this.onCommand(interactable, label, args);
    }
}
