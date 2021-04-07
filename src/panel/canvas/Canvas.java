package src.panel.canvas;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import src.ObjectManager;
import src.panel.canvas.components.BaseComponent;
import src.panel.canvas.components.ObjectComponent;
import src.panel.canvas.components.Basic.Port;

public class Canvas extends JPanel{

    public Canvas()
    {

        ports = new ArrayList<>();

        this.addMouseListener(new ClickListener());
        this.setBackground(Color.LIGHT_GRAY);
        this.setLayout(null);

        objCompts = new ArrayList<>();
    }

    public void update()
    {

        ports.clear();

        for (BaseComponent objComp: objCompts)
        {
            if (objComp.getSelected())
            {

                Port topPort = new Port();
                topPort.setPosition(objComp.getLocation());
                ports.add(topPort);
                
            }
        }

        for (Port port: ports)
        {
            this.add(port);
        }

        this.paintImmediately(0, 0, 2000, 2000);

        for (Port port: ports)
        {
            this.remove(port);
        }
    }

    public List<BaseComponent> getObjComponents() { return objCompts; }

    public void setCurtComponent(BaseComponent obj) { curtComponent = obj; }

    private void clickAction(Point location)
    {
        ObjectManager.objectManager.updateCanvas(this);
        curtComponent.setPosition(location);
        objCompts.add(curtComponent);
        this.add(curtComponent);
        update();
    }

    private class ClickListener extends MouseAdapter
    {
        public void mousePressed(MouseEvent e)
        {
            clickAction(e.getPoint());
        }
    }

    private class DragListener extends MouseMotionAdapter
    {
        public void mouseDragged(MouseEvent e)
        {
        }
    }

    private BaseComponent curtComponent;
    private List<BaseComponent> objCompts;
    private List<Port> ports;

}
