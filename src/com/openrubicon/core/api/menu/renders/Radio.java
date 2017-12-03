package com.openrubicon.core.api.menu.renders;

import com.openrubicon.core.api.menu.defaults.render.RadioRender;

abstract public class Radio extends Render {

    @Override
    public com.openrubicon.core.api.menu.formats.Radio getFormat() {
        return (com.openrubicon.core.api.menu.formats.Radio)super.getFormat();
    }

    @Override
    public com.openrubicon.core.api.menu.components.Radio getComponent() {
        return (com.openrubicon.core.api.menu.components.Radio)super.getComponent();
    }
    
    @Override
    public Render getDefault() {
        return new RadioRender();
    }
}
