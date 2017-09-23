package com.openrubicon.core.api.discord;

import com.openrubicon.core.RRPGCore;
import com.openrubicon.core.configuration.Configuration;
import com.openrubicon.core.database.models.DiscordTextChannel;
import com.openrubicon.core.helpers.Helpers;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.apache.commons.lang.RandomStringUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.sql2o.Connection;

import java.util.HashSet;
import java.util.UUID;

public class DiscordEventTestListener extends ListenerAdapter {

    public static HashSet<MessageChannel> channels = new HashSet<>();

    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        if (event.getAuthor().isBot()) return;
        // We don't want to respond to other bot accounts, including ourself
        Message message = event.getMessage();
        String content = message.getRawContent();
        // getRawContent() is an atomic getter
        // getContent() is a lazy getter which modifies the content for e.g. console view (strip discord formatting)
        if (content.equals("!rubicon-use-channel"))
        {
            MessageChannel channel = event.getChannel();

            // Important to call .queue() on the RestAction returned by sendMessage(...)
            //Bukkit.broadcastMessage(event.getChannel().getId());
            //Bukkit.broadcastMessage(event.getGuild().getId());
            //GuildImpl g = (GuildImpl)event.getGuild();
            //TextChannelImpl ch = new TextChannelImpl(event.getChannel().getIdLong(), );
            //Discord.getApi().getGuildById(event.getGuild().getIdLong());



            //MessageChannel ch = Discord.getApi().getTextChannelById(event.getChannel().getIdLong());
            //ch.sendMessage("COCK").queue();
            DiscordTextChannel txtChannels = new DiscordTextChannel(event.getChannel().getIdLong());

            String test = "SELECT count(id) FROM rubicon_discord_text_channels WHERE channel_id = :channel_id";
            int count = 0;
            try(Connection connection = RRPGCore.database.connection().get())
            {
                count = connection.createQuery(test).bind(txtChannels).executeScalar(Integer.class);
            }

            if(count < 1)
            {
                txtChannels.setDisabled(false);
                String sql = "INSERT INTO rubicon_discord_text_channels (channel_id, disabled) VALUES (:channel_id, :disabled)";
                try(Connection connection = RRPGCore.database.connection().get())
                {
                    connection.createQuery(sql, true).bind(txtChannels).executeUpdate().getKey();
                }

                channel.sendMessage("Registered Rubicon chat channel!").queue();
                channels.add(channel);
            } else {
                channel.sendMessage("This channel was already registered!").queue();
            }

        } else if(content.length() > 19 && content.substring(0,19).equals("!rubicon-verify-me ")) {
            String playerName = content.substring(19);
            Bukkit.broadcastMessage(playerName);
            for(Player player : Bukkit.getOnlinePlayers())
            {
                if(player.getName().equals(playerName))
                {
                    if(com.openrubicon.core.database.models.Player.isPlayerExist(RRPGCore.database.connection(), new com.openrubicon.core.database.models.Player(player.getUniqueId().toString())))
                    {
                        com.openrubicon.core.database.models.Player p = com.openrubicon.core.database.models.Player.getPlayer(RRPGCore.database.connection(), new com.openrubicon.core.database.models.Player(player.getUniqueId().toString()));
                        Bukkit.broadcastMessage(p.getUuid());
                        String token = RandomStringUtils.randomAlphanumeric(10);
                        p.setToken(token);
                        p.setDiscord_id(event.getAuthor().getIdLong());
                        com.openrubicon.core.database.models.Player.updatePlayer(RRPGCore.database.connection(), p);
                        player.sendMessage("Your secret token is: " + token);
                    }
                }
            }

        } else if(content.length() > 16 && content.substring(0,16).equals("!rubicon-verify ")) {
            String token = content.substring(16);
            for(com.openrubicon.core.database.models.Player player : com.openrubicon.core.database.models.Player.getPlayers(RRPGCore.database.connection()))
            {
                if(player.getToken().equals(token) && !token.equals(""))
                {
                    if(event.getAuthor().getIdLong() == player.getDiscord_id())
                    {
                        player.setToken("");
                        player.setVerified(1);
                        com.openrubicon.core.database.models.Player.updatePlayer(RRPGCore.database.connection(), player);
                        Bukkit.broadcastMessage("THIS VERIFIED");
                    }
                }
            }

        } else if(!content.substring(0, 1).equals("!"))
        {
            for(com.openrubicon.core.database.models.Player player : com.openrubicon.core.database.models.Player.getPlayers(RRPGCore.database.connection()))
            {
                Bukkit.broadcastMessage(Helpers.colorize(Configuration.PRIMARY_COLOR+"<"+ player.getDisplay_name()+Configuration.PRIMARY_COLOR+"> " + Configuration.RESET_FORMAT + event.getMessage().getContent()));
            }

        }
    }
}
