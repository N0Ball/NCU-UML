package src.components.UMLObjects;

import java.awt.Color;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import src.MainManager;

public class GroupObject extends UMLObject
{
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public GroupObject (List<UMLObject> objs)
    {
        items.add(this);
        
        Point bottomRight = new Point(0, 0);
        for (UMLObject obj: objs)
        {
            items.add(obj);
            obj.setGrouped();

            corner = new Point(
                (int) Math.min(corner.getX(), obj.getPosition().getX()),
                (int) Math.min(corner.getY(), obj.getPosition().getY())
            );
            bottomRight = new Point(
                (int) Math.max(bottomRight.getX(), obj.getPosition().getX() + obj.getObjectSize().x),
                (int) Math.max(bottomRight.getY(), obj.getPosition().getY() + obj.getObjectSize().y)
            );
        }

        size = new Point(
            (int) (bottomRight.getX() - corner.getX()),
            (int) (bottomRight.getY() - corner.getY())
        );

        setPosition(corner);
        this.setSize((int) size.getX(),(int) size.getY());
        this.setOpaque(false);
        group = true;
        zIndex = count + 20;
        count ++; 
        select();

    }

    @Override
    public void update(Point pt) {

        prevPt = this.getPosition();
        transform = new Point(
            (int) (pt.getX() - prevPt.getX()),
            (int) (pt.getY() - prevPt.getY())
        );

        for(UMLObject item: items)
        {
            if (item != this)
            {
                item.update(new Point(
                    (int) (item.getPosition().getX() + transform.getX()),
                    item.getPosition().y + transform.y
                ));
            }
        }

        super.update(new Point(
            getPosition().x + transform.x,
            getPosition().y + transform.y
        ));

        MainManager.canvas.update(pt);
    }

    @Override
    public void unsetGroup() {

        if (!getGrouped())
        {
            for (UMLObject item: items)
            {
                if (item != this)
                {
                    item.unsetGroup();
                }
            }

            MainManager.umlObjs.remove(this);
            MainManager.canvas.remove(this);
            
        }else
        {
            super.unsetGroup();
        }

        MainManager.canvas.update(corner);
    }

    @Override
    public void setClickable(boolean status) {

        for (UMLObject item: items)
        {
            if (item != this)
            {
                item.setClickable(status);
            }
        }

        super.setClickable(status);
    }

    @Override
    public void setDragable(boolean status) {
        
        for (UMLObject item: items)
        {
            if (item != this)
            {
                item.setDragable(status);
            }
        }
        super.setDragable(status);
    }

    @Override
    public void paintComponent(Graphics g) {

        Graphics2D painter = (Graphics2D) g;

        if (selected)
        {
            painter.setPaint(new Color(255, 0, 0, 30));
            painter.drawRect(
                0, 
                0,
                (int) size.getX(), 
                (int) size.getY()
            );
        }


    }

    @Override
    public void onClick(Point pt) {
        
        prevPt = pt;

        super.onClick(pt);

    }

    @Override
    public void onDrag(Point pt) {
        
        update(new Point(
            pt.x - size.x/2 - 140 ,
            pt.y - size.y/2 - 20
        ));
        prevPt = pt;

    }

    public Point getCorner() { return corner; }
    
    private Point prevPt;
    private Point transform; 
    private Point corner = new Point(2000, 2000);
    private List<UMLObject> items = new ArrayList<>();
}
