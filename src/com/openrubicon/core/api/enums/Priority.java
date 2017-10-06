package com.openrubicon.core.api.enums;

public enum Priority {

    HIGH(5),
    ABOVE_NORMAL(4),
    NORMAL(3),
    BELOW_NORMAL(2),
    LOW(1);

    private final int priority;

    Priority(final int priority)
    {
        this.priority = priority;
    }

    @Override
    public String toString()
    {
        return String.valueOf(this.priority);
    }

    public int toInt()
    {
        return this.priority;
    }

    public static Priority fromInt(int priority)
    {
        for (Priority n : Priority.values()) {
            if (n.priority == priority) {
                return n;
            }
        }

        return NORMAL;
    }

    public static Priority fromString(String priority)
    {
        int p = Integer.parseInt(priority);
        for (Priority n : Priority.values()) {
            if (n.priority == p) {
                return n;
            }
        }

        return NORMAL;
    }

    public boolean isHigher(Priority priority, Priority compare)
    {
        return priority.toInt() > compare.toInt();
    }

    public boolean isLower(Priority priority, Priority compare)
    {
        return priority.toInt() < compare.toInt();
    }

    public boolean isHigher(Priority compare )
    {
        return this.toInt() > compare.toInt();
    }

    public boolean isLower(Priority compare)
    {
        return this.toInt() < compare.toInt();
    }

}
