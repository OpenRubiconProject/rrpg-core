package com.openrubicon.core.configuration.database;

import com.openrubicon.core.api.configuration.ConfigurationProperty;

public class DatabaseName extends ConfigurationProperty<String> {

    public DatabaseName() {
        super("database-name", "", false);
        this.setObservable(false);
    }
}
