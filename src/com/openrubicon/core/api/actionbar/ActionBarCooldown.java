package com.openrubicon.core.api.actionbar;

import com.openrubicon.core.api.cooldowns.EntityCooldown;

public class ActionBarCooldown extends EntityCooldown {

    public ActionBarCooldown(String id, String modifier) {
        super(id, modifier);
        this.setLength(10);
        this.setModuleName("actionbar");
        this.setId(id, modifier);
    }
}
