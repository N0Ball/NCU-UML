package src.menuBar;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

import src.menuBar.menu.Edit;
import src.menuBar.menu.File;

public class MenuBar extends JMenuBar{
    
    /**
     *
     */
    private static final long serialVersionUID = 1L;

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
