package com.openrubicon.core.api.actionbar;

import com.openrubicon.core.helpers.Helpers;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

import java.util.HashMap;

public final class ActionBarManager {

    protected static HashMap<Player, PlayerActionBar> playerActionBars = new HashMap<>();

    public static void process()
    {
        for(PlayerActionBar actionBar : ActionBarManager.playerActionBars.values())
        {
            if(actionBar.getCooldown().isOnCooldown())
                continue;

            ActionBarMessage abMessage = actionBar.remove();
            if(abMessage == null)
                continue;

            actionBar.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(Helpers.colorize(abMessage.getMessage())));
            actionBar.getCooldown().setLength(abMessage.getLength());
            ActionBarCooldownManager.start(actionBar.getCooldown());
        }
    }

    public static void interrupt(Player player, ActionBarMessage message)
    {
        ActionBarManager.queueMessage(player, message);

        ActionBarCooldown cooldown = ActionBarCooldownManager.getActionBar(player);
        if(cooldown == null)
            return;

        ActionBarCooldownManager.skip(cooldown);
    }

    public static void queueMessage(Player player, ActionBarMessage message)
    {
        ActionBarManager.addPlayer(player);

        ActionBarManager.playerActionBars.get(player).add(message);
    }

    public static void addPlayer(Player player)
    {
        if(!ActionBarManager.playerActionBars.containsKey(player))
            ActionBarManager.playerActionBars.put(player, new PlayerActionBar(player));
    }

    public static void removePlayer(Player player)
    {
        if(ActionBarManager.playerActionBars.containsKey(player))
            ActionBarManager.playerActionBars.remove(player);
    }

}
