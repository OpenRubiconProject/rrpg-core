package com.openrubicon.core.api.interactables;

import com.openrubicon.core.api.interactables.enums.InteractableSenderVisibility;
import com.openrubicon.core.api.interactables.enums.InteractableType;
import com.openrubicon.core.api.interactables.interfaces.Interactable;

public class Connector implements Interactable {

    @Override
    public void sendMessage(String message) {

    }

    @Override
    public InteractableType getInteractableType() {
        return InteractableType.CONNECTOR;
    }

    @Override
    public InteractableSenderVisibility getInteractableSenderVisibility() {
        return InteractableSenderVisibility.NOT_APPLICABLE;
    }

    @Override
    public String getId() {
        return "";
    }
}
