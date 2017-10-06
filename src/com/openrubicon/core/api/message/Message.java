package com.openrubicon.core.api.message;

import java.util.Date;

abstract public class Message {

    private String message;
    private Date created_at;

    public Message(String message, Date created_at) {
        this.message = message;
        this.created_at = created_at;
    }

    public String getMessage() {
        return message;
    }

    public Date getCreated_at() {
        return created_at;
    }

}
