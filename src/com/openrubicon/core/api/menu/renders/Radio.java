package com.openrubicon.core.api.menu.renders;

import com.openrubicon.core.api.menu.defaults.render.RadioRender;
import com.openrubicon.core.api.menu.interfaces.Format;
import com.openrubicon.core.api.menu.interfaces.Render;

abstract public class Radio implements Render {
    @Override
    public Render getDefault() {
        return new RadioRender();
    }
}
