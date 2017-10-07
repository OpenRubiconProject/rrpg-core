package com.openrubicon.core.configuration;

import com.openrubicon.core.api.configuration.ConfigurationProperty;

public class ConnectorPort extends ConfigurationProperty<Integer> {

    public ConnectorPort() {
        super("connector-port", 5369, false);
    }
}
