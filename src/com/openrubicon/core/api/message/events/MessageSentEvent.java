package com.openrubicon.core.api.message.events;

import com.openrubicon.core.api.events.Event;
import com.openrubicon.core.api.message.Message;

public class MessageSentEvent extends Event {

    private Message message;

    public MessageSentEvent(Message message) {
        this.message = message;
    }

    public Message getMessage() {
        return message;
    }

}
