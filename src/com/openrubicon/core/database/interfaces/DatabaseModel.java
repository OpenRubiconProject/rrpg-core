package com.openrubicon.core.database.interfaces;

import java.util.HashMap;

public interface DatabaseModel {

    HashMap<Integer, DatabaseMigration> getMigrations();
    String getTableName();
    int getVersion();


}
