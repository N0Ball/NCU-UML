package src.panel.canvas;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLayeredPane;

import src.ObjectManager;
import src.panel.canvas.components.BaseComponent;
import src.panel.canvas.components.ObjectComponent;
import src.panel.canvas.components.Basic.Port;

public class Canvas extends JLayeredPane{

    public Canvas()
    {

        ports = new ArrayList<>();
        mode = "";

        this.addMouseListener(new ClickListener());
        this.setBackground(Color.LIGHT_GRAY);
        this.setLayout(null);

        objCompts = new ArrayList<>();
    }

    public void update()
    {

        for (Port port: ports)
        {
            this.remove(port);
        }

        ports.clear();

        for (BaseComponent objComp: objCompts)
        {
            if (objComp.getSelected())
            {

                Port topPort = new Port();
                topPort.setPosition(objComp.getPosition());
                ports.add(topPort);
                
            }
        }

        for (Port port: ports)
        {
            this.add(port, new Integer(80));
        }

        this.paintImmediately(0, 0, 2000, 2000);

    }

    public List<ObjectComponent> getObjComponents() { return objCompts; }

    public void setCurtComponent(ObjectComponent obj) { curtComponent = obj; }
    public void setSelectMode()
    {
        for (ObjectComponent objCompt: objCompts)
        {
            objCompt.addMouseListener(objCompt.clickListener);
        }
    }
    public void deselectMode()
    {
        for (ObjectComponent objCompt: objCompts)
        {
            objCompt.removeMouseListener(objCompt.clickListener);
        }
    }

    private void clickAction(Point location)
    {

        mode = ObjectManager.objectManager.updateCanvas(this);
        
        if (mode == "Object")
        {
            curtComponent.setPosition(location);
            objCompts.add(curtComponent);
            this.add(curtComponent, new Integer(100));
            update();
        }else if(mode == "Basic")
        {
            update();
        }

    }

    private class ClickListener extends MouseAdapter
    {
        public void mousePressed(MouseEvent e)
        {
            clickAction(e.getPoint());
        }
    }

    private ObjectComponent curtComponent;
    private List<ObjectComponent> objCompts;
    private List<Port> ports;
    private String mode;

}
