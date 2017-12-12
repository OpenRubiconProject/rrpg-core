package com.openrubicon.core.api.command;

import com.openrubicon.core.api.interactables.interfaces.Interactable;
import com.openrubicon.core.api.interactables.enums.InteractableSenderVisibility;
import com.openrubicon.core.api.interactables.enums.InteractableType;
import com.openrubicon.core.api.permission.interfaces.PermissionNode;
import com.openrubicon.core.api.utility.DynamicPrimitive;
import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;

abstract public class Command {

    private int argsCount = 0;
    private String command = "";
    private LinkedHashSet<String> switchArgs = new LinkedHashSet<>();

    private int optionalParameterCount = 0;

    private ArrayList<Object> variableArgTypes = new ArrayList<>();
    private ArrayList<DynamicPrimitive> variableArgs = new ArrayList<>();

    private boolean debugCommand = false;

    public Command() {
        this.parseCommandFormat();
    }

    public Command(boolean debugCommand) {
        this.debugCommand = debugCommand;
        this.parseCommandFormat();
    }

    private ArrayList<Object> getVariableArgTypes() {
        return variableArgTypes;
    }

    private ArrayList<DynamicPrimitive> getVariableArgs() {
        return variableArgs;
    }

    public boolean isDebugCommand() {
        return debugCommand;
    }

    public void setDebugCommand(boolean debugCommand) {
        this.debugCommand = debugCommand;
    }

    abstract public String getCommandFormat();
    abstract public ArrayList<InteractableType> getAllowedSenderTypes();
    //abstract public void handle(Interactable sender, String[] args);
    abstract public void handle(Interactable sender, ArrayList<DynamicPrimitive> args);

    public ArrayList<InteractableSenderVisibility> getAllowedSenderVisiblity()
    {
        return new ArrayList<>();
    }

    // All of the listed permissions will be required. It's an AND, not an OR
    public ArrayList<PermissionNode> getPermissions() { return new ArrayList<>(); }

    public String getExampleCommand()
    {
        String command = "/" + CommandExecutor.commandPrefix;

        command += " " + this.command.split(",")[0];

        for(String arg : this.switchArgs)
        {
            command += " " + arg.split(",")[0];
        }

        return command;
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
            if(parts[i].equals("$") || parts[i].equals("$s"))
                variableArgTypes.add("");

            else if(parts[i].equals("$n"))
                variableArgTypes.add(0.0);

            else if(parts[i].equals("$b"))
                variableArgTypes.add(false);

            else if(i == parts.length - 1 && parts[i].matches("^[|]\\d$"))
            {
                this.optionalParameterCount = Integer.parseInt(parts[i].substring(1));
                if(this.optionalParameterCount > 0)
                {
                    for(int j = 0; j < optionalParameterCount; j++)
                        variableArgTypes.add("");
                    this.argsCount--;
                }
            }
            else
                switchArgs.add(parts[i]);
        }
    }

    public boolean onCommand(Interactable sender, String command, String[] args)
    {
        //Bukkit.broadcastMessage(args.length + "");

        ArrayList<String> commandAliases = new ArrayList<>(Arrays.asList(this.command.split(",")));

        // Only the first word
        if(!commandAliases.contains(command))
            return false;

        // Denies based on permission nodes
        for(PermissionNode permissionNode : this.getPermissions())
        {
            if(!sender.isAllowed(permissionNode))
            {
                sender.sendMessage(permissionNode.getNoPermissionError());
                if(this.isDebugCommand())
                {
                    sender.sendMessage("Missing Node: " + permissionNode.getNode());
                }
                return false;
            }
        }

        // Checks whether the remaining text arguments are correct.
        // Switches between which command class to use
        int i = 0;
        for(String arg : this.switchArgs)
        {
            ArrayList<String> switchArgAliases = new ArrayList<>(Arrays.asList(arg.split(",")));
            if(!switchArgAliases.contains(args[i]))
            {
                //sender.sendMessage("Incompatible arguments. Please check the syntax of this command and try again.");
                return false;
            }

            i++;
        }

        // Checks whether we have a compatible amount of remaining arguments
        if(args.length < this.argsCount || args.length > this.argsCount + this.optionalParameterCount)
        {
            //sender.sendMessage("Incompatible arguments. Please check the syntax of this command and try again.");
            return false;
        }

        //int variableArgCount = this.argsCount - this.switchArgs.size();

        // Checking if the variable arguments are the right types
        // If they are set their value into them
        int j = this.switchArgs.size();
        for(Object object : this.getVariableArgTypes())
        {
            if(j >= args.length && this.optionalParameterCount > 0)
                break;

            if(object instanceof Number)
            {
                if(NumberUtils.isNumber(args[j]))
                    this.variableArgs.add(new DynamicPrimitive<>(Double.parseDouble(args[j])));
                else
                    return false;
            }
            else if(object instanceof Boolean)
            {
                if("true".equalsIgnoreCase(args[j]) || "false".equalsIgnoreCase(args[j]))
                    this.variableArgs.add(new DynamicPrimitive<>(Boolean.parseBoolean(args[j])));
                else
                    return false;
            }
            else
            {
                this.variableArgs.add(new DynamicPrimitive<>(args[j]));
            }
            j++;
        }

        // Will output the objects
        /*for(Object object : this.getVariableArgs())
        {
            Bukkit.broadcastMessage(object.toString());
        }*/

        // Ensures we are a valid sender type
        if(!this.getAllowedSenderTypes().contains(sender.getInteractableType()))
        {
            sender.senderTypeError();
            return false;
        }

        // Ensures we are sending from an allowed channel (public, private)
        if(!this.getAllowedSenderVisiblity().isEmpty() && !this.getAllowedSenderVisiblity().contains(sender.getInteractableSenderVisibility()))
        {
            sender.visiblityTypeError();
            return false;
        }

        if(debugCommand)
            this.debugCommands(args);

        //String[] arguments = Arrays.copyOfRange(args, i, args.length);

        this.handle(sender, this.getVariableArgs());

        this.getVariableArgs().clear();

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
