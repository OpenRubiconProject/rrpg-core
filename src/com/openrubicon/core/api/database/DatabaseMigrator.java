package com.openrubicon.core.api.database;

import com.openrubicon.core.api.database.interfaces.DatabaseMigration;
import com.openrubicon.core.api.database.interfaces.PostDatabaseLoad;
import com.openrubicon.core.api.database.models.ModelVersion;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.Date;

public class DatabaseMigrator implements PostDatabaseLoad {

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

    @Override
    public void run()
    {
        ModelVersion modelVersion = new ModelVersion();

        DatabaseMigration modelVersionCreateMigration = modelVersion.getMigrations().get(1);
        modelVersionCreateMigration.up(Database.connection());

        int version = modelVersion.getVersion();
        int lastUpdate;

        modelVersion.setTable_name(modelVersion.getTableName());
        if(modelVersion.count("id").where("table_name", ":table_name").executeCount() > 0)
        {
            modelVersion = modelVersion.selectAll().where("table_name", ":table_name").executeFetchFirst(ModelVersion.class);
            lastUpdate = modelVersion.getMigration_version();

        }
        else
        {
            modelVersion.setMigration_version(0);
            lastUpdate = 0;
            modelVersion.insert("migration_version, table_name", ":migration_version, :table_name").executeInsert();
            modelVersion = modelVersion.selectAll().where("table_name", ":table_name").executeFetchFirst(ModelVersion.class);
        }

        for(int i = lastUpdate + 1; i <= version; i++)
        {
            DatabaseMigration migration = modelVersion.getMigrations().get(i);
            if(migration == null)
                continue;
            migration.up(Database.connection());
            modelVersion.setMigration_version(i);
        }

        modelVersion.setUpdated_at(new Date());
        modelVersion.update().set("migration_version").touch().executeUpdate();

        this.up(Database.connection());
    }

    public int up(Connection connection)
    {
        int count = 0;
        for(com.openrubicon.core.api.database.interfaces.DatabaseModel model : this.models)
        {
            int lastUpdate;
            int version = model.getVersion();

            ModelVersion modelVersion = new ModelVersion();
            modelVersion.setTable_name(model.getTableName());
            if(modelVersion.count("id").where("table_name", ":table_name").executeCount() > 0)
            {
                modelVersion = modelVersion.selectAll().where("table_name", ":table_name").executeFetchFirst(ModelVersion.class);
                lastUpdate = modelVersion.getMigration_version();
            }
            else
            {
                modelVersion.setMigration_version(0);
                lastUpdate = 0;
                modelVersion.insert("migration_version, table_name", ":migration_version, :table_name").executeInsert();
                modelVersion = modelVersion.selectAll().where("table_name", ":table_name").executeFetchFirst(ModelVersion.class);
            }

            for(int i = lastUpdate + 1; i <= version; i++)
            {
                if(model.getMigrations() == null)
                {
                    Bukkit.getLogger().info("No Migrations for " + model.getTableName());
                    continue;
                }

                DatabaseMigration migration = model.getMigrations().get(i);
                if(migration == null)
                    continue;
                migration.up(connection);
                count++;
                modelVersion.setMigration_version(i);
            }
            modelVersion.setUpdated_at(new Date());
            modelVersion.update().set("migration_version").touch().executeUpdate();

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
