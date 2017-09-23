package com.openrubicon.core.interfaces;

import com.openrubicon.core.database.interfaces.DatabaseModel;

import java.util.ArrayList;

public interface Module {

    ArrayList<DatabaseModel> getDatabaseModels();
    String getKey();
    String getName();
    String getOverview();
    String getConfiguration();

}
