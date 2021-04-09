package src.components.UMLObjects;

import java.awt.Image;

import javax.swing.ImageIcon;

public class UseCaseObject extends UMLObject
{
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public UseCaseObject()
    {
        img = new ImageIcon("img/use_case.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        clickValid = true;
        selected = false;
        this.setSize(100, 100);
        zIndex = count + 20;
        count += 1;
    }
}
