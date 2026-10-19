import java.awt.*;
import java.util.ArrayList;

public class Turret
{
    private int myRange; // detection radius for invaders (center-to-center)
    private double myRechargeTime; // minimum time between shots for this turret
    private int myDamage; // how much damage this turret does
    private int myType; // my type of turret.

    private World myWorld; // a pointer to the world where this turret will live.
    private double myTimeSinceLastFire;
    private Coordinates myLoc;
    private double myGunAngle;

    // constants for the class, not the individual turret. (i.e., static variables)
    // note "final" means we're defining them here, and they may never change.
    private static Stroke thinLine;
    private static Stroke thickLine;
    private static Stroke gunLine;
    private static final Color[] shotColors = {Color.GREEN, Color.BLUE, Color.PINK};
    public static final int[] RANGES = {70, 100, 150};
    public static final double[] RECHARGE_TIMES = {1.0, 2.0, 6.0};
    public static final int[] DAMAGES = {10, 15, 40};

    public Turret(int type, World myWorld)
    {
        // based on "type," select the range, recharge time, and damage done by this turret.
        this.myRange = RANGES[type];
        this.myRechargeTime = RECHARGE_TIMES[type];
        this.myDamage = DAMAGES[type];
        this.myType = type;

        this.myWorld = myWorld;
        // reset recharge to fully charged.
        resetTimer();

        // start this turret offscreen.
        
        // TODO #0g: Activate this once you have written Coordinates.
        //myLoc = new Coordinates(-100,-100);

        // the gun starts pointing east.
        myGunAngle = 0;

        // these are static variables, so if this is the first turret we created, we need to initialize them once.
        if (null == thickLine)
        {
            thinLine = new BasicStroke(1);
            thickLine = new BasicStroke(2);
            gunLine = new BasicStroke(4);
        }
    }

    public Coordinates getMyLoc() {return myLoc;}

    public void setMyLoc(Coordinates myLoc)
    {
        this.myLoc = myLoc;
    }

    public void setMyLoc(int x, int y)
    {
        // TODO #0h: Activate this once you have written Coordinates.
//        myLoc = new Coordinates(x,y);
    }

    /**
     * resets the timer so that this turret is fully charged again.
     */
    public void resetTimer() {myTimeSinceLastFire = 999;}

    /**
     * draws this turret, centered at myLoc, depending on what this turret's "myType" is.
     * @param g - the Graphics object that indicates where on the screen to draw and the tools to do it.
     *          Note: this usually will be the main panel, but this method also gets called and sent a graphics for
     *                the images used in the buttons at the top of the screen.
     */
    public void drawSelf(Graphics g)
    {

        // TODO #0i: activate this code once you have written Coordinates.
        /*

        // Note: we sometimes cast "g" as a "Graphics2D" object, so we can use stroke commands to make the lines thicker.
        // most of the time in modern programs, g really is an instance of this subclass of Graphics, so it works!
        switch (myType)
        {
            case 0:
                g.setColor(Color.BLACK);
                g.drawOval(myLoc.getX()-18, myLoc.getY()-18, 36, 36);
                g.drawLine(myLoc.getX()-12, myLoc.getY()-12, myLoc.getX()+12, myLoc.getY()+12);
                g.drawLine(myLoc.getX()-12, myLoc.getY()+12, myLoc.getX()+12, myLoc.getY()-12);
                break;
            case 1:
                g.setColor(Color.BLACK);
                ((Graphics2D)g).setStroke(thickLine); // use a thicker pen.
                g.drawOval(myLoc.getX()-18, myLoc.getY()-18, 36, 36);
                g.drawLine(myLoc.getX()-12, myLoc.getY()-12, myLoc.getX()+12, myLoc.getY()+12);
                g.drawLine(myLoc.getX()-12, myLoc.getY()+12, myLoc.getX()+12, myLoc.getY()-12);
                ((Graphics2D)g).setStroke(thinLine); // restore the pen to thin for the next thing we draw.
                break;
            case 2:
            default:
                g.setColor(Color.BLACK);
                int[] x_vals = {myLoc.getX()-18, myLoc.getX()-9, myLoc.getX()+9, myLoc.getX()+18,
                myLoc.getX()+9, myLoc.getX()-9, myLoc.getX()-18};
                int[] y_vals = {myLoc.getY(), myLoc.getY()-18, myLoc.getY()-18, myLoc.getY(), myLoc.getY()+18, myLoc.getY()+18, myLoc.getY()};
                g.drawPolygon(x_vals, y_vals, 7);
                for (int i=0; i<3; i++)
                    g.drawLine(x_vals[i], y_vals[i], x_vals[i+3], y_vals[i+3]);
        }
         */

        // draw the circle indicating the state of this gun's charge
        drawRecharge((Graphics2D)g);
        // draw the gun on this turret to indicate where it is pointing.
        drawGun((Graphics2D)g);
    }

