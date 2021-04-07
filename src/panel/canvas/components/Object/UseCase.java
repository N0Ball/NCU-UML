package src.panel.canvas.components.Object;

import java.awt.Image;

import javax.swing.ImageIcon;

import src.panel.canvas.components.ObjectComponent;

public class UseCase extends ObjectComponent
{
    public UseCase()
    {
        Image scaledImage = new ImageIcon("img/use_case.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        paintBrush = new ImageIcon(scaledImage);
        this.setIcon(paintBrush);
        selected = false;
    }
}
