package com.openrubicon.core.api.interactables;

import com.openrubicon.core.api.interactables.enums.InteractableType;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;

public class Discord implements Interactable {

    private MessageChannel channel;
    private User author;

    public Discord(MessageChannel channel) {
        this.channel = channel;
    }

    public Discord(MessageChannel channel, User author) {
        this.channel = channel;
        this.author = author;
    }

    public User getAuthor() {
        return author;
    }

    @Override
    public void sendMessage(String message) {
        this.channel.sendMessage(message).queue();
    }

    @Override
    public InteractableType getInteractableType() {
        return InteractableType.DISCORD;
    }
}
