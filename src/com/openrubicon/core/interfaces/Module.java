package com.openrubicon.core.interfaces;

import com.openrubicon.core.api.command.Command;
import com.openrubicon.core.api.configuration.ConfigurationProperty;
import com.openrubicon.core.api.database.interfaces.PostDatabaseLoad;
import com.openrubicon.core.api.database.interfaces.DatabaseModel;
import com.openrubicon.core.api.permission.interfaces.PermissionNode;
import com.openrubicon.core.api.recipes.interfaces.Recipe;
import com.openrubicon.core.api.scoreboard.interfaces.ScoreboardSection;
import com.openrubicon.core.api.server.players.interfaces.PlayerData;

import java.util.ArrayList;
import java.util.LinkedList;

public interface Module {

    default ArrayList<DatabaseModel> getDatabaseModels()
    {
        return new ArrayList<>();
    }
    default ArrayList<Command> getCommands()
    {
        return new ArrayList<>();
    }
    default ArrayList<PostDatabaseLoad> getPostDatabaseLoads()
    {
        return new ArrayList<>();
    }
    default ArrayList<PlayerData> getPlayerDatas()
    {
        return new ArrayList<>();
    }
    default ArrayList<Recipe> getRecipes()
    {
        return new ArrayList<>();
    }
    default ArrayList<ScoreboardSection> getScoreboardSections()
    {
        return new ArrayList<>();
    }
    default ArrayList<PermissionNode> getPermissionNodes()
    {
        return new ArrayList<>();
    }
    default LinkedList<ConfigurationProperty> getConfigurationProperties()
    {
        return new LinkedList<>();
    }
    String getKey();
    String getName();
    String getOverview();
    String getConfiguration();

}
