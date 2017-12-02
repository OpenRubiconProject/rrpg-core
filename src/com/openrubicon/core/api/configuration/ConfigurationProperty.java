package com.openrubicon.core.api.configuration;

import com.openrubicon.core.api.utility.DynamicPrimitive;
import com.openrubicon.core.helpers.Constants;

import java.util.ArrayList;

abstract public class ConfigurationProperty<T> extends DynamicPrimitive<T> {

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

    @Override
    public void setProperty(String property) {
        if(this.getProperty() != null && (!editable || !observable))
            return;

        if(property == null)
        {
            this.changeProperty(this.defaultValue);
        }
        else
        {
            if(this.getDefaultValue() instanceof Boolean)
                this.changeProperty((T)Boolean.valueOf(property));
            else if(this.getDefaultValue()instanceof Integer)
                this.changeProperty((T)Integer.valueOf(property));
            else if(this.getDefaultValue()instanceof Short)
                this.changeProperty((T)Short.valueOf(property));
            else if(this.getDefaultValue()instanceof Byte)
                this.changeProperty((T)Byte.valueOf(property));
            else if(this.getDefaultValue()instanceof Long)
                this.changeProperty((T)Long.valueOf(property));
            else if(this.getDefaultValue()instanceof Character)
                this.changeProperty((T)Character.valueOf(property.charAt(0)));
            else if(this.getDefaultValue()instanceof Float)
                this.changeProperty((T)Float.valueOf(property));
            else if(this.getDefaultValue()instanceof Double)
                this.changeProperty((T)Double.valueOf(property));
            else
                this.changeProperty((T)property);
        }

        this.setPrim();
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
        observations.add(Constants.PRIMARY_COLOR + this.getKey() + ": " + Constants.SECONDARY_COLOR + this.getProperty().toString());
        return observations;
    }
}
