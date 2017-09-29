package com.openrubicon.core.api.interactables;

import com.openrubicon.core.api.interactables.enums.InteractableType;

public class Connector implements Interactable {

    @Override
    public void sendMessage(String message) {

    }

    @Override
    public InteractableType getInteractableType() {
        return InteractableType.CONNECTOR;
    }
}
