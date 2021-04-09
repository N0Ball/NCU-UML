package src.frame;

import java.awt.Color;
import java.awt.BorderLayout;

import javax.swing.JFrame;

import src.MainManager;
import src.canvas.Canvas;
import src.components.BaseComponent;
import src.menuBar.MenuBar;
import src.sidebar.Sidebar;

public class MainFrame extends JFrame
{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public static Canvas canvas = new Canvas();
    public static MainManager mainManager = new MainManager(canvas); 

    public MainFrame()
    {
        this.setBackground(Color.WHITE);
        this.setTitle("OO UML");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 600);
        this.setUndecorated(true);
        
        this.add(canvas, BorderLayout.CENTER);
        this.add(new Sidebar(), BorderLayout.WEST);
        this.setJMenuBar(new MenuBar());
        this.setVisible(true);
    }

    public void addCanvas(BaseComponent obj)
    {
        canvas.add(obj);
    }

    public void removeCanvas(BaseComponent obj)
    {
        canvas.remove(obj);
    }
}
