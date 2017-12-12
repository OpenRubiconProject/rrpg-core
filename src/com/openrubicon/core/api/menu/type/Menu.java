package com.openrubicon.core.api.menu.type;

import com.openrubicon.core.api.menu.interfaces.MenuFormat;
import com.openrubicon.core.api.menu.interfaces.MenuRender;
import com.openrubicon.core.api.menu.interfaces.MenuTemplate;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

abstract public class Menu {

    private MenuFormat format;
    private MenuRender render;
    private MenuTemplate template;

    private ArrayList<Player> viewers;

    private UUID uuid;

    public Menu(MenuTemplate template, MenuRender render, MenuFormat format) {
        this.format = format;
        this.render = render;
        this.template = template;

        this.uuid = UUID.randomUUID();
    }

    public MenuFormat getFormat() {
        return format;
    }

    public MenuRender getRender() {
        return render;
    }

    public MenuTemplate getTemplate() {
        return template;
    }

    public UUID getUuid() {
        return uuid;
    }

    public ArrayList<Player> getViewers() {
        return viewers;
    }

    public void setViewers(ArrayList<Player> viewers) {
        this.viewers = viewers;
    }

    public void prepare()
    {
        this.getRender().getCheckboxRender().setFormat(this.getFormat().getCheckboxFormat());
        this.getRender().getRadioRender().setFormat(this.getFormat().getRadioFormat());
        this.getRender().getSliderRender().setFormat(this.getFormat().getSliderFormat());
        this.getRender().getTextRender().setFormat(this.getFormat().getTextFormat());
    }
    abstract public void draw();
    abstract public void display(ArrayList<Player> viewers);
    abstract public void update();
    abstract public void clear();

}
