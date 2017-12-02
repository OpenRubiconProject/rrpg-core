package com.openrubicon.core.api.menu.renders;

import com.openrubicon.core.api.menu.defaults.render.SliderRender;
import com.openrubicon.core.api.menu.interfaces.Format;
import com.openrubicon.core.api.menu.interfaces.Render;

abstract public class Slider implements Render {
    @Override
    public Render getDefault() {
        return new SliderRender();
    }
}
