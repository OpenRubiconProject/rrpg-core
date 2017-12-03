package com.openrubicon.core.server.playerdata;

import com.openrubicon.core.RRPGCore;
import com.openrubicon.core.api.scoreboard.ScoreboardSectionService;
import com.openrubicon.core.api.scoreboard.interfaces.ScoreboardSection;
import com.openrubicon.core.api.server.players.interfaces.PlayerData;

import java.util.HashMap;

public class ScoreboardSections implements PlayerData {
    HashMap<ScoreboardSection, Boolean> sections = new HashMap<>();

    public ScoreboardSections() {
        for(ScoreboardSection section : RRPGCore.services.getSerivce(ScoreboardSectionService.class).getScoreboardSections())
        {
            this.sections.put(section, true);
        }
    }

    public HashMap<ScoreboardSection, Boolean> getSections() {
        return sections;
    }


}
