package src.components.LineObjects;

import java.awt.Point;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;

import src.components.BaseComponent;

public class LineObject extends BaseComponent
{
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public LineObject()
    {

        this.setSize(2000, 2000);
        this.setOpaque(false);
        this.setBackground(null);
        this.setPosition(new Point(0, 0));
        this.addMouseListener(clickListener);
        zIndex = 15;
        clickValid = true;

    }

    @Override
    public void onClick(Point pt) {
        setInvalid();
    }

    @Override
    public void update(Point pt) {
        setStart( new Point(
            (int) startObj.getPosition().getX() + 50,
            (int) startObj.getPosition().getY() + 50 
        ));
        setEnd( new Point(
            (int) endObj.getPosition().getX() + 145,
            (int) endObj.getPosition().getY() + 125
        ));
        isValid();
    }

    public void setMode(String m) { mode = m; }
    public void setValid() { valid = true; }
    public void setInvalid() { valid = false; }
    public void setStart(Point pt) {
        STARTX = (int) pt.getX();
        STARTY = (int) pt.getY();
    }
    public void setEnd(Point pt) {
        ENDX = (int) pt.getX() - 95;
        ENDY = (int) pt.getY() - 75;
    }
    public void setStartObject(BaseComponent obj) { 
        STARTX = (int) obj.getPosition().getX();
        STARTY = (int) obj.getPosition().getY();
        startObj = obj; 
    }
    public void setEndObject(BaseComponent obj) {
        ENDX = (int) obj.getPosition().getX();
        ENDY = (int) obj.getPosition().getY();
        endObj = obj; 
    }
    public LineObject clone() { 
        LineObject c = new LineObject();

        c.setStartObject(startObj);
        c.setEndObject(endObj);
        c.startPort = startPort;
        c.endPort = endPort;
        c.setMode(mode);
        c.setValid();

        return c;
    }

    public boolean getValid() { return valid; }
    public BaseComponent getStartObject() { return startObj; }
    public BaseComponent getEndObject() { return endObj; }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Graphics2D painter = (Graphics2D) g;