    /**
     * draws a partial ring around the center of this turret. The color and completeness of the ring indicate whether
     * it is ready to fire.
     * @param g - the Graphics object that indicates where on the screen to draw and the tools to do it.
     */
    private void drawRecharge(Graphics2D g)
    {
        // TODO #0j: Activate this code once you have written Coordinates.
        /*
        g.setStroke(thickLine);
        if (myTimeSinceLastFire >= myRechargeTime)
        {
            g.setColor(Color.RED);
            g.drawOval(myLoc.getX()-7, myLoc.getY()-7, 14, 14);
        }
        else
        {
            int angle = (int)( 360 * myTimeSinceLastFire/ myRechargeTime);
            g.setColor(new Color(255, 255-255*angle/720, 0));
            g.drawArc(myLoc.getX()-7, myLoc.getY()-7, 14, 14, 0, angle);
        }
        g.setStroke(thinLine);
         */
    }

    /**
     * draws the gun (the actual turret on this turret)
     * @param g - the Graphics object that indicates where on the screen to draw and the tools to do it.
     */
    private void drawGun(Graphics2D g)
    {
        // TODO #0k: Activate this code once you have written Coordinates.
        /*
        g.setColor(Color.BLACK);
        g.setStroke(gunLine);
        g.drawLine(myLoc.getX(),myLoc.getY(),(int)(myLoc.getX()+20*Math.cos(myGunAngle)), (int)(myLoc.getY()+20*Math.sin(myGunAngle)));
        g.setStroke(thinLine);
         */
    }

    /**
     * updates the turret to reflect an animation step. In this case, it just recharges the gun a little bit.
     * @param deltaT - the time, in seconds, since the last animation step.
     */
    public void advance(double deltaT)
    {
        myTimeSinceLastFire += deltaT;
    }

    /**
     * aims the turret at the nearest invader in range. If the gun is fully charged, it shoots the invader.
     */
    public void targetNearestInvader()
    {
        ArrayList<Invader> invaders = myWorld.getInvaderList();


        // identify which invader is nearest to this gun.
        // TODO #10: loop through all the invaders and identify which one is the nearest to this turret, and what that
        //  distance-squared is.
        double nearestDist2 = 1;  // obviously not right. This is the distance squared to the nearest turret.
        Invader nearestInv = null;


        // TODO #0l: Activate this code once you have written Coordinates.
        /*
        // if we've identified a nearest invader and it is within range...
        if (nearestInv != null && (nearestDist2 < Math.pow(myRange,2)))
        {

            // point the gun at this nearest invader.
            double[] invLoc = nearestInv.getLoc();
            myGunAngle = Math.atan2(nearestInv.getLoc()[1]-myLoc.getY(),nearestInv.getLoc()[0]-myLoc.getX());

            // if my gun is charged, fire at the invader!
            if (myTimeSinceLastFire >= myRechargeTime)
            {
                myWorld.addShot(myLoc, invLoc, shotColors[myType]);
                myWorld.damageInvader(nearestInv, myDamage);
                myTimeSinceLastFire = 0; // and the gun now needs to recharge.
            }
        }

         */
    }
}
