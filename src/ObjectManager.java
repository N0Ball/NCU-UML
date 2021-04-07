package src;

import java.util.List;

import src.frame.MainFrame;
import src.panel.canvas.components.BaseComponent;
import src.panel.canvas.components.ObjectComponent;
import src.panel.canvas.Canvas;
import src.panel.canvas.components.Object.Classes;
import src.panel.canvas.components.Object.UseCase;
import src.panel.canvas.components.Basic.SelectArea;
import src.panel.sidebar.BaseButton;

public class ObjectManager {

    public static ObjectManager objectManager = new ObjectManager();

    ObjectManager() {
        mainFrame = new MainFrame();
        buttons = mainFrame.sidebar.getButtons();
    };

    private void setMode(String m) { mode = m;}

    public ObjectManager(String status)
    {
        System.out.println("Run with mode: " + status);
    }

    public void updateCanvas(Canvas canvas)
    {
        switch (mode){
            case "Classes":
                canvas.setCurtComponent(new Classes());
                break;
            case "Use Case":
                canvas.setCurtComponent(new UseCase());
                break;
            case "Select":
                canvas.setCurtComponent(new SelectArea());
            default:
                System.out.println("Uninpliment Mode!");
                break;
        }
    }

    public void updateSidebar(BaseButton btn)
    {
        setMode(btn.name);
        for (BaseButton button: buttons)
        {
            if (btn == button){
                button.select();
            }
            else{
                button.deselect();
            }

            button.update();
        }
    }

    private String mode;
    private ObjectComponent curtObject;
    private MainFrame mainFrame;
    private List<BaseButton> buttons;
}
