package com.openrubicon.core.api.discord;

import com.openrubicon.core.RRPGCore;
import com.openrubicon.core.api.command.CommandService;
import com.openrubicon.core.api.interactables.Discord;
import com.openrubicon.core.api.interactables.enums.InteractableSenderVisibility;
import com.openrubicon.core.database.models.DiscordAccount;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.util.Date;

public class DiscordEventListener extends ListenerAdapter {

    @Override
    public void onPrivateMessageReceived(PrivateMessageReceivedEvent e)
    {
        if (e.getAuthor().isBot())
            return;

        String text = e.getMessage().getContent();

        this.tempRegister(e.getAuthor());

        if(this.isCommand(text))
        {
            this.onSendCommand(new Discord(e.getChannel(), e.getAuthor(), InteractableSenderVisibility.PRIVATE), text);
        }
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent e)
    {
        if (e.getAuthor().isBot())
            return;

        if(e.getChannelType() == ChannelType.PRIVATE)
            return;

        String text = e.getMessage().getContent();

        this.tempRegister(e.getAuthor());

        if(this.isCommand(text))
        {
            this.onSendCommand(new Discord(e.getChannel(), e.getAuthor(), InteractableSenderVisibility.PUBLIC), text);
            e.getMessage().delete().complete();
        }
    }

    private boolean isCommand(String text)
    {
        return text.substring(0,1).equals("!");
    }

    private void onSendCommand(Discord discord, String command)
    {
        RRPGCore.services.getSerivce(CommandService.class).getCommandExecutor().onCommand(discord, command.substring(1));
    }

    private void tempRegister(User author)
    {
        DiscordAccount discordAccount = new DiscordAccount();

        discordAccount.setDiscord_id(author.getId());

        if(discordAccount.count("id").where("discord_id", ":discord_id").executeCount() == 1)
        {
            discordAccount = discordAccount.selectAll().where("discord_id", ":discord_id").executeFetchFirst(DiscordAccount.class);
            discordAccount.setLast_joined(new Date());
            discordAccount.update().set("last_joined").executeUpdate();
        }
        else
        {
            discordAccount.setDisplay_name(author.getName());
            discordAccount.setUsername(author.getName());
            discordAccount.setLast_joined(new Date());

            discordAccount.insert("discord_id, username, display_name, last_joined", ":discord_id, :username, :display_name, :last_joined").executeInsert();
        }
    }

}
