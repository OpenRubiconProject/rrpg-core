package com.openrubicon.core.api.cooldowns;

import com.openrubicon.core.configuration.Configuration;

abstract public class Cooldown {
    
    private String moduleName = "default";

    private int length = 20;
    private int current = 0;
    private int cooldownReduction = 0;
    private boolean locked = false;
    private boolean bypass = false;

    private String id = "";
    private String instanceId = "";
    private String modifier = "";

    public Cooldown(String id, String modifier)
    {
        this.setId(id, modifier);
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setId(String id, String modifier) {
        this.instanceId = id;
        this.modifier = modifier;
        this.id = this.getModuleName()+"-"+this.instanceId+"-"+this.modifier;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getCooldownReduction() {
        return cooldownReduction;
    }

    public void setCooldownReduction(int cooldownReduction) {
        if(cooldownReduction > Configuration.COOLDOWN_REDUCTION_CAP)
            cooldownReduction = Configuration.COOLDOWN_REDUCTION_CAP;
        this.cooldownReduction = cooldownReduction;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public boolean isBypass() {
        return bypass;
    }

    public void setBypass(boolean bypass) {
        this.bypass = bypass;
    }

    public int getCooldownSeconds()
    {
        return this.getCurrent() / 20;
    }

    public int getCooldownLengthSeconds()
    {
        return this.getLength() / 20;
    }

    public void setCooldownSeconds(int seconds)
    {
        this.length = seconds * 20;
    }

    public boolean isOnCooldown()
    {
        if(this.bypass)
            return false;
        return this.current != 0;
    }

    public void insta()
    {
        this.bypass = true;
    }

}
