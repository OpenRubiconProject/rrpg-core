package com.openrubicon.core.api.configuration;

import com.openrubicon.core.api.interfaces.Observeable;

import java.util.ArrayList;

abstract public class ConfigurationProperty<T> implements Observeable {

    private T property;
    private T defaultValue;

    private String key;

    private boolean saved = true;

    private boolean observable = true;
    private boolean editable = true;

    public ConfigurationProperty(String key, T defaultValue) {
        this.defaultValue = defaultValue;
        this.key = key;
    }

    public ConfigurationProperty(String key, T defaultValue, boolean saved) {
        this.defaultValue = defaultValue;
        this.key = key;
        this.saved = saved;
    }

    public T getProperty() {
        return property;
    }

    public void setProperty(T property) {
        if(this.property != null && (!editable || !observable))
            return;

        if(property == null)
            this.property = this.defaultValue;
        else
            this.property = property;
    }

    public T getDefaultValue() {
        return defaultValue;
    }

    public String getKey() {
        return key;
    }

    public boolean isSaved() {
        return saved;
    }

    public void setObservable(boolean observable) {
        this.observable = observable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    @Override
    public ArrayList<String> getObservation() {
        if(!observable)
        {
            ArrayList<String> observations = new ArrayList<>();
            observations.add("For safety and security reasons, this property cannot be read.");
            return observations;
        }

        ArrayList<String> observations = new ArrayList<>();
        observations.add(this.getKey() + ": " + this.getProperty().toString());
        return observations;
    }
}
