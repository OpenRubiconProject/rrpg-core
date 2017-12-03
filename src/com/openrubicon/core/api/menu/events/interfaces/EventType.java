package com.openrubicon.core.api.menu.events.interfaces;

import org.bukkit.entity.Player;

public interface EventType {
    default void setParameters(String... parameters) {}
    void trigger(Player player);
}
