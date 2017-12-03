package com.openrubicon.core.api.menu.renders;

import com.openrubicon.core.api.menu.defaults.render.SliderRender;

abstract public class Slider extends Render {

    @Override
    public com.openrubicon.core.api.menu.formats.Slider getFormat() {
        return (com.openrubicon.core.api.menu.formats.Slider)super.getFormat();
    }

    @Override
    public com.openrubicon.core.api.menu.components.Slider getComponent() {
        return (com.openrubicon.core.api.menu.components.Slider)super.getComponent();
    }
    
    @Override
    public Render getDefault() {
        return new SliderRender();
    }
}
