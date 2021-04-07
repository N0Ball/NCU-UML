package src.frame;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import src.panel.canvas.Canvas;
import src.panel.menuBar.MenuBar;
import src.panel.sidebar.Sidebar;

public class MainFrame extends JFrame
{

    public MenuBar menuBar;
    public Canvas canvas;
    public Sidebar sidebar;

    public MainFrame()
    {

        menuBar = new MenuBar();
        canvas = new Canvas();
        sidebar = new Sidebar();

        this.setTitle("OO UML");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 600);

        this.add(sidebar, BorderLayout.WEST);
        this.add(canvas, BorderLayout.CENTER);
        this.setJMenuBar(menuBar);
        this.setVisible(true);

    }
}