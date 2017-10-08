package com.openrubicon.core.api.interactables;

import com.openrubicon.core.api.interactables.enums.InteractableSenderVisibility;
import com.openrubicon.core.api.interactables.enums.InteractableType;
import com.openrubicon.core.api.interactables.interfaces.Interactable;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;

public class Discord implements Interactable {

    private MessageChannel channel;
    private User author;

    private InteractableSenderVisibility interactableSenderVisibility;

    public Discord(MessageChannel channel) {
        this.channel = channel;
    }

    public Discord(MessageChannel channel, User author) {
        this.channel = channel;
        this.author = author;
        this.interactableSenderVisibility = InteractableSenderVisibility.PUBLIC;
    }

    public Discord(MessageChannel channel, User author, InteractableSenderVisibility interactableSenderVisibility) {
        this.channel = channel;
        this.author = author;
        this.interactableSenderVisibility = interactableSenderVisibility;
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

    @Override
    public InteractableSenderVisibility getInteractableSenderVisibility() {
        return this.interactableSenderVisibility;
    }

    @Override
    public String getId() {
        return this.author.getId();
    }
}
