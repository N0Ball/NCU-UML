package src.panel.menuBar.menu;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import src.panel.menuBar.menu.item.Exit;
import src.panel.menuBar.menu.item.Load;
import src.panel.menuBar.menu.item.Save;

public class File extends JMenu
{
    
    public File()
    {
        super("File");
        items = new ArrayList<>();

        items.add(new Load());
        items.add(new Save());
        items.add(new Exit());

        for (JMenuItem item: items)
        {
            this.add(item);
        }

    }

    private List<JMenuItem> items;
}
