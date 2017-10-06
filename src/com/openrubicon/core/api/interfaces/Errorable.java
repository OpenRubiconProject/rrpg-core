package com.openrubicon.core.api.interfaces;

public interface Errorable {

    boolean isErrored();
    default String getError()
    {
        return "An error has occurred.";
    }

}
