package com.openrubicon.core.api.menu.components;

abstract public class Component<T> {

    private String label;

    public String getLabel() {
        return label;
    }

    public T setLabel(String label) {
        this.label = label;
        return (T)(this);
    }
}
