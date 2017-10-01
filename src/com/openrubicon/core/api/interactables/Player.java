package com.openrubicon.core.api.interactables;

import com.openrubicon.core.api.interactables.enums.InteractableSenderVisibility;
import com.openrubicon.core.api.interactables.enums.InteractableType;

public class Player implements Interactable {

    private org.bukkit.entity.Player player;

    public Player(org.bukkit.entity.Player player) {
        this.player = player;
    }

    @Override
    public void sendMessage(String message) {
        player.sendMessage(message);
    }

    @Override
    public InteractableType getInteractableType() {
        return InteractableType.PLAYER;
    }

    public org.bukkit.entity.Player getPlayer() {
        return player;
    }

    @Override
    public InteractableSenderVisibility getInteractableSenderVisibility() {
        return InteractableSenderVisibility.NOT_APPLICABLE;
    }
}
