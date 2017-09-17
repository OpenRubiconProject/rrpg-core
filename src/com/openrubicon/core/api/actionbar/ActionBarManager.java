package com.openrubicon.core.api.actionbar;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class ActionBarManager {

    protected static HashMap<Player, PlayerActionBar> playerMessages = new HashMap<>();

    public ActionBarManager()
    {

    }

    public static void process()
    {
        for(PlayerActionBar actionBars : ActionBarManager.playerMessages.values())
        {
            if(actionBars.getCooldown().isCooldown())
                continue;

            if(CooldownManager.getEntities().get(actionBars.getPlayer()) != null)
            {
                CooldownManager.getEntities().get(actionBars.getPlayer()).clean();

                if(actionBars.getMessages().isEmpty() && !CooldownManager.getEntities().get(actionBars.getPlayer()).hasCooldowns())
                    continue;
            }

            if(actionBars.getMessages().isEmpty() || (CombatManager.isInCombat(actionBars.getPlayer()) && actionBars.getNext().getPriority().isLower(Priority.HIGH)))
            {
                if(CooldownManager.getEntities().get(actionBars.getPlayer()) != null && CooldownManager.getEntities().get(actionBars.getPlayer()) instanceof PlayerCooldowns)
                {
                    //Bukkit.broadcastMessage("BOB IS A BOOB");
                    PlayerCooldowns playerCooldowns = (PlayerCooldowns)CooldownManager.getEntities().get(actionBars.getPlayer());
                    actionBars.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(Helpers.colorize(Configuration.HEADING_COLOR + Configuration.BOLD + "Cooldowns: " + Configuration.RESET_FORMAT + playerCooldowns.getActionbar())));
                    actionBars.getCooldown().setLength(10);
                }

                continue;
            } else {
                actionBars.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(Helpers.colorize(actionBars.remove().getMessage())));
                actionBars.getCooldown().setLength(60);
            }

            CooldownManager.start(actionBars.getCooldown());
        }
    }

    public static void interrupt(Player player, String message)
    {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(Helpers.colorize(message)));

        if(!ActionBarManager.playerMessages.containsKey(player))
            ActionBarManager.playerMessages.put(player, new PlayerMessages(player));

        ActionBarManager.playerMessages.get(player).getCooldown().setLength(10);
        CooldownManager.start(ActionBarManager.playerMessages.get(player).getCooldown());
    }

    public static void queueMessage(Player player, Message message)
    {
        if(!ActionBarManager.playerMessages.containsKey(player))
            ActionBarManager.playerMessages.put(player, new PlayerMessages(player));

        ActionBarManager.playerMessages.get(player).add(message);

        //CooldownManager.start(ActionBarManager.playerMessages.get(player).getCooldown());
    }

    public static void addPlayer(Player player)
    {
        if(!ActionBarManager.playerMessages.containsKey(player))
            ActionBarManager.playerMessages.put(player, new PlayerActionBar(player));
    }

    public static void removePlayer(Player player)
    {
        if(ActionBarManager.playerMessages.containsKey(player))
            ActionBarManager.playerMessages.remove(player);
    }

}
