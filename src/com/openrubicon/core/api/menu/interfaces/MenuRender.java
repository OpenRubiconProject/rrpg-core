package com.openrubicon.core.api.menu.interfaces;

import com.openrubicon.core.api.menu.renders.Checkbox;
import com.openrubicon.core.api.menu.renders.Radio;
import com.openrubicon.core.api.menu.renders.Slider;
import com.openrubicon.core.api.menu.renders.Text;

public interface MenuRender {

    Checkbox getCheckboxRender();
    Radio getRadioRender();
    Slider getSliderRender();
    Text getTextRender();

}
