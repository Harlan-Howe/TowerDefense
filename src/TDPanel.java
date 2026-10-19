import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class TDPanel extends JPanel implements MouseListener, MouseMotionListener
{
    private final World myWorld;
    private final TDFrame myParent;
    private final AnimationThread myThread;
    private int status;
    private Turret currentTurret;

    public TDPanel(TDFrame parent)
    {
        super();
        myParent = parent;
        setBackground(Color.LIGHT_GRAY);
        myWorld = new World(this);
        myThread = new AnimationThread();
        status = TDFrame.STATUS_WAITING;
        addMouseListener(this);
        addMouseMotionListener(this);
        myThread.start();
    }

    public void placeTurret(int turretType)
    {
        currentTurret = new Turret(turretType, myWorld);
        status = TDFrame.STATUS_PLACING;
        requestFocus();
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.drawString("Level: "+myWorld.getCurrentLevelNum(), 10, 10);
        myWorld.drawPath(g);
        myWorld.drawShots(g);
        myWorld.drawInvaders(g);
        myWorld.drawTurrets(g);
        if (TDFrame.STATUS_PLACING == status)
            currentTurret.drawSelf(g);
    }

    public void turretKilledInvader(int type)
    {
        myParent.addCash(Invader.REWARDS[type]);
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {

    }

    @Override
    public void mousePressed(MouseEvent e)
    {

    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        if ((TDFrame.STATUS_PLACING == status) && (null != currentTurret))
        {
            // Rounding to centered multiples of World.CELL_SIZE.
            int destX = World.CELL_SIZE/2 + (e.getX()/ World.CELL_SIZE)*World.CELL_SIZE;
            int destY = World.CELL_SIZE/2 + (e.getY()/ World.CELL_SIZE)*World.CELL_SIZE;
            ArrayList<Coordinates> path = myWorld.getCurrentPath();
            // TODO #11: Loop through the (x,y) locations in the path. If
            //       any of these (x,y) values match the destX, destY, call "myParent.overrideStatusMessage" with an
            //       explanation that you can't drop the turret here, and return from this method immediately, without
            //       adding a turret or changing the status..

            myWorld.addTurret(currentTurret, destX, destY);
            status = TDFrame.STATUS_WAITING;
            myParent.setStatus(TDFrame.STATUS_WAITING);
            repaint();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {

    }

    @Override
    public void mouseExited(MouseEvent e)
    {

    }

    @Override
    public void mouseDragged(MouseEvent e)
    {

    }

    @Override
    /**
     * responds to any time the mouse moves within this pane. When we are in STATUS_PLACING, the location of the current
     * turret should be updated to match the mouse, but locked to 40 x 40 gridsquares.
     */
    public void mouseMoved(MouseEvent e)
    {
        if ((TDFrame.STATUS_PLACING== status) && (null != currentTurret ))
        {
            // Note: "e.getX()/40" is integer division, so it yields an integer, even if we're about to multiply by 40!
            currentTurret.setMyLoc(20+(e.getX()/40)*40, 20+(e.getY()/40)*40);
            repaint();
        }
    }

    public void startRun()
    {
        myThread.restart();
        myWorld.spawnInvader(); // Begin the run with at least one invader.
        status = TDFrame.STATUS_RUNNING;
    }

    public void stopRun()
    {
        status = TDFrame.STATUS_WAITING;
        myParent.setStatus(TDFrame.STATUS_WAITING);
    }

    class AnimationThread extends Thread
    {
        private long start;
        public void run() // this is what gets called when we tell this thread to start(). You should _NEVER_ call this
        // method directly.
        {
            start = System.currentTimeMillis();
            long difference;
            System.out.println("Starting Expansion Thread.");
            while (true)
            {
                if (TDFrame.STATUS_RUNNING == status)
                {
                    difference = System.currentTimeMillis() - start;
                    start = System.currentTimeMillis();

                    myWorld.updateAllObjects((int) difference);
                    repaint();
                    if (myWorld.getNumRemainingInvadersToSpawn() + myWorld.getInvaderList().size() == 0)
                    {
                        stopRun();
                        myWorld.advanceLevel();
                        repaint();
                    }
                }
                try
                {
                    //noinspection BusyWait
                    Thread.sleep(25); // wait a 1/40th second before you consider running again.
                } catch (InterruptedException iExp)
                {
                    System.out.println("AnimationThread was interrupted.");
                    break;
                }

            }
        }

        /**
         * resets the value of "start" to the present when the user begins a run... otherwise, the first deltaT would
         * be huge!
         */
        public void restart()
        {
            start = System.currentTimeMillis();
        }
    }

}
