package com.openrubicon.core.events;

import com.openrubicon.core.api.discord.DiscordEventTestListener;
import net.dv8tion.jda.core.entities.MessageChannel;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class EventListener implements Listener {

    @EventHandler
    public void onAsyncPlayerChat(AsyncPlayerChatEvent e)
    {
        for(MessageChannel channel : DiscordEventTestListener.channels)
        {
            channel.sendMessage("["+e.getPlayer().getName()+"] " + e.getMessage()).queue();
        }
    }

    /*@EventHandler
    public void onPlayerJoin(PlayerJoinEvent e)
    {
        if(!Player.isPlayerExist(RRPGCore.database.connection(), new Player(e.getPlayer().getUniqueId().toString())))
        {
            Player.insertPlayer(RRPGCore.database.connection(), new Player(e.getPlayer().getUniqueId().toString(), e.getPlayer().getName(), e.getPlayer().getDisplayName()));
        } else {
            Player player = Player.getPlayer(RRPGCore.database.connection(), new Player(e.getPlayer().getUniqueId().toString()));
            player.setLast_joined(new Date());
            Player.updatePlayer(RRPGCore.database.connection(), player);
        };
    }*/



}
