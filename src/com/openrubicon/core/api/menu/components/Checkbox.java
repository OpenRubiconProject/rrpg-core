package com.openrubicon.core.api.menu.components;

public class Checkbox extends Component<Checkbox> {

    private boolean checked = false;

    public Checkbox(boolean checked) {
        this.checked = checked;
    }

    public boolean isChecked() {
        return checked;
    }

    public Checkbox setChecked(boolean checked) {
        this.checked = checked;
        return this;
    }
}
