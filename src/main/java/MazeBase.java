import java.util.ArrayList;
import java.util.LinkedList;

class Pos {

    public final int x;
    public final int y;
    public Pos(int x, int y) { this.x = x; this.y = y; }

    @Override
    public boolean equals(Object other) {
        if (other == this) { return true; }
        if (!(other instanceof Pos)) { return false; }
        Pos pos = (Pos)other;
        return pos.x == this.x && pos.y == this.y;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + this.x;
        result = 31 * result + this.y;
        return result;
    }
}

public abstract class MazeBase {

    public final static int XMAX = 80;
    public final static int YMAX = 24;

    public final static char TRAP = '.';
    public final static char WALL = '#';
    public final static char ROAD = ' ';
    public final static char SELF = 'i';

    protected final static Pos START_POS = new Pos(/*x=*/ 2, /*y=*/ 3);
    protected final static Pos GOAL_POS = new Pos(/*x=*/ XMAX-2, /*y=*/ YMAX-3);
    protected final static char START = 'S';
    protected final static char GOAL = 'G';

    protected char[][] maze;
    protected ArrayList<Pos> path;


    // protected‚ðŽŽ‚µ‚Ä‚Ý‚é
    public MazeBase() {
        maze = new char[YMAX+1][XMAX+1];
        path = new ArrayList<>();
    }

    public void showMaze() {
        for (char[] row : maze) { System.out.println(new String(row)); }
    }

    static public ArrayList<Pos> solveMaze(char[][] maze) {
        LinkedList<Pos> frontier = new LinkedList<>();
        frontier.add(START_POS);
        
        while (!frontier.isEmpty()) {
            Pos pos = frontier.poll();

        }

        return new ArrayList<Pos>();
    }
    public ArrayList<Pos> solveMaze() {
        this.path = solveMaze(maze);
        return path;
    }

    public abstract char[][] genMaze();
    protected abstract void initMaze();
}
