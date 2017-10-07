package com.openrubicon.core.configuration.database;

import com.openrubicon.core.api.configuration.ConfigurationProperty;

public class DatabaseUsername extends ConfigurationProperty<String> {

    public DatabaseUsername() {
        super("database-username", "", false);
        this.setObservable(false);
    }
}
