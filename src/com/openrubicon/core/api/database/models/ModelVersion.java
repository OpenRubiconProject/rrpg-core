package com.openrubicon.core.api.database.models;

import com.openrubicon.core.api.database.DatabaseModel;
import com.openrubicon.core.api.database.interfaces.DatabaseMigration;
import com.openrubicon.core.api.database.migrations.CreateModelVersions;

import java.util.Date;
import java.util.HashMap;

public class ModelVersion extends DatabaseModel<ModelVersion> {

    private long id;
    private String table_name = "";
    private int migration_version;
    private Date created_at;
    private Date updated_at;

    public ModelVersion() {
        this.setSoftDeletes(false);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTable_name() {
        return table_name;
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }

    public int getMigration_version() {
        return migration_version;
    }

    public void setMigration_version(int migration_version) {
        this.migration_version = migration_version;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    @Override
    public HashMap<Integer, DatabaseMigration> getMigrations() {
        HashMap<Integer, DatabaseMigration> migrations = new HashMap<>();
        migrations.put(1, new CreateModelVersions());
        return migrations;
    }

    @Override
    public String getTableName() {
        return "rubicon_core_model_versions";
    }

    @Override
    public int getVersion() {
        return 1;
    }
}
