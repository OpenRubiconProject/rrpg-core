package com.openrubicon.core.api.menu.defaults;

import com.openrubicon.core.api.menu.defaults.render.CheckboxRender;
import com.openrubicon.core.api.menu.defaults.render.RadioRender;
import com.openrubicon.core.api.menu.defaults.render.SliderRender;
import com.openrubicon.core.api.menu.defaults.render.TextRender;
import com.openrubicon.core.api.menu.renders.Checkbox;
import com.openrubicon.core.api.menu.renders.Radio;
import com.openrubicon.core.api.menu.renders.Slider;
import com.openrubicon.core.api.menu.renders.Text;

public class MenuRender implements com.openrubicon.core.api.menu.interfaces.MenuRender {

    private CheckboxRender checkboxRender = new CheckboxRender();
    private RadioRender radioRender = new RadioRender();
    private SliderRender sliderRender = new SliderRender();
    private TextRender textRender = new TextRender();
    
    @Override
    public Checkbox getCheckboxRender() {
        return checkboxRender;
    }

    @Override
    public Radio getRadioRender() {
        return radioRender;
    }

    @Override
    public Slider getSliderRender() {
        return sliderRender;
    }

    @Override
    public Text getTextRender() {
        return textRender;
    }
}
