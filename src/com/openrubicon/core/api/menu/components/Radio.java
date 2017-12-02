package com.openrubicon.core.api.menu.components;

public class Radio extends Component<Radio> {

    private int selection = 0;

    public Radio(int selection) {
        this.selection = selection;
    }

    public int getSelection() {
        return selection;
    }

    public Radio setSelection(int selection) {
        this.selection = selection;
        return this;
    }
}
