package com.openrubicon.core.api.interactables;

import com.openrubicon.core.api.interactables.enums.InteractableSenderVisibility;
import com.openrubicon.core.api.interactables.enums.InteractableType;
import com.openrubicon.core.api.interactables.interfaces.Interactable;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;

public class Console implements Interactable {

    private ConsoleCommandSender console;

    public Console() {
        console = Bukkit.getConsoleSender();
    }

    @Override
    public void sendMessage(String message) {
        console.sendMessage("[RRPG] " + message);
    }

    @Override
    public InteractableType getInteractableType() {
        return InteractableType.CONSOLE;
    }

    @Override
    public InteractableSenderVisibility getInteractableSenderVisibility() {
        return InteractableSenderVisibility.NOT_APPLICABLE;
    }
}
