package com.openrubicon.core.configuration;

import com.openrubicon.core.api.configuration.ConfigurationProperty;

public class DiscordAppToken extends ConfigurationProperty<String> {

    public DiscordAppToken() {
        super("discord-app-token", "", false);
        this.setObservable(false);
    }
}