        if (valid && (ENDX > 0 && ENDY > 0))
        {
    
            drawObject(painter);

        }
    }

    protected int STARTX = 0;
    protected int STARTY = 0;
    protected int ENDX = 0;
    protected int ENDY = 0;
    protected BaseComponent startObj;
    protected BaseComponent endObj;
    public Port startPort = new Port();
    public Port endPort = new Port();

    private String mode;
    private boolean valid;

    protected void drawObject(Graphics2D painter)
    {
        
        int startX = (int) (STARTX + startPort.getOffset().getX());
        int startY = (int) (STARTY + startPort.getOffset().getY());
        int endX   = (int) (ENDX   + endPort.getOffset().getX());
        int endY   = (int) (ENDY   + endPort.getOffset().getY());
            
        painter.setPaint(Color.BLACK);
        painter.drawLine(startX, startY, endX, endY);

        if (mode == "Association Line")
        {
            drawAssociationTail(painter);
        }else if (mode == "Composition Line")
        {
            drawCompositionTail(painter);
        }else if (mode == "Generation Line")
        {
            drawGenerationTail(painter);
        }
    }

    public class Port
    {
        public Port()
        {
            property = "";
        }

        public void setProperty(String s) { property = s; }

        public Point getOffset()
        {
            int XOffset = 0;
            int YOffset = 0;

            switch(property)
            {
                case "TOP":
                    XOffset = 0;
                    YOffset = -40;
                    break;
                case "BOTTOM":
                    XOffset = 0;
                    YOffset = 40;
                    break;
                case "LEFT":
                    XOffset = -40;
                    YOffset = 0;
                    break;
                case "RIGHT":
                    XOffset = 40;
                    YOffset = 0;
                    break;
                default:
                    break;
            }

            return new Point(XOffset, YOffset);
        }

        private String property;
    }

    private void drawAssociationTail(Graphics2D painter)
    {

        int startX = (int) (STARTX + startPort.getOffset().getX());
        int startY = (int) (STARTY + startPort.getOffset().getY());
        int endX   = (int) (ENDX   + endPort.getOffset().getX());
        int endY   = (int) (ENDY   + endPort.getOffset().getY());

        int dx = endX - startX;
        int dy = endY - startY;

        double D = Math.sqrt(dx * dx + dy * dy);

        double arrowLeftX = D - 16;
        double arrowRightX = arrowLeftX;
        double arrowLeftY = 12;
        double arrowRightY = -12;
        double x;

        double sin = dy / D;
        double cos = dx / D;

        x = arrowLeftX * cos - arrowLeftY * sin + startX;
        arrowLeftY = arrowLeftX * sin + arrowLeftY * cos + startY;
        arrowLeftX = x;

        x = arrowRightX * cos - arrowRightY * sin + startX;
        arrowRightY = arrowRightX * sin + arrowRightY * cos + startY;
        arrowRightX = x;

        painter.setColor(Color.DARK_GRAY);
        painter.setStroke(new BasicStroke(3));
        painter.drawLine(endX, endY, (int) arrowLeftX, (int) arrowLeftY);
        painter.drawLine(endX, endY, (int) arrowRightX, (int) arrowRightY);
    }

    void drawCompositionTail(Graphics2D painter)
    {

        int startX = (int) (STARTX + startPort.getOffset().getX());
        int startY = (int) (STARTY + startPort.getOffset().getY());
        int endX   = (int) (ENDX   + endPort.getOffset().getX());
        int endY   = (int) (ENDY   + endPort.getOffset().getY());

        int dx = endX - startX;
        int dy = endY - startY;

        double D = Math.sqrt(dx * dx + dy * dy);

        double arrowLeftX = D - 10;
        double arrowRightX = arrowLeftX;
        double arrowLeftY = 10;
        double arrowRightY = -10;
        double x;

        double sin = dy / D;
        double cos = dx / D;

        x = arrowLeftX * cos - arrowLeftY * sin + startX;
        arrowLeftY = arrowLeftX * sin + arrowLeftY * cos + startY;
        arrowLeftX = x;

        x = arrowRightX * cos - arrowRightY * sin + startX;
        arrowRightY = arrowRightX * sin + arrowRightY * cos + startY;
        arrowRightX = x;

        double arrowBackX = arrowLeftX + (arrowRightX - endX);
        double arrowBackY = arrowLeftY + (arrowRightY - endY);

        int[] xpoints = { endX, (int) arrowLeftX, (int) arrowBackX, (int) arrowRightX };
        int[] ypoints = { endY, (int) arrowLeftY, (int) arrowBackY, (int) arrowRightY };

        painter.setStroke(new BasicStroke(6));

        painter.setColor(Color.GRAY);
        painter.drawPolygon(xpoints, ypoints, 4);

        painter.setColor(Color.WHITE);
        painter.fillPolygon(xpoints, ypoints, 4);
    }

    void drawGenerationTail(Graphics2D painter)
    {

        int startX = (int) (STARTX + startPort.getOffset().getX());
        int startY = (int) (STARTY + startPort.getOffset().getY());
        int endX   = (int) (ENDX   + endPort.getOffset().getX());
        int endY   = (int) (ENDY   + endPort.getOffset().getY());

        int dx = endX - startX;
        int dy = endY - startY;

        double D = Math.sqrt(dx * dx + dy * dy);

        double arrowLeftX = D - 16;
        double arrowRightX = arrowLeftX;
        double arrowLeftY = 8;
        double arrowRightY = -8;
        double x;

        double sin = dy / D;
        double cos = dx / D;

        x = arrowLeftX * cos - arrowLeftY * sin + startX;
        arrowLeftY = arrowLeftX * sin + arrowLeftY * cos + startY;
        arrowLeftX = x;

        x = arrowRightX * cos - arrowRightY * sin + startX;
        arrowRightY = arrowRightX * sin + arrowRightY * cos + startY;
        arrowRightX = x;

        int[] xpoints = { endX, (int) arrowLeftX, (int) arrowRightX };
        int[] ypoints = { endY, (int) arrowLeftY, (int) arrowRightY };

        painter.setStroke(new BasicStroke(6));

        painter.setColor(Color.GRAY);
        painter.drawPolygon(xpoints, ypoints, 3);

        painter.setColor(Color.WHITE);
        painter.fillPolygon(xpoints, ypoints, 3);
    }
}
