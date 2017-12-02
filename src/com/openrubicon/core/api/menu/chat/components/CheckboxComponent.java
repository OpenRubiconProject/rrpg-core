package com.openrubicon.core.api.menu.chat.components;

import com.openrubicon.core.api.command.Command;
import com.openrubicon.core.api.menu.chat.Line;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;

public class CheckboxComponent extends Component {

    public static final String DEFAULT_FORMAT = " [%s] ";
    public static final String YES            = "✔";
    public static final String NO             = "✖";

    private boolean checked = false;

    private String label = "";

    private Command command;

    private String format = DEFAULT_FORMAT;
    private String stringChecked   = YES;
    private String stringUnchecked = NO;

    public CheckboxComponent withFormat(String format)
    {
        this.format = format;
        return this;
    }

    public CheckboxComponent withCommand(Command command)
    {
        this.command = command;
        return this;
    }

    public CheckboxComponent withCheckedChar(String checked)
    {
        this.stringChecked = checked;
        return this;
    }

    public CheckboxComponent withUncheckedChar(String unchecked)
    {
        this.stringUnchecked = unchecked;
        return this;
    }

    public CheckboxComponent withLabel(String label)
    {
        this.label = label;
        return this;
    }

    public boolean isChecked() {
        return checked;
    }

    public CheckboxComponent setChecked(boolean checked) {
        this.checked = checked;
        return this;
    }

    @Override
    public String render() {
        String output = this.format;

        output = String.format(output, isChecked() ? stringChecked : stringUnchecked);

        return output;
    }

    @Override
    public ClickEvent getClickEvent() {
        return new ClickEvent(ClickEvent.Action.RUN_COMMAND, this.command.getExampleCommand() + " " + !this.isChecked());
    }

    @Override
    public ClickEvent getHoverEvent() {
        return null;
    }

    @Override
    public Line getLine() {
        //Line line = new Line().add(this.label + ":").useHeadingColor().event(this.getClickEvent()).add(this.render()).usePrimaryColor();
        //line.getInterfaceLine().event(this.getClickEvent()).append(this.render());
        //line.usePrimaryColor();
        return new Line().add(this.label + ":").useHeadingColor().event(this.getClickEvent()).add(this.render()).usePrimaryColor();
    }
}
