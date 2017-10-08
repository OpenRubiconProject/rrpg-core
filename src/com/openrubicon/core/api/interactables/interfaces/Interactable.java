package com.openrubicon.core.api.interactables.interfaces;

import com.openrubicon.core.api.interactables.enums.InteractableSenderVisibility;
import com.openrubicon.core.api.interactables.enums.InteractableType;

import java.util.ArrayList;

public interface Interactable {
    void sendMessage(String message);
    default void sendMessage(ArrayList<String> message)
    {
        for(int i = 0; i < message.size(); i++)
        {
            this.sendMessage(message.get(i));
        }
    }
    InteractableType getInteractableType();
    InteractableSenderVisibility getInteractableSenderVisibility();
    String getId();
    default void senderTypeError()
    {
        sendMessage("This type of client doesn't support this command.");
    }
    default void visiblityTypeError() {
        sendMessage("This is not the right place to perform this command.");
    }
    default void permissionError() {
        sendMessage("You don't have permission to do this.");
    }
    default void error() {
        sendMessage("An Error has Occurred!");
    }
}
