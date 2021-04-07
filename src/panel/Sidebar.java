package src.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import src.panel.button.AssociationLine;
import src.panel.button.BaseButton;
import src.panel.button.Classes;
import src.panel.button.CompositionLine;
import src.panel.button.GenerationLine;
import src.panel.button.Select;
import src.panel.button.UseCase;

public class Sidebar extends JPanel
{

    public Sidebar()
    {
        buttons = new ArrayList<>();

        this.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        this.setPreferredSize(new Dimension(100, 250));

        buttons.add(new Select());
        buttons.add(new AssociationLine());
        buttons.add(new GenerationLine());
        buttons.add(new CompositionLine());
        buttons.add(new Classes());
        buttons.add(new UseCase());

        buttons.get(0).select();

        for (BaseButton button: buttons)
        {
            this.add(button.getButton());
        }

        this.setBackground(Color.GRAY);
    }

    public List<BaseButton> getButtons()
    {
        return buttons;
    }

    private List<BaseButton> buttons;
}
