package com.openrubicon.core.api.actionbar;

import com.openrubicon.core.api.cooldowns.CooldownManager;
import org.bukkit.entity.Player;

import java.util.ArrayDeque;

public class PlayerActionBar {
    private Player player;
    private ArrayDeque<ActionBarMessage> messages = new ArrayDeque<>();
    private ActionBarCooldown cooldown;

    public PlayerActionBar(Player player) {
        this.player = player;
        this.initializeCooldown();
    }

    public PlayerActionBar(Player player, ArrayDeque<ActionBarMessage> messages) {
        this.player = player;
        this.messages = messages;
        this.initializeCooldown();
    }

    private void initializeCooldown()
    {
        this.cooldown = new ActionBarCooldown(this.player.getUniqueId().toString(), "player");
        CooldownManager.add(this.cooldown);
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ArrayDeque<ActionBarMessage> getMessages() {
        return messages;
    }

    public void setMessages(ArrayDeque<ActionBarMessage> messages) {
        this.messages = messages;
    }

    public ActionBarCooldown getCooldown() {
        return cooldown;
    }

    public void setCooldown(ActionBarCooldown cooldown) {
        this.cooldown = cooldown;
    }

    public void add(ActionBarMessage message)
    {
        this.messages.addLast(message);
    }

    public ActionBarMessage remove()
    {
        return this.messages.remove();
    }

    public void addStart(ActionBarMessage message)
    {
        this.messages.addFirst(message);
    }

    public ActionBarMessage getNext()
    {
        return this.messages.peek();
    }
}
