package com.openrubicon.core.api.menu.chat.components;

import com.openrubicon.core.api.menu.chat.Line;
import net.md_5.bungee.api.chat.ClickEvent;

abstract public class Component {

    public abstract String render();

    public abstract Line getLine();

    public abstract ClickEvent getClickEvent();

    public abstract ClickEvent getHoverEvent();

}
