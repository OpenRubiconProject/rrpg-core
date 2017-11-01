package com.openrubicon.core.api.scoreboard;

import com.openrubicon.core.api.scoreboard.interfaces.ScoreboardSection;
import com.openrubicon.core.api.services.interfaces.Service;

import java.util.ArrayList;

public class ScoreboardSectionService implements Service {

    private ArrayList<ScoreboardSection> scoreboardSections = new ArrayList<>();

    public ArrayList<ScoreboardSection> getScoreboardSections() {
        return scoreboardSections;
    }
}
