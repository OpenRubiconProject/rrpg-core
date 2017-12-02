package com.openrubicon.core.api.permission.interfaces;

public interface PermissionNode {

    String getNode();

    default boolean isOpOverride()
    {
        return true;
    }

    default boolean isCreativeOverride()
    {
        return false;
    }

    default String getNoPermissionError()
    {
        return "You don't have permission to do this.";
    }

}
