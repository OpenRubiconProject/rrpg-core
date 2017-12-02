package com.openrubicon.core.api.menu.renders;

import com.openrubicon.core.api.menu.components.Component;
import com.openrubicon.core.api.menu.defaults.format.CheckboxFormat;
import com.openrubicon.core.api.menu.defaults.render.CheckboxRender;
import com.openrubicon.core.api.menu.interfaces.Format;
import com.openrubicon.core.api.menu.interfaces.Render;

abstract public class Checkbox implements Render {

    private com.openrubicon.core.api.menu.formats.Checkbox format;
    private com.openrubicon.core.api.menu.components.Checkbox component;

    @Override
    public Format getFormat() {
        return format;
    }

    @Override
    public Component<com.openrubicon.core.api.menu.components.Checkbox> getComponent() {
        return component;
    }

    @Override
    public void setFormat(Format format) {
        this.format = (CheckboxFormat)format;
    }

    @Override
    public void setComponent(Component component) {
        this.setComponent();

    }

    @Override
    public Render getDefault() {
        return new CheckboxRender();
    }
}
