package src.panel.canvas.components.Basic;

import java.lang.Math;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import src.ObjectManager;
import src.panel.canvas.components.BasicComponent;

public class SelectArea extends BasicComponent
{
    public SelectArea()
    {
        selected = false;
        corner = new Point(-10, -10);
        size = new Point(0, 0);
        this.setLocation(0, 0);
        this.setSize(2000, 2000);
        this.addMouseListener(new ClickListener());
        this.setBackground(null);
        this.addMouseMotionListener(new DragListener());
    }
    
    public void paint(Graphics g)
    {
        super.paint(g);
        Graphics2D painter = (Graphics2D) g;
        painter.setPaint(new Color(200, 200, 200, 100));
        painter.fillRect((int) corner.getX(),(int) corner.getY(),(int) size.getX() + 5,(int) size.getY() + 5);
        painter.setPaint(Color.DARK_GRAY);
        painter.setStroke(new BasicStroke(5));
        painter.drawRect((int) corner.getX(),(int) corner.getY(),(int) size.getX() + 5,(int) size.getY() + 5);
    }

    private class ClickListener extends MouseAdapter
    {
        public void mousePressed(MouseEvent e)
        {
            pressedPt = e.getPoint();
            ObjectManager.objectManager.deselectAll();
        }

        public void mouseReleased(MouseEvent e)
        {
            corner = new Point(-10, -10);
            size = new Point(0, 0);
        }
    }



    private class DragListener extends MouseMotionAdapter
    {
        public void mouseDragged(MouseEvent e)
        {
            Point currentPt = e.getPoint();

            corner = new Point(
                Math.min((int)currentPt.getX(), (int) pressedPt.getX()), 
                Math.min((int)currentPt.getY(), (int) pressedPt.getY())
            );

            size = new Point(
                Math.abs((int) (currentPt.getX() - pressedPt.getX())),
                Math.abs((int) (currentPt.getY() - pressedPt.getY()))
            );

            repaint();
        }
    }

    private Point pressedPt;
    private Point corner;
    private Point size;
}
