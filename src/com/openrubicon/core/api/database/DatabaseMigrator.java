package com.openrubicon.core.api.database;

import java.util.ArrayList;

public class DatabaseMigrator {

    private ArrayList<com.openrubicon.core.api.database.interfaces.DatabaseModel> models;

    public DatabaseMigrator(ArrayList<com.openrubicon.core.api.database.interfaces.DatabaseModel> models) {
        this.models = models;
    }

    public ArrayList<com.openrubicon.core.api.database.interfaces.DatabaseModel> getModels() {
        return models;
    }

    public void setModels(ArrayList<com.openrubicon.core.api.database.interfaces.DatabaseModel> models) {
        this.models = models;
    }

    public int up(Connection connection)
    {
        int count = 0;
        for(com.openrubicon.core.api.database.interfaces.DatabaseModel model : this.models)
        {
            int lastUpdate = 0;
            int version = model.getVersion();
            for(int i = lastUpdate + 1; i <= version; i++)
            {
                model.getMigrations().get(i).up(connection);
                count++;
            }
        }
        return count;
    }

    public int down(Connection connection)
    {
        int count = 0;
        for(com.openrubicon.core.api.database.interfaces.DatabaseModel model : this.models)
        {
            int lastUpdate = 0;
            int version = model.getVersion();
            for(int i = lastUpdate; i > 0; i--)
            {
                model.getMigrations().get(i).down(connection);
                count++;
            }
        }
        return count;
    }
}
