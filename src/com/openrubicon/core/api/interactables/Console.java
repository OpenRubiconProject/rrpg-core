package com.openrubicon.core.api.interactables;

import com.openrubicon.core.api.interactables.enums.InteractableSenderVisibility;
import com.openrubicon.core.api.interactables.enums.InteractableType;
import com.openrubicon.core.api.interactables.interfaces.Interactable;
import com.openrubicon.core.api.permission.interfaces.PermissionNode;
import com.openrubicon.core.helpers.Helpers;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;

public class Console implements Interactable {

    private ConsoleCommandSender console;

    public Console() {
        console = Bukkit.getConsoleSender();
    }

    @Override
    public void sendMessage(String message) {
        console.sendMessage(Helpers.colorize("[RRPG] " + message));
    }

    @Override
    public InteractableType getInteractableType() {
        return InteractableType.CONSOLE;
    }

    @Override
    public InteractableSenderVisibility getInteractableSenderVisibility() {
        return InteractableSenderVisibility.NOT_APPLICABLE;
    }

    @Override
    public String getId() {
        return "0";
    }

    @Override
    public boolean isAllowed(PermissionNode permissionNode) {
        return true;
    }
}
