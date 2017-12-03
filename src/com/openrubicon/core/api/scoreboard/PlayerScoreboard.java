package com.openrubicon.core.api.scoreboard;

import com.openrubicon.core.RRPGCore;
import com.openrubicon.core.api.scoreboard.interfaces.ScoreboardSection;
import com.openrubicon.core.helpers.Constants;
import com.openrubicon.core.helpers.Helpers;
import com.openrubicon.core.server.playerdata.ScoreboardSections;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class PlayerScoreboard {
    private Player player;
    private boolean enabled = true;
    private boolean compact = false;
    private ArrayList<ScoreboardSection> scoreboardSections = new ArrayList<>();

    public PlayerScoreboard(Player player, ArrayList<ScoreboardSection> scoreboardSections) {
        this.player = player;
        this.scoreboardSections = scoreboardSections;
    }

    public PlayerScoreboard(Player player, ArrayList<ScoreboardSection> scoreboardSections, boolean enabled) {
        this.player = player;
        this.enabled = enabled;
        this.scoreboardSections = scoreboardSections;
    }

    public Player getPlayer() {
        return player;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public ArrayList<ScoreboardSection> getScoreboardSections() {
        return scoreboardSections;
    }

    public ArrayList<String> getScoreboardContent()
    {
        ArrayList<String> scoreboardContent = new ArrayList<>();
        scoreboardContent.add(Constants.PRIMARY_COLOR + Constants.BOLD + "Rubicon");
        scoreboardContent.add(Constants.SECONDARY_COLOR+"/rrpg sb");
        scoreboardContent.add(" ");

        String uniqueSpaces = " ";
        for(int i = 0; i < this.getScoreboardSections().size(); i++)
        {
            if(!RRPGCore.players.getPlayerData(this.getPlayer(), ScoreboardSections.class).getSections().get(this.getScoreboardSections().get(i)))
                continue;

            uniqueSpaces += " ";

            ArrayList<String> content;

            if(this.compact)
                content = this.getScoreboardSections().get(i).getLinesCompact(this.player);
            else
                content = this.getScoreboardSections().get(i).getLines(this.player);

            if(content.size() > 0)
            {
                scoreboardContent.add(Constants.HEADING_COLOR + Constants.BOLD + this.getScoreboardSections().get(i).getTitle());
                scoreboardContent.add(Constants.RESET_FORMAT+"====="+uniqueSpaces);
                scoreboardContent.addAll(content);

                if(i != this.getScoreboardSections().size() - 1)
                    scoreboardContent.add(uniqueSpaces);
            }

        }
        return Helpers.colorize(scoreboardContent);
    }
}
