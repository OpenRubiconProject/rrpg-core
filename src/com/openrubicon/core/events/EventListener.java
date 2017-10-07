package com.openrubicon.core.events;

import com.openrubicon.core.api.actionbar.ActionBarManager;
import com.openrubicon.core.api.cooldowns.CooldownManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class EventListener implements Listener {

    @EventHandler
    public void onFiveTick(FiveTickEvent e)
    {
        CooldownManager.passTime(5);
        ActionBarManager.process();
    }


}
