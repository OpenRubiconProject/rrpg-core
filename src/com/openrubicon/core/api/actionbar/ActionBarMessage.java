package com.openrubicon.core.api.actionbar;

import com.openrubicon.core.api.enums.Priority;
import com.openrubicon.core.helpers.Helpers;

public class ActionBarMessage {

    private String message;
    private Priority priority;
    private int length = 60;

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

    public ActionBarMessage(String message, int length) {
        this.message = message;
        this.length = length;
    }

    public ActionBarMessage(String message, Priority priority, int length) {
        this.message = message;
        this.priority = priority;
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
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
