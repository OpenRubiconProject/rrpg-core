package com.openrubicon.core.interfaces;

import com.openrubicon.core.api.command.Command;
import com.openrubicon.core.api.database.interfaces.DatabaseModel;

import java.util.ArrayList;

public interface Module {

    ArrayList<DatabaseModel> getDatabaseModels();
    ArrayList<Command> getCommands();
    String getKey();
    String getName();
    String getOverview();
    String getConfiguration();

}
