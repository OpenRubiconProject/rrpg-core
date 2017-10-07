package com.openrubicon.core.configuration;

import com.openrubicon.core.api.configuration.ConfigurationProperty;

public class CooldownReductionCap extends ConfigurationProperty<Integer> {

    public CooldownReductionCap() {
        super("cooldown-reduction-cap", 75, true);
    }
}
