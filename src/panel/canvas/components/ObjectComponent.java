package src.panel.canvas.components;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
}
