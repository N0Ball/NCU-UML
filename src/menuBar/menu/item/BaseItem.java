package src.menuBar.menu.item;

import javax.swing.JMenuItem;

import src.MainManager;

public class BaseItem extends JMenuItem
{
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public BaseItem(String s)
    {
        super(s);

        this.addActionListener(e -> {
            menuSelected();
        });

        name = s;
    }

    protected void menuSelected()
    {
        MainManager.selectMenu(name);
    }

    private String name;
}
