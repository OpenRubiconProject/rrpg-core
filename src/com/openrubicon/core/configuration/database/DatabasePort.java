package com.openrubicon.core.configuration.database;

import com.openrubicon.core.api.configuration.ConfigurationProperty;

public class DatabasePort extends ConfigurationProperty<String> {

    public DatabasePort() {
        super("database-port", "3306", false);
        this.setObservable(false);
    }
}
