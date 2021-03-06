package com.openrubicon.core.api.interactables;

import com.openrubicon.core.api.interactables.enums.InteractableSenderVisibility;
import com.openrubicon.core.api.interactables.enums.InteractableType;
import com.openrubicon.core.api.interactables.interfaces.Interactable;
import com.openrubicon.core.api.permission.Permission;
import com.openrubicon.core.api.permission.interfaces.PermissionNode;
import com.openrubicon.core.helpers.Helpers;

public class Player implements Interactable {

    private org.bukkit.entity.Player player;

    public Player(org.bukkit.entity.Player player) {
        this.player = player;
    }

    @Override
    public void sendMessage(String message) {
        player.sendMessage(Helpers.colorize(message));
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

    @Override
    public String getId() {
        return this.player.getUniqueId().toString();
    }

    @Override
    public boolean isAllowed(PermissionNode permissionNode) {
        return Permission.has(player, permissionNode);
    }
}
