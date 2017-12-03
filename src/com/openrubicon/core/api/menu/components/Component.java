package com.openrubicon.core.api.menu.components;

import com.openrubicon.core.api.menu.events.interfaces.EventType;

abstract public class Component<T> {

    private String label;

    private EventType event = null;

    public EventType getEvent() {
        return event;
    }

    public T setEvent(EventType event) {
        this.event = event;
        this.initEventParameters();
        return (T)(this);
    }

    public String getLabel() {
        return label;
    }

    public T setLabel(String label) {
        this.label = label;
        return (T)(this);
    }

    abstract public T initEventParameters();

    public boolean hasEvent()
    {
        return this.event != null;
    }

}
