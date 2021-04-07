package src.panel.canvas.components;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;

import src.ObjectManager;


public class ObjectComponent extends BaseComponent
{

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        paintBrush.paintIcon(this, g, (int)position.getX(), (int)position.getY());
    }

    protected ImageIcon paintBrush;

    protected void clickAction(){
        System.out.println("Button Clicked!");
        ObjectManager.objectManager.selectObject(this);
    }

    public ClickListener clickListener = new ClickListener();

    private class ClickListener extends MouseAdapter
    {
        public void mousePressed(MouseEvent e)
        {
            clickAction();
        }
    }

    private class DragListener extends MouseMotionAdapter
    {
        public void mouseDragged(MouseEvent e)
        {
            
        }
    }
}
