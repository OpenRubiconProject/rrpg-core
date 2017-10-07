package com.openrubicon.core.configuration.database;

import com.openrubicon.core.api.configuration.ConfigurationProperty;

public class DatabasePassword extends ConfigurationProperty<String> {

    public DatabasePassword() {
        super("database-password", "", false);
        this.setObservable(false);
    }
}
