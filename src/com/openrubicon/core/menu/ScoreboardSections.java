package com.openrubicon.core.menu;

import com.openrubicon.core.api.menu.components.Checkbox;
import com.openrubicon.core.api.menu.components.Component;
import com.openrubicon.core.api.menu.events.types.CommandMenuEvent;
import com.openrubicon.core.api.menu.interfaces.MenuTemplate;
import com.openrubicon.core.api.scoreboard.interfaces.ScoreboardSection;
import com.openrubicon.core.commands.scoreboard.ScoreboardSectionToggle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ScoreboardSections implements MenuTemplate {

    private HashMap<ScoreboardSection, Boolean> sections = new HashMap<>();

    public ScoreboardSections(HashMap<ScoreboardSection, Boolean> sections) {
        this.sections = sections;
    }

    @Override
    public String getName() {
        return "Toggle Scoreboard Sections";
    }

    @Override
    public ArrayList<Component> getComponents() {
        ArrayList<Component> components = new ArrayList<>();
        for(Map.Entry<ScoreboardSection, Boolean> entry : sections.entrySet())
        {
            ScoreboardSection section = entry.getKey();
            Boolean status = entry.getValue();

            components.add(new Checkbox(status).setLabel(section.getTitle()).setName(section.getClass().getSimpleName()).setEvent(new CommandMenuEvent(new ScoreboardSectionToggle())));
        }
        return components;
    }
}
