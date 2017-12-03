package com.openrubicon.core.api.menu.renders;

import com.openrubicon.core.api.menu.defaults.render.CheckboxRender;

abstract public class Checkbox extends Render {

    @Override
    public com.openrubicon.core.api.menu.formats.Checkbox getFormat() {
        return (com.openrubicon.core.api.menu.formats.Checkbox)super.getFormat();
    }

    @Override
    public com.openrubicon.core.api.menu.components.Checkbox getComponent() {
        return (com.openrubicon.core.api.menu.components.Checkbox)super.getComponent();
    }

    @Override
    public Render getDefault() {
        return new CheckboxRender();
    }

}
