package com.openrubicon.core.api.interactables;

import com.openrubicon.core.api.interactables.enums.InteractableType;

public interface Interactable {
    void sendMessage(String message);
    InteractableType getInteractableType();
}
