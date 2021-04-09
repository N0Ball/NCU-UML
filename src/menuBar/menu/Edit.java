package src.menuBar.menu;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import src.menuBar.menu.item.Group;
import src.menuBar.menu.item.Rename;
import src.menuBar.menu.item.UnGroup;

public class Edit extends JMenu
{
    
    /**
     *
     */
    private static final long serialVersionUID = 1L;

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
