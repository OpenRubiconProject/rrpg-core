package com.openrubicon.core.api.menu.chat;

import com.openrubicon.core.api.chat.interfaces.Colors;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.*;

public class Line implements Colors {

    private ComponentBuilder interfaceLine = new ComponentBuilder("");

    public Line() {
    }

    public Line(ComponentBuilder interfaceLine) {
        this.interfaceLine = interfaceLine;
    }

    public Line(String text)
    {
        this.add(text);
    }

    public ComponentBuilder getInterfaceLine() {
        return interfaceLine;
    }

    public static Line blank()
    {
        return new Line();
    }

    public Line addLegacyText(String legacyText)
    {
        this.interfaceLine.append(TextComponent.fromLegacyText(legacyText));
        return this;
    }

    public Line add(String text)
    {
        this.interfaceLine.append(text);
        return this;
    }

    public Line add(BaseComponent... component)
    {
        this.interfaceLine.append(component);
        return this;
    }

    public Line event(ClickEvent clickEvent)
    {
        this.interfaceLine.event(clickEvent);
        return this;
    }

    public Line event(HoverEvent hoverEvent)
    {
        this.interfaceLine.event(hoverEvent);
        return this;
    }

    public Line useColor(ChatColor color)
    {
        this.interfaceLine.color(color);
        return this;
    }

    public Line usePrimaryColor() {
        this.interfaceLine.color(primaryColor());
        return this;
    }

    public Line useSecondaryColor() {
        this.interfaceLine.color(secondaryColor());
        return this;
    }

    public Line useTertiaryColor() {
        this.interfaceLine.color(tertiaryColor());
        return this;
    }

    public Line useHeadingColor() {
        this.interfaceLine.color(headingColor());
        return this;
    }

    public Line useMysticPrimaryColor() {
        this.interfaceLine.color(mysticPrimaryColor());
        return this;
    }

    public Line useMysticSecondaryColor() {
        this.interfaceLine.color(mysticSecondaryColor());
        return this;
    }

    public Line useRed() {
        this.interfaceLine.color(red());
        return this;
    }

    public Line useYellow() {
        this.interfaceLine.color(yellow());
        return this;
    }

    public Line useGreen() {
        this.interfaceLine.color(green());
        return this;
    }
}

