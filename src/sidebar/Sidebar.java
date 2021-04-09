package src.sidebar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import src.sidebar.button.AssociationLine;
import src.sidebar.button.Classes;
import src.sidebar.button.CompositionLine;
import src.sidebar.button.GenerationLine;
import src.sidebar.button.Select;
import src.sidebar.button.UseCase;

public class Sidebar extends JPanel
{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

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

        buttons.get(0).pressAction();

        for (BaseButton button: buttons)
        {
            this.add(button.getButton());
        }

        this.setBackground(Color.LIGHT_GRAY);
    }

    public static void updateSideBar(BaseButton btn)
    {
        for (BaseButton button: buttons)
        {
            if (button == btn)
            {
                button.select();
            }else
            {
                button.deselect();
            }

            button.update();
        }
    }

    public List<BaseButton> getButtons()
    {
        return buttons;
    }

    private static List<BaseButton> buttons;
}
