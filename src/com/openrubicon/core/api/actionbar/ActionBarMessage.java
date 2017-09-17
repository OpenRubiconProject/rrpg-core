package com.openrubicon.core.api.actionbar;

import com.openrubicon.core.enums.Priority;
import com.openrubicon.core.helpers.Helpers;

public class ActionBarMessage {

    private String message;
    private Priority priority;

    public ActionBarMessage()
    {
        this.message = "";
        this.priority = Priority.NORMAL;
    }

    public ActionBarMessage(String message) {
        this.message = message;
        this.priority = Priority.NORMAL;
    }

    public ActionBarMessage(String message, Priority priority) {
        this.message = message;
        this.priority = priority;
    }

    public String getMessage() {
        return Helpers.colorize(message);
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

}
