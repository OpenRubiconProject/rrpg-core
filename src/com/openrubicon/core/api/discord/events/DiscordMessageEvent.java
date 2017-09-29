package com.openrubicon.core.api.discord.events;

import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;

abstract public class DiscordMessageEvent extends DiscordEvent {

    private MessageChannel channel;
    private Message message;

    public DiscordMessageEvent(MessageChannel channel, Message message) {
        this.channel = channel;
        this.message = message;
    }

    public MessageChannel getChannel() {
        return channel;
    }

    public Message getMessage() {
        return message;
    }

}
