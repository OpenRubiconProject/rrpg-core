package com.openrubicon.core.api.menu.renders;

import com.openrubicon.core.api.menu.defaults.render.TextRender;
import com.openrubicon.core.api.menu.interfaces.Format;
import com.openrubicon.core.api.menu.interfaces.Render;

abstract public class Text implements Render {
    @Override
    public Render getDefault() {
        return new TextRender();
    }
}
