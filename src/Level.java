import java.util.ArrayList;
import java.util.Arrays;

public class Level
{
    private final int whichLevelIsThis;
    private final ArrayList<Coordinates> path;
    private final int[] startingEnemyCounts;

    public Level(int whichLevel)
    {
        this.whichLevelIsThis = whichLevel;
        path = new ArrayList<>();
        startingEnemyCounts = new int[Invader.NUM_INVADER_TYPES];
        loadFromFile();
    }

    public void loadFromFile()
    {
          //TODO # 0d: Once you have created a Coordinates class, you can activate this section of temporary code to see a few
          // path elements onscreen. HOWEVER... this is just TEMPORARY.
        /*
        path.add(new Coordinates(20,380));
        path.add(new Coordinates(60,380));
        path.add(new Coordinates(100,380));
        path.add(new Coordinates(100,340));
        path.add(new Coordinates(100,300));
        path.add(new Coordinates(100,260));
        startingEnemyCounts[0] = 5;
        startingEnemyCounts[1] = 8;
        startingEnemyCounts[2] = 2;
        */

        // TODO #1: open up a file (e.g. "level0.tsv") and read in the information about the starting enemy counts and the
        //       path.

        // note that the number of invader types is Invader.NUM_INVADER_TYPES - I suggest you use this constant, instead
        //     of hard-coding in "3," in case you decide to change this.

    }

    public ArrayList<Coordinates> getPath()
    {
        return path;
    }

    public int[] getStartingEnemyCounts()
    {
        return startingEnemyCounts;
    }

    @Override
    public String toString()
    {
        return "Level{" +
                "startingEnemyCounts=" + Arrays.toString(startingEnemyCounts) +
                ", path=" + path +
                ", whichLevelIsThis=" + whichLevelIsThis +
                '}';
    }
}
