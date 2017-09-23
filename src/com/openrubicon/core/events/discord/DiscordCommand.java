package com.openrubicon.core.events.discord;

import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;

public class DiscordCommand extends DiscordMessageEvent {
    public DiscordCommand(MessageChannel channel, Message message) {
        super(channel, message);
    }

    /*private

    public DiscordCommand(MessageChannel channel, Message message) {
        super(channel, message);
    }*/
}
