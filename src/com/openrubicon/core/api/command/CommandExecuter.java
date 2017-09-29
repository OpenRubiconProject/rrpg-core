package com.openrubicon.core.api.command;

import com.openrubicon.core.RRPGCore;
import com.openrubicon.core.api.interactables.Console;
import com.openrubicon.core.api.interactables.Interactable;
import com.openrubicon.core.api.interactables.enums.InteractableType;
import org.bukkit.command.*;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public class CommandExecuter implements CommandExecutor {

    private String command = "rrpg";

    public CommandExecuter(JavaPlugin plugin) {
        plugin.getCommand(command).setExecutor(this);
    }

    public final boolean onCommand(Interactable sender, String command)
    {
        String[] parts = command.split(" ");

        if(!parts[0].equals(command))
            return false;

        String[] arguments = Arrays.copyOfRange(parts, 2, parts.length);

        return this.onCommand(sender, parts[1], arguments);
    }

    public final boolean onCommand(Interactable sender, String command, String[] args)
    {
        if(!command.equals(this.command))
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
