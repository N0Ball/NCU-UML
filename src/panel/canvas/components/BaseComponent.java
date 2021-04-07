package src.panel.canvas.components;

import java.awt.Color;
import java.awt.Point;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class BaseComponent extends JLabel
{
    BaseComponent()
    {
        this.setSize(100, 100);
        this.setLocation(150, 150);
        this.setBackground(new Color(0, 0, 0, 0));
        this.setOpaque(true);
        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.setVerticalAlignment(SwingConstants.CENTER);
        setPosition(new Point(100, 100));
    }

    
    public Point getPosition() { return position; }
    public boolean getSelected() { return selected;}
    
    public void select() { selected = true; }
    public void deselect() { selected = false; }
    public void setPosition(Point pt)
    {
        position = pt;
        this.setLocation(pt);
    }
    

    protected Point position;
    protected boolean selected;
}
