package src.panel.canvas.components.Basic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import src.panel.canvas.components.BasicComponent;

public class Port extends BasicComponent
{
    public Port()
    {
        this.setSize(100, 100);
        selected = false;
    }

    public void paint(Graphics g)
    {
        super.paint(g);
        Graphics2D painter = (Graphics2D) g;
        painter.setPaint(Color.BLACK);
        painter.fillRect(45, 10, 10, 10);
        painter.fillRect(5, 45,  10, 10);
        painter.fillRect(85, 45, 10, 10);
        painter.fillRect(45, 80, 10, 10);
    }
}
