package com.openrubicon.core.api.menu.enums;

public enum EventType {

    COMMAND("menu.type.command");

    private final String name;

    EventType(final String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return this.name;
    }

    public static EventType fromString(String name)
    {
        for (EventType n : EventType.values()) {
            if (n.name.equalsIgnoreCase(name)) {
                return n;
            }
        }

        return null;
    }

}
