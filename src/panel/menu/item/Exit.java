package src.panel.menu.item;

import javax.swing.JMenuItem;

public class Exit extends JMenuItem
{

    public Exit()
    {
        super("Exit");
        this.addActionListener(e -> {
            throw new UnsupportedOperationException("Not implemented Yet");
        });
    }
    
}
