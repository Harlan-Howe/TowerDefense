import java.awt.*;

public class Shot
{
    private Coordinates pt1, pt2;
    private Color myColor;
    private static Stroke shotThickLine;
    private static Stroke thinLine;
    private int framesDrawn;

    public Shot(Coordinates gunLoc, Coordinates invaderLoc, Color color)
    {
        pt1 = gunLoc;
        // TODO #0e: Activate this code once you have written Coordinates:
//        pt2 = new Coordinates((int) invaderLoc.getX(), (int) invaderLoc.getY());
        myColor = color;
        if (null == shotThickLine)
        {
            shotThickLine = new BasicStroke(2);
            thinLine = new BasicStroke(1);
        }
        framesDrawn = 0;
    }

    public void drawSelf(Graphics2D g)
    {
        // TODO #0f: Activate this code once you have written Coordinates:
        /*
        g.setStroke(shotThickLine);
        g.setColor(myColor);
        g.drawLine(pt1[0],pt1[1],pt2[0],pt2[1]);
        g.setStroke(thinLine);
         */
        framesDrawn++;
    }

    public int getFramesDrawn() {return framesDrawn;}
}
