package src.panel.sidebar;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import src.ObjectManager;

public abstract class BaseButton
{
    public BaseButton(String name, ImageIcon icon) {
        this.name = name;
        isSelect = false;
        paintBrush = icon.getImage();
        button = new JButton(icon);
        button.setOpaque(true);
        button.addActionListener(e -> {
            pressAction();
        });
    };

    public void update()
    {
        if (isSelect){
            color = Color.DARK_GRAY;
        }else{
            color = Color.lightGray;
        }

        this.button.setBackground(color);
    }

    public JButton getButton(){ return button; }

    public void select() { isSelect = true; }
    public void deselect() { isSelect = false; }

    public boolean getIsSelect() { return isSelect; }
    public Image getPaintBrush() { return paintBrush; }

    protected void pressAction()
    {
        ObjectManager.objectManager.UpdateSidebar(this);
        System.out.println("Button Pressed: " + name);
    }
    
    protected Color color;
    protected JButton button; 
    private boolean isSelect;
    public String name;
    private Image paintBrush;
}
