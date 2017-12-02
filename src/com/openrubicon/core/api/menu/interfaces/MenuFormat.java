package com.openrubicon.core.api.menu.interfaces;

import com.openrubicon.core.api.menu.formats.Checkbox;
import com.openrubicon.core.api.menu.formats.Radio;
import com.openrubicon.core.api.menu.formats.Slider;
import com.openrubicon.core.api.menu.formats.Text;

public interface MenuFormat {
    Checkbox getCheckboxFormat();
    Radio getRadioFormat();
    Slider getSliderFormat();
    Text getTextFormat();
}
