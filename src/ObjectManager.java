package src;

import java.io.ObjectOutput;
import java.util.List;

import src.frame.MainFrame;
import src.panel.canvas.Canvas;
import src.panel.canvas.components.Object.Classes;
import src.panel.canvas.components.Object.UseCase;
import src.panel.canvas.components.ObjectComponent;
import src.panel.canvas.components.Basic.SelectArea;
import src.panel.sidebar.BaseButton;

public class ObjectManager {

    public static ObjectManager objectManager = new ObjectManager();
    private static SelectArea select = new SelectArea();

    ObjectManager() {

        preMode = "";

        mainFrame = new MainFrame();
        buttons = mainFrame.sidebar.getButtons();
    };

    private void setMode(String m) { mode = m;}

    public ObjectManager(String status)
    {
        System.out.println("Run with mode: " + status);
    }

    public void selectObject(ObjectComponent obj)
    {
        deselectAll();
        if (mode == "Select")
        {
            obj.select();
        }

        mainFrame.canvas.update();
    }

    public void deselectAll()
    {
        if (mode == "Select")
        {
            List<ObjectComponent> objcomps = mainFrame.canvas.getObjComponents();

            for (ObjectComponent objcomp: objcomps)
            {
                objcomp.deselect();
            }
        }

        mainFrame.canvas.update();
    }

    public String updateCanvas(Canvas canvas)
    {
        String res;

        switch (mode){
            case "Classes":
                canvas.setCurtComponent(new Classes());
                res = "Object";
                break;
            case "Use Case":
                canvas.setCurtComponent(new UseCase());
                res = "Object";
                break;
            case "Select":
                res = "Basic";
                break;
            default:
                System.out.println("Uninpliment Mode: " + mode);
                res = "Default";
                break;
        }

        return res;
    }

    public void updateSidebar(BaseButton btn)
    {
        setMode(btn.name);

        if (preMode == "Select" && preMode != mode)
        {
            mainFrame.canvas.remove(select);
            mainFrame.canvas.deselectMode();
            
        }else if (preMode != "Select" && mode == "Select")
        {
            mainFrame.canvas.add(select, new Integer(1));
            mainFrame.canvas.setSelectMode();
        }

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
        
        preMode = mode;
    }

    private String mode;
    private String preMode;
    private MainFrame mainFrame;
    private List<BaseButton> buttons;
}
