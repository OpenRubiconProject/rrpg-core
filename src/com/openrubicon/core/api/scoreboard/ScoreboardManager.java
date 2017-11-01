package com.openrubicon.core.api.scoreboard;

import com.openrubicon.core.RRPGCore;
import me.winterguardian.easyscoreboards.ScoreboardUtil;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class ScoreboardManager {

    protected static HashMap<OfflinePlayer, PlayerScoreboard> playerScoreboards = new HashMap<>();

    public static void process()
    {
        for(PlayerScoreboard scoreboard : playerScoreboards.values())
        {
            if(!scoreboard.isEnabled())
                continue;

            String[] content = scoreboard.getScoreboardContent().toArray(new String[0]);
            //Bukkit.broadcastMessage(scoreboard.getPlayer().getDisplayName());
            ScoreboardUtil.unrankedSidebarDisplay(scoreboard.getPlayer(), content);
        }
    }

    public static void addPlayer(Player player)
    {
        if(!playerScoreboards.containsKey(player))
            playerScoreboards.put(player, new PlayerScoreboard(player, RRPGCore.services.getSerivce(ScoreboardSectionService.class).getScoreboardSections()));
    }

    public static void removePlayer(OfflinePlayer player)
    {
        playerScoreboards.remove(player);
    }

}
