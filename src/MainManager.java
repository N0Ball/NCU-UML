package src;

import java.util.ArrayList;
import java.util.List;
import java.awt.Point;

import javax.swing.JOptionPane;

import src.canvas.Canvas;
import src.components.BaseComponent;
import src.components.BasicObjects.Select;
import src.components.LineObjects.LineObject;
import src.components.UMLObjects.ClassObject;
import src.components.UMLObjects.GroupObject;
import src.components.UMLObjects.UMLObject;
import src.components.UMLObjects.UseCaseObject;

public class MainManager 
{

    public MainManager(Canvas c)
    {
        umlObjs = new ArrayList<>();
        lines = new ArrayList<>();
        canvas = c;
        prevMode = "";
    }

    public static String getMode() { return mode; }

    public static Select select = new Select();
    public static LineObject line;

    public List<BaseComponent> getObject()
    {
        UMLObject obj;

        switch(mode)
        {
            case "Classes":
                obj = new ClassObject();
                umlObjs.add(obj);
                break;
            case "Use Case":
                obj = new UseCaseObject();
                umlObjs.add(obj);
                break;
            default:
                obj = null;
                break;
        }

        List<BaseComponent> allComponent = new ArrayList<>();

        for (BaseComponent line: lines)
        {
            allComponent.add(line);
        }
        for (BaseComponent umlobj: umlObjs)
        {
            allComponent.add(umlobj);
        }
        
        return allComponent;
    }

    public static void setMode(String m) {

        mode = m; 
        canvas.deselectAll();

        System.out.println("Set mode: "+ mode);

        if (mode != prevMode)
        {
            if (prevMode == "Select")
            {

                System.out.println("REMOVE SELECT");
                canvas.remove(select);
                canvas.paintImmediately(0, 0, 2000, 2000);

            }
            if (prevMode.contains("Line"))
            {
                System.out.println("REMOVE LINE");
                canvas.remove(line);
                canvas.paintImmediately(0, 0, 2000, 2000);
            }

            switch(mode)
            {
                case "Classes":
                case "Use Case":
                    System.out.println("SET UML");
                    for (BaseComponent obj: umlObjs)
                    {
                        obj.setClickable(false);
                        obj.setDragable(false);
                    }
                    canvas.setMode("UML");
                    break;
    
                case "Group":
                case "Select":
                    System.out.println("SET SELECT");
                    canvas.add(select, Integer.valueOf(18));
                    for (BaseComponent obj: umlObjs)
                    {
                        obj.setClickable(true);
                        obj.setDragable(true);
                    }
                    canvas.setMode("Select");
                    break;
    
                case "Association Line":
                case "Generation Line":
                case "Composition Line":
                    System.out.println("SET LINE");
                    line = new LineObject();
                    line.setMode(mode);
                    canvas.add(line, Integer.valueOf(line.getzIndex()));
                    for (UMLObject obj: umlObjs)
                    {
                        if (obj.group)
                        {
                            obj.setDragable(false);
                            obj.setClickable(false);
                        }else
                        {
                            obj.setDragable(true);
                            obj.setClickable(true);
                        }
                    }
                    canvas.setMode("Line");
                    break;
            }

            if (mode == "Group")
            {
                setMode("Select");
            }
        }


        prevMode = mode;
    }
    
    public static void selectMenu(String name) {
        if (name == "Group")
        {
            System.out.println("Pressed Item: " + name);

            List<UMLObject> objs = new ArrayList<>();
            
            for (UMLObject obj: umlObjs)
            {
                if (obj.getSelected())
                {
                    objs.add(obj);
                }
            }

            if (objs.size() > 1)
            {
                GroupObject obj = new GroupObject(objs);
                umlObjs.add(obj);
                setMode("Group");
                canvas.update(obj.getCorner());

            }else
            {
                System.out.println("Not enough to group");
            }
        }else if (name == "UnGroup")
        {
            int countSelected = 0;
            UMLObject target = null;

            for (UMLObject umlobj: umlObjs)
            {
                if (umlobj.getSelected())
                {
                    target = umlobj;
                    countSelected ++;
                }
            }

            if (countSelected == 1)
            {
                if (target.group)
                {
                    System.out.println("UnsetGrouping");
                    target.unsetGroup();
                }
            }else
            {
                System.out.println("Not valid");
            }

        }else if (name == "Rename")
        {
            List<UMLObject> canidates = new ArrayList<>();

            for (UMLObject obj: umlObjs)
            {
                if (obj.getSelected())
                {
                    canidates.add(obj);
                }
            }
            if (canidates.size() > 0)
            {
                String newname = JOptionPane.showInputDialog("Enter New Name");

                if (newname != null)
                {
                    for (UMLObject canidate: canidates)
                    {
                        canidate.rename(newname);
                    }
    
                    canvas.update(new Point(0, 0));
                }
            }
        }else if (name == "Exit")
        {
            System.exit(0);
        }
    }

    private static String mode;
    private static String prevMode;
    public static Canvas canvas;
    public static List<UMLObject> umlObjs;
    public static List<LineObject> lines;

}
