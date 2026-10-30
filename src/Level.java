import java.util.ArrayList;
import java.util.Arrays;

public class Level
{
    private final int whichLevelIsThis;
    private final int[] startingEnemyCounts;

    public Level(int whichLevel, int[] enemyCounts)
    {
        this.whichLevelIsThis = whichLevel;
        startingEnemyCounts = enemyCounts;
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
                ", whichLevelIsThis=" + whichLevelIsThis +
                '}';
    }
}
