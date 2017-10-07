package com.openrubicon.core.configuration;

import com.openrubicon.core.api.configuration.ConfigurationProperty;

public class DevMode extends ConfigurationProperty<Boolean> {

    public DevMode() {
        super("dev-mode", false, false);
    }
}
