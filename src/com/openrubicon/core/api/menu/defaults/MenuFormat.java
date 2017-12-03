package com.openrubicon.core.api.menu.defaults;

import com.openrubicon.core.api.menu.defaults.format.CheckboxFormat;
import com.openrubicon.core.api.menu.defaults.format.RadioFormat;
import com.openrubicon.core.api.menu.defaults.format.SliderFormat;
import com.openrubicon.core.api.menu.defaults.format.TextFormat;
import com.openrubicon.core.api.menu.formats.Checkbox;
import com.openrubicon.core.api.menu.formats.Radio;
import com.openrubicon.core.api.menu.formats.Slider;
import com.openrubicon.core.api.menu.formats.Text;

public class MenuFormat implements com.openrubicon.core.api.menu.interfaces.MenuFormat {
    @Override
    public Checkbox getCheckboxFormat() {
        return new CheckboxFormat();
    }

    @Override
    public Radio getRadioFormat() {
        return new RadioFormat();
    }

    @Override
    public Slider getSliderFormat() {
        return new SliderFormat();
    }

    @Override
    public Text getTextFormat() {
        return new TextFormat();
    }
}
