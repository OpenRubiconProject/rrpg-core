package com.openrubicon.core.api.menu.chat;

import com.openrubicon.core.api.menu.type.Menu;
import com.openrubicon.core.api.menu.chat.interfaces.Colors;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;

public class ChatMenu extends Menu<ChatMenu> implements Colors {

    private ArrayList<Line> userInterface = new ArrayList<>();

    public ChatMenu(String name, Player... viewers) {
        super(name, new ArrayList<>(Arrays.asList(viewers)));
    }

    public ChatMenu(String name, ArrayList<Player> viewers) {
        super(name, viewers);
    }

    public ArrayList<Line> getUserInterface() {
        return userInterface;
    }

    @Override
    public ChatMenu render() {
        for(Player player : this.getViewers())
        {
            player.spigot().sendMessage(Line.blank());
            player.spigot().sendMessage(new Line("===== " + this.getName() + " =====").useHeadingColor().getInterfaceLine().create());
            for(Line line : this.userInterface)
            {
                player.spigot().sendMessage(line.getInterfaceLine().create());
            }
        }
        return this;
    }

    @Override
    public ChatMenu prepare() {
        return this;
    }

}

