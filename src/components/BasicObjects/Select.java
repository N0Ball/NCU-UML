package src.components.BasicObjects;

import java.awt.Point;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import src.MainManager;
import src.components.BaseComponent;
import src.components.UMLObjects.UMLObject;

public class Select extends BaseComponent
{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public Select()
    {
        this.clickValid = false;
        this.addMouseListener(clickListener);
        this.addMouseMotionListener(dragListener);

        this.setSize(2000, 2000);
        this.setOpaque(false);
        size = new Point(0, 0);
        corner = new Point(-10, -10);
        setPosition(new Point(0, 0));
        this.setBackground(null);
    }

    @Override
    public void onClick(Point pt) {

        MainManager.canvas.deselectAll();

        pt = new Point(
            (int) pt.getX() - 100,
            (int) pt.getY() - 40
        );

        prevPt = pt;
    }

    @Override
    public void onRelease(Point pt) {

        for (UMLObject obj: MainManager.umlObjs)
        {
            if (obj.includeWith(corner, size))
            {
                obj.select();
            }
        }

        corner = new Point(-10, -10);
        size = new Point(0, 0);

        MainManager.canvas.update(pt);
    }

    @Override
    public void onDrag(Point pt) {

        pt = new Point(
            (int) pt.getX() - 100,
            (int) pt.getY() - 40
        );

        Point currentPt = pt;
        
        corner = new Point(
            (int) Math.min(currentPt.getX(), prevPt.getX()),
            (int) Math.min(currentPt.getY(), prevPt.getY())
        );
        size = new Point(
            (int) Math.abs(currentPt.getX() - prevPt.getX()),
            (int) Math.abs(currentPt.getY() - prevPt.getY())
        );

        repaint();
    }

    @Override
    public void update(Point pt) {
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Graphics2D painter = (Graphics2D) g;

        painter.setStroke(new BasicStroke(7));
        painter.setPaint(Color.DARK_GRAY);
        painter.drawRect(
            (int) corner.getX(), 
            (int) corner.getY(), 
            (int) size.getX(), 
            (int) size.getY()
        );
        painter.setPaint(new Color(200, 200, 200, 50));
        painter.fillRect(
            (int) corner.getX(), 
            (int) corner.getY(), 
            (int) size.getX(), 
            (int) size.getY()
        );
    }

    private Point prevPt;
    private Point corner = new Point(0, 0);
    private Point size = new Point(2000, 2000);
    
}
