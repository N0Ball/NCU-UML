package src.components.UMLObjects;

import java.awt.Image;

import javax.swing.ImageIcon;

public class ClassObject extends UMLObject
{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public ClassObject()
    {
        img = new ImageIcon("img/classes.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        clickValid = true;
        selected = false;
        this.setSize(100, 100);
        zIndex = count + 20;
        count += 1;
    }
    
}
