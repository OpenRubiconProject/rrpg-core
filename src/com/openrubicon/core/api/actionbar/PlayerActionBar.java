package com.openrubicon.core.api.actionbar;

import org.bukkit.entity.Player;

import java.util.concurrent.LinkedBlockingQueue;

public class PlayerActionBar {
    private Player player;
    private LinkedBlockingQueue<ActionBarMessage> messages = new LinkedBlockingQueue<>();
    private ActionBarCooldown cooldown;

    public PlayerActionBar(Player player) {
        this.player = player;
        this.initializeCooldown();
    }

    public PlayerActionBar(Player player, LinkedBlockingQueue<ActionBarMessage> messages) {
        this.player = player;
        this.messages = messages;
        this.initializeCooldown();
    }

    private void initializeCooldown()
    {
        this.cooldown = new ActionBarCooldown(this.player.getUniqueId().toString(), "player");
        CooldownManager.regsiter(this.cooldown);
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public LinkedBlockingQueue<ActionBarMessage> getMessages() {
        return messages;
    }

    public void setMessages(LinkedBlockingQueue<ActionBarMessage> messages) {
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
        try {
            this.messages.put(message);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public ActionBarMessage remove()
    {
        try {
            return this.messages.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

    public ActionBarMessage getNext()
    {
        return this.messages.peek();
    }
}
