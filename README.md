# NCU-UML
A UML project for NCU's OO class

## Basic Layouts
```java
JMenuBar menuBar = new JMenuBar();
```

## A.1

```java
public class UserInput extends JFrame implements MouseListener
{
    something.addMouseListener(this);
}
```

## B.1

```java
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class DragPanel extends JPanel 
{

    ImageIcon image = new ImageIcon("img/smile.png");
    final int WIDTH = image.getIconWidth();
    final int HEIGHT = image.getIconHeight();
    Point imageCorner;
    Point prevPt;

    DragPanel()
    {
        imageCorner = new Point(0, 0);
        ClickListener clickListener = new ClickListener();
        DragListener dragListener = new DragListener();

        this.addMouseListener(clickListener);
        this.addMouseMotionListener(dragListener);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        image.paintIcon(this, g, (int)imageCorner.getX(), (int)imageCorner.getY());
    }

    private class ClickListener extends MouseAdapter
    {
        public void mousePressed(MouseEvent e)
        {
            prevPt = e.getPoint();
        }
    }

    private class DragListener extends MouseMotionAdapter
    {
        public void mouseDragged(MouseEvent e)
        {
            Point currentPt = e.getPoint();

            imageCorner.translate(
                (int)(currentPt.getX() - prevPt.getX()), 
                (int)(currentPt.getY() - prevPt.getY())
            );
            prevPt = currentPt;
            repaint();
        }
    }
}
```


## F.1

### 3.a
```java
JOptionPane.showInputDialog("What is your name?", "Default");
```