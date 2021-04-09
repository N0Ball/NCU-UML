package src.components.BasicObjects;

import java.awt.Color;
import java.awt.Point;

import src.MainManager;
import src.components.BaseComponent;

public class MainCanvas extends BaseComponent
{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public MainCanvas()
    {
        this.addMouseListener(clickListener);
        setPosition(new Point(0, 0));
        this.setSize(2000, 2000);
        this.setBackground(Color.GREEN);
        this.setOpaque(false);
    }

    @Override
    public void update(Point pt) {

    }

    @Override
    public void onClick(Point pt) {
        MainManager.canvas.update( new Point(
            (int) pt.getX() - 100,
            (int) pt.getY() - 40
        ));
    }
    

}
