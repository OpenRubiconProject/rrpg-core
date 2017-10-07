package com.openrubicon.core.configuration.database;

import com.openrubicon.core.api.configuration.ConfigurationProperty;

public class DatabaseHost extends ConfigurationProperty<String> {

    public DatabaseHost() {
        super("database-host", "localhost", false);
        this.setObservable(false);
    }
}
