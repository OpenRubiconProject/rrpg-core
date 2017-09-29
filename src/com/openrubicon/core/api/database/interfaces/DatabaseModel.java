package com.openrubicon.core.api.database.interfaces;

import java.util.HashMap;

public interface DatabaseModel {

    HashMap<Integer, DatabaseMigration> getMigrations();
    String getTableName();
    int getVersion();


}
