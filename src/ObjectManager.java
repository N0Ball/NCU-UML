package src;

import java.util.List;

import src.frame.MainFrame;
import src.panel.sidebar.BaseButton;

public class ObjectManager {

    public static ObjectManager objectManager = new ObjectManager();

    ObjectManager() {
        buttons = mainFrame.sidebar.getButtons();
    };

    public void UpdateSidebar(BaseButton btn)
    {
        for (BaseButton button: buttons)
        {
            if (btn == button){
                button.select();
            }
            else{
                button.deselect();
            }

            button.update();
            System.out.println("Button Updated: " + button.name);
        }
    }

    private MainFrame mainFrame = new MainFrame();
    private List<BaseButton> buttons;
}
