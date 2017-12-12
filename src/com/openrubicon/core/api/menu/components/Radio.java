package com.openrubicon.core.api.menu.components;

import java.util.ArrayList;

public class Radio extends Component<Radio> {

    private int selection = 0;

    private int count = 0;

    private ArrayList<String> labels = new ArrayList<>();

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

    public int getOptionsCount() {
        return count;
    }

    public ArrayList<String> getOptions() {
        return labels;
    }

    public Radio setLabels(ArrayList<String> labels) {
        this.labels = labels;
        this.count = this.labels.size();
        return this;
    }

    @Override
    public Radio initEventParameters() {
        this.getMenuEvent().setParameters(String.valueOf(this.getSelection()));
        return this;
    }
}
