package com.openrubicon.core.interfaces;

import com.openrubicon.core.api.command.Command;
import com.openrubicon.core.api.database.interfaces.PostDatabaseLoad;
import com.openrubicon.core.api.database.interfaces.DatabaseModel;

import java.util.ArrayList;

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
    String getKey();
    String getName();
    String getOverview();
    String getConfiguration();

}
