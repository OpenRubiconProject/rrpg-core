package com.openrubicon.core.api.discord;

import com.openrubicon.core.RRPGCore;
import com.openrubicon.core.api.command.CommandService;
import com.openrubicon.core.api.interactables.Discord;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.bukkit.Bukkit;

public class DiscordEventListener extends ListenerAdapter {

    @Override
    public void onPrivateMessageReceived(PrivateMessageReceivedEvent e)
    {
        if (e.getAuthor().isBot())
            return;

        if(e.getMessage().getContent().substring(0,1).equals("!"))
        {

            RRPGCore.services.getSerivce(CommandService.class).getCommandExecutor().onCommand(new Discord(e.getChannel(), e.getAuthor()), e.getMessage().getContent().substring(1));
        } else {
        }
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {

    }

}
