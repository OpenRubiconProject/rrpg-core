package com.openrubicon.core.api.menu.renders;

import com.openrubicon.core.api.menu.defaults.render.TextRender;

abstract public class Text extends Render {

    @Override
    public com.openrubicon.core.api.menu.formats.Text getFormat() {
        return (com.openrubicon.core.api.menu.formats.Text)super.getFormat();
    }

    @Override
    public com.openrubicon.core.api.menu.components.Text getComponent() {
        return (com.openrubicon.core.api.menu.components.Text)super.getComponent();
    }
    
    @Override
    public Render getDefault() {
        return new TextRender();
    }
}
