package com.openrubicon.core.helpers;

public class UUID {

    private long id;

    public UUID(long id)
    {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void generate(long previous)
    {
        this.id = previous + 1;
    }

    public boolean isValid()
    {
        return this.id >= 0;
    }

}
