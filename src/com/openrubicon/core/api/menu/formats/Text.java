package com.openrubicon.core.api.menu.formats;

import com.openrubicon.core.api.menu.defaults.format.TextFormat;
import com.openrubicon.core.api.menu.interfaces.Format;

abstract public class Text implements Format {

    abstract public String getFormat();

    @Override
    public Format getDefault() {
        return new TextFormat();
    }
}
