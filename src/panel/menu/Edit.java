package src.panel.menu;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import src.panel.menu.item.Group;
import src.panel.menu.item.Rename;
import src.panel.menu.item.UnGroup;

public class Edit extends JMenu
{
    
    public Edit()
    {
        super("Edit");

        items = new ArrayList<>();

        items.add(new Group());
        items.add(new UnGroup());
        items.add(new Rename());

        for (JMenuItem item: items)
        {
            this.add(item);
        }
    }

    private List<JMenuItem> items;
}
