package com.openrubicon.core.api.actionbar;

import com.openrubicon.core.api.cooldowns.interfaces.Cooldownable;
import com.openrubicon.core.api.enums.Priority;
import com.openrubicon.core.api.interfaces.Prioritable;
import com.openrubicon.core.api.message.Message;
import com.openrubicon.core.helpers.Helpers;

public class ActionBarMessage extends Message implements Cooldownable, Prioritable {

    private Priority priority;
    private int cooldownLengthTicks = 60;

    public ActionBarMessage(String message) {
        super(message);
        this.priority = Priority.NORMAL;
    }

    public ActionBarMessage(String message, Priority priority) {
        super(message);
        this.priority = priority;
    }

    public ActionBarMessage(String message, int cooldownLengthTicks) {
        super(message);
        this.cooldownLengthTicks = cooldownLengthTicks;
    }

    public ActionBarMessage(String message, Priority priority, int cooldownLengthTicks) {
        super(message);
        this.priority = priority;
        this.cooldownLengthTicks = cooldownLengthTicks;
    }

    @Override
    public int getCooldownLengthTicks() {
        return this.cooldownLengthTicks;
    }

    @Override
    public Priority getPriority() {
        return priority;
    }

}
