package com.openrubicon.core.api.menu.type;

import com.openrubicon.core.api.menu.chat.Line;
import com.openrubicon.core.api.menu.components.Checkbox;
import com.openrubicon.core.api.menu.components.Component;
import com.openrubicon.core.api.menu.components.Radio;
import com.openrubicon.core.api.menu.components.Slider;
import com.openrubicon.core.api.menu.interfaces.MenuFormat;
import com.openrubicon.core.api.menu.interfaces.MenuRender;
import com.openrubicon.core.api.menu.interfaces.MenuTemplate;
import com.openrubicon.core.api.menu.interfaces.markers.TextSupport;
import com.openrubicon.core.api.menu.renders.Render;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Text extends Menu implements TextSupport {

    private ArrayList<Line> menuOutput = new ArrayList<>();

    public Text(MenuTemplate template, MenuRender render, MenuFormat format) {
        super(template, render, format);
    }

    public ArrayList<Line> getMenuOutput() {
        return menuOutput;
    }

    @Override
    public void prepare() {
        super.prepare();
    }

    @Override
    public void draw() {

        // Render title bar
        this.getMenuOutput().add(Line.blank());
        this.getMenuOutput().add(new Line("===== " + this.getTemplate().getName() + " =====").useHeadingColor());

        // Render actual components
        for(Component component : this.getTemplate().getComponents())
        {
            Render render = null;
            
            if(component instanceof Checkbox)
                 render = this.getRender().getCheckboxRender();

            if(component instanceof Radio)
                render = this.getRender().getRadioRender();

            if(component instanceof Slider)
                render = this.getRender().getSliderRender();

            if(component instanceof com.openrubicon.core.api.menu.components.Text)
                render = this.getRender().getTextRender();
            
            if(render != null)
            {
                render.setComponent(component);
                this.getMenuOutput().add(render.renderText(this.getUuid()));
            }

        }
    }

    @Override
    public void update() {
        this.clear();
        this.draw();
    }

    @Override
    public void clear() {
        this.menuOutput.clear();
    }

    @Override
    public void display(ArrayList<Player> viewers) {

        this.setViewers(viewers);

        // Render the menu to the viewers
        for(Player player : viewers)
        {
            for(Line outputLine : this.getMenuOutput())
            {
                player.spigot().sendMessage(outputLine.getInterfaceLine().create());
            }
        }
    }
}
