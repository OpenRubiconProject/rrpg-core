package com.openrubicon.core.api.menu.components;

import org.bukkit.Bukkit;

public class Checkbox extends Component<Checkbox> {

    private boolean checked = false;

    private String name = "";

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

    public String getName() {
        return name;
    }

    public Checkbox setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public Checkbox initEventParameters() {
        this.getEvent().setParameters(this.getName(), String.valueOf(!this.isChecked()));
        return this;
    }
}
