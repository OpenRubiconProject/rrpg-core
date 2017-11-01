package com.openrubicon.core.api.scoreboard.interfaces;

import com.openrubicon.core.api.enums.Priority;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public interface ScoreboardSection {

    Priority getPriority();
    String getTitle();
    ArrayList<String> getLines(Player player);
    default ArrayList<String> getLinesCompact(Player player)
    {
        return this.getLines(player);
    }

}
