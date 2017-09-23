package com.openrubicon.core.api.actionbar;

import com.openrubicon.core.api.cooldowns.CooldownManager;
import org.bukkit.entity.Player;

import java.util.concurrent.LinkedBlockingDeque;

public class PlayerActionBar {
    private Player player;
    private LinkedBlockingDeque<ActionBarMessage> messages = new LinkedBlockingDeque<>();
    private ActionBarCooldown cooldown;

    public PlayerActionBar(Player player) {
        this.player = player;
        this.initializeCooldown();
    }

    public PlayerActionBar(Player player, LinkedBlockingDeque<ActionBarMessage> messages) {
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

    public LinkedBlockingDeque<ActionBarMessage> getMessages() {
        return messages;
    }

    public void setMessages(LinkedBlockingDeque<ActionBarMessage> messages) {
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

    public void addStart(ActionBarMessage message)
    {
        this.messages.addFirst(message);
    }

    public ActionBarMessage getNext()
    {
        return this.messages.peek();
    }
}
