package com.openrubicon.core.api.command;

import com.openrubicon.core.api.interactables.interfaces.Interactable;
import com.openrubicon.core.api.interactables.enums.InteractableSenderVisibility;
import com.openrubicon.core.api.interactables.enums.InteractableType;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;

abstract public class Command {

    private int argsCount = 0;
    private String command = "";
    private LinkedHashSet<String> switchArgs = new LinkedHashSet<>();
    private boolean debugCommand = false;

    public Command() {
        this.parseCommandFormat();
    }

    public Command(boolean debugCommand) {
        this.debugCommand = debugCommand;
        this.parseCommandFormat();
    }

    public void setDebugCommand(boolean debugCommand) {
        this.debugCommand = debugCommand;
    }

    abstract public String getCommandFormat();
    abstract public ArrayList<InteractableType> getAllowedSenderTypes();
    abstract public void handle(Interactable sender, String[] args);

    public ArrayList<InteractableSenderVisibility> getAllowedSenderVisiblity()
    {
        return new ArrayList<>();
    }

    /**
     * Takes string in the form of:
     *      money pay $ $
     * and converts it into the applicable command handler where $ represents variables.
     */
    private void parseCommandFormat()
    {
        String commandFormat = this.getCommandFormat();
        String[] parts = commandFormat.split(" ");
        this.argsCount = parts.length - 1;
        this.command = parts[0];
        if(this.argsCount < 1)
            return;
        for(int i = 1; i < parts.length; i++)
        {
            if(!parts[i].equals("$"))
                switchArgs.add(parts[i]);
            else
                break;

        }
    }

    public final boolean onCommand(Interactable sender, String command, String[] args)
    {
        if(!command.equals(this.command))
            return false;

        int i = 0;
        for(String arg : this.switchArgs)
        {
            if(!arg.equals(args[i]))
            {
                //sender.sendMessage("Incompatible arguments. Please check the syntax of this command and try again.");
                return false;
            }

            i++;
        }

        if(args.length != this.argsCount)
        {
            sender.sendMessage("Incompatible arguments. Please check the syntax of this command and try again.");
            return false;
        }

        if(!this.getAllowedSenderTypes().contains(sender.getInteractableType()))
        {
            sender.senderTypeError();
            return false;
        }

        if(!this.getAllowedSenderVisiblity().isEmpty() && !this.getAllowedSenderVisiblity().contains(sender.getInteractableSenderVisibility()))
        {
            sender.visiblityTypeError();
            return false;
        }

        if(debugCommand)
            this.debugCommands(args);

        String[] arguments = Arrays.copyOfRange(args, i, args.length);

        this.handle(sender, arguments);

        return true;
    }

    private void debugCommands(String[] args)
    {
        Bukkit.getLogger().info("===Debugging Command===");

        Bukkit.getLogger().info("Command: " + this.command);
        Bukkit.getLogger().info("Command Format: " + this.getCommandFormat());
        Bukkit.getLogger().info("Args:");

        if (args != null){
            for(int i=0; i<args.length; i++) {
                Bukkit.getLogger().info("Arg[" + i + "]: " + args[i]);
            }
        } else {
            Bukkit.getLogger().info("No arguments input.");
        }
        Bukkit.getLogger().info("===End Debug===");
    }
}
