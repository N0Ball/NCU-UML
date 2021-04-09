package src.canvas;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLayeredPane;

import src.components.BaseComponent;
import src.components.BasicObjects.MainCanvas;
import src.frame.MainFrame;

public class Canvas extends JLayeredPane
{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public Canvas()
    {

        currentObjs = new ArrayList<>();

        this.add(new MainCanvas(), Integer.valueOf(15));

        this.setBackground(null);
        this.setLayout(null);

    }

    public void update(Point pt)
    {

        for (BaseComponent obj: currentObjs)
        {
            this.remove(obj);
        }

        currentObjs = MainFrame.mainManager.getObject();

        for (BaseComponent obj: currentObjs)
        {
            if (obj.getClickValid())
            {
                obj.update(pt);
            }

            this.add(obj, Integer.valueOf(obj.getzIndex()));
        }

        paintImmediately(0, 0, 2000, 2000);
        paintImmediately(0, 0, 2000, 2000);

    }

    public void deselectAll()
    {
        for (BaseComponent obj: currentObjs)
        {
            obj.deselect();
        }
    }

    public static String getMode() { return mode; }

    public void setMode(String m) { mode = m; }

    private static List<BaseComponent> currentObjs;
    private static String mode;
}
