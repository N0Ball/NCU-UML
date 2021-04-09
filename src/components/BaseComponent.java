package src.components;

import java.awt.Point;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JLabel;

public abstract class BaseComponent extends JLabel
{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public abstract void update(Point pt);

    public void onClick(Point pt)
    {
        System.out.println("Now Clicked at: " + pt);
    }

    public void onRelease(Point pt)
    {
        System.out.println("Now Release at: " + pt);
    }

    public void onDrag(Point pt)
    {
        System.out.println("Now Dragging at: " + pt);
    }
    
    public void setClickValid(boolean status) { clickValid = status; }
    public void select() { selected = true; }
    public void deselect() { selected = false; }
    public void setPosition(Point pt)
    {
        this.setLocation(pt);
        position = pt;
    }
    public void setClickable(boolean status) { 

        if (clickable != status)
        {
            if (status)
            {
                this.addMouseListener(clickListener);
            }else
            {
                this.removeMouseListener(clickListener);
            }
        }

        clickable = status; 
    }
    public void setDragable(boolean status) { 

        if (dragable != status)
        {
            if (status)
            {
                this.addMouseMotionListener(dragListener);
            }else
            {
                this.removeMouseMotionListener(dragListener);
            }
        }

        dragable = status; 
    }

    public Point getPosition() { return position; }
    public boolean getSelected() { return selected; }
    public boolean getClickValid() { return clickValid; }
    public boolean getClickable() { return clickable; }
    public boolean getDragable() { return dragable; }
    public int getzIndex() { return zIndex; }
    public int getX() {return (int)position.getX(); }
    public int getY() {return (int)position.getY(); }

    public String name;
    protected boolean selected;
    protected boolean clickValid;
    protected boolean clickable = false;
    protected boolean dragable = false;
    protected Image img;
    protected Point position;
    protected ClickListener clickListener = new ClickListener(); 
    protected DragListener dragListener = new DragListener(); 
    protected int zIndex;
    protected static int count = 0;

    private class ClickListener extends MouseAdapter
    {
        public void mousePressed(MouseEvent e)
        {
            onClick(new Point(e.getXOnScreen(), e.getYOnScreen()));
        }

        public void mouseReleased(MouseEvent e)
        {
            onRelease(new Point(e.getXOnScreen(), e.getYOnScreen()));
        }
    }

    private class DragListener extends MouseMotionAdapter
    {
        public void mouseDragged(MouseEvent e)
        {
            onDrag(new Point(e.getXOnScreen(), e.getYOnScreen()));
        }
    }
}
