package src.components.UMLObjects;

import java.awt.Point;
import java.util.List;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import src.MainManager;
import src.components.BaseComponent;
import src.components.LineObjects.LineObject;

public class UMLObject extends BaseComponent
{
    
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public void update(Point pt)
    {
        setPosition(pt);
        setClickValid(false);
    }
    
    public boolean getGrouped() { return grouped; };
    public Point getObjectSize() { return size; }

    public void setGrouped() {
        grouped = true;
        deselect();
        setClickable(true);
        setDragable(false);
    }

    public void unsetGroup()
    {
        grouped = false;
        select();
        setClickable(true);
        setDragable(true);
    }

    @Override
    public void select() {
        if (!grouped)
        {
            super.select();
        }
    }

    @Override
    public void onClick(Point pt) {
        
        MainManager.canvas.deselectAll();

        selected = true;

        if (MainManager.getMode().contains("Line"))
        {

            pt = new Point(
                (int) pt.getX() - 100,
                (int) pt.getY() - 40
            );

            double dx = - getPosition().getX() + pt.getX() - 50;
            double dy = - getPosition().getY() + pt.getY() - 50;

            if (dx - dy < 0)
            {
                if (dx + dy > 0)
                {
                    MainManager.line.startPort.setProperty("BOTTOM");
                }else
                {
                    MainManager.line.startPort.setProperty("LEFT");
                }
            }else
            {
                if (dx + dy > 0)
                {
                    MainManager.line.startPort.setProperty("RIGHT");
                }else
                {
                    MainManager.line.startPort.setProperty("TOP");
                }
            }

            MainManager.line.setValid();
            MainManager.line.setStartObject(this);
            MainManager.line.setStart( new Point(
                (int) getPosition().getX() + 50,
                (int) getPosition().getY() + 50 
            ));
        }

        MainManager.canvas.repaint();
    }

    @Override
    public void onRelease(Point pt) {

        if (MainManager.getMode().contains("Line"))
        {
            pt  = new Point(
                (int) pt.getX()-95,
                (int) pt.getY()-75
            );

            MainManager.canvas.deselectAll();
            MainManager.line.setInvalid();

            for (int i = MainManager.umlObjs.size(); i-- > 0; ) {
                List<UMLObject> obj = MainManager.umlObjs;

                if (obj.get(i).collideWith(pt) && obj.get(i) != this)
                {

                    pt = new Point(
                        (int) pt.getX(),
                        (int) pt.getY() + 30
                    );
        
                    double dx = - obj.get(i).getPosition().getX() + pt.getX() - 50;
                    double dy = - obj.get(i).getPosition().getY() + pt.getY() - 50;
        
                    if (dx - dy < 0)
                    {
                        if (dx + dy > 0)
                        {
                            MainManager.line.endPort.setProperty("BOTTOM");
                        }else
                        {
                            MainManager.line.endPort.setProperty("LEFT");
                        }
                    }else
                    {
                        if (dx + dy > 0)
                        {
                            MainManager.line.endPort.setProperty("RIGHT");
                        }else
                        {
                            MainManager.line.endPort.setProperty("TOP");
                        }
                    }
                    
                    MainManager.line.setEndObject(obj.get(i));
                    MainManager.lines.add(MainManager.line.clone());
                    MainManager.line = new LineObject();
                    MainManager.canvas.remove(MainManager.line);
                    MainManager.canvas.add(MainManager.line, Integer.valueOf(15));
                    MainManager.line.setMode(MainManager.getMode());

                    break;
                }
            }
            MainManager.line.setStart(new Point(0, 0));
            MainManager.line.setEnd(new Point(0, 0));
            MainManager.canvas.update(pt);
        }
    }
    
    @Override
    public void onDrag(Point pt) 
    {
    
        if (MainManager.getMode().contains("Line"))
        {

            pt = new Point(
                (int) pt.getX(),
                (int) pt.getY() + 30
            );

            if (collideWith(pt))
            {
                MainManager.line.setInvalid();
            }else
            {           
                MainManager.line.setValid();
                MainManager.line.setEnd(pt);
            }
        }else if (selected)
        {
        
            pt = new Point(
                (int) pt.getX() - 150,
                (int) pt.getY() - 100
            );
            
            update(pt);
        }
        
        MainManager.canvas.update(pt);
    }

    public void rename(String newname)
    {
        name = newname;
    }

    public boolean collideWith(Point pt)
    {
        if (!grouped && !group)
        {
            double dx = pt.getX() - getPosition().getX();
            double dy = pt.getY() - getPosition().getY() + 30;
    
            if ( dx > 0 && dy > 0 && dx < 100 && dy < 100)
            {
                return true;
            }
        }
        return false;
    }

    public boolean includeWith(Point corner, Point s)
    {
        if (!grouped)
        {
            if (corner.getX() < getPosition().getX() && corner.getY() < getPosition().getY())
            {
                if (s.getX() > getPosition().getX() + size.x - corner.getX())
                {
                    if (s.getY() > getPosition().getY() + size.y - corner.getY())
                    {
                        return true;
                    }
                }
            }
        }

        return false;
    }
        
    @Override
    public void paintComponent(Graphics g)
    {

        super.paintComponent(g);
        
        Graphics2D painter = (Graphics2D) g;
        
        painter.drawImage(img, 0, 0, null);
        painter.drawString(name, 25, size.y/2 + 5);
        
        if (selected)
        {
            painter.setPaint(Color.BLACK);
            painter.fillRect(45, 10, 10, 10);
            painter.fillRect(45, 80, 10, 10);
            painter.fillRect(05, 45, 10, 10);
            painter.fillRect(85, 45, 10, 10);
        }

    }

    private boolean grouped = false;
    public boolean group = false;
    public String name = "";
    protected Point size = new Point(100, 100);
}
