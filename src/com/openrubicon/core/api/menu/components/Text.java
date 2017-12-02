package com.openrubicon.core.api.menu.components;

public class Text extends Component<Text> {

    private String text;

    public Text(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public Text setText(String text) {
        this.text = text;
        return this;
    }
}
