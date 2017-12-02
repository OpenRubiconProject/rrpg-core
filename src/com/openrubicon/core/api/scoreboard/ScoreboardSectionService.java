package com.openrubicon.core.api.scoreboard;

import com.openrubicon.core.api.scoreboard.interfaces.ScoreboardSection;
import com.openrubicon.core.api.services.interfaces.Service;

import java.util.ArrayList;

public class ScoreboardSectionService implements Service {

    private ArrayList<ScoreboardSection> scoreboardSections = new ArrayList<>();

    public ScoreboardSectionService(ArrayList<ScoreboardSection> scoreboardSections) {
        this.scoreboardSections = scoreboardSections;
    }

    public ArrayList<ScoreboardSection> getScoreboardSections() {
        return scoreboardSections;
    }

    @Override
    public ArrayList<String> getObservation() {
        ArrayList<String> observation = new ArrayList<>();
        for(ScoreboardSection scoreboardSection : this.getScoreboardSections())
        {
            observation.add(scoreboardSection.getTitle());
        }
        return observation;
    }

}
