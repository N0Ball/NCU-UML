package src.panel.menuBar;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

import src.panel.menuBar.menu.Edit;
import src.panel.menuBar.menu.File;

public class MenuBar extends JMenuBar{
    
    public MenuBar()
    {
        menus.add(new File());
        menus.add(new Edit());

        for(JMenu menu: menus){
            this.add(menu);
        }
    }

    private List<JMenu> menus = new ArrayList<>();
}
