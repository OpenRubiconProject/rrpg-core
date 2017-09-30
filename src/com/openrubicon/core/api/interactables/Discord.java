package com.openrubicon.core.api.interactables;

import com.openrubicon.core.api.interactables.enums.InteractableType;
import net.dv8tion.jda.core.entities.MessageChannel;

public class Discord implements Interactable {

    private MessageChannel channel;

    public Discord(MessageChannel channel) {
        this.channel = channel;
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
