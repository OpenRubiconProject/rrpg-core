package com.openrubicon.core.api.interactables;

import com.openrubicon.core.api.interactables.enums.InteractableSenderVisibility;
import com.openrubicon.core.api.interactables.enums.InteractableType;

public interface Interactable {
    void sendMessage(String message);
    InteractableType getInteractableType();
    InteractableSenderVisibility getInteractableSenderVisibility();
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
