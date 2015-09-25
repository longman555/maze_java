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


    protected static Pos[] DIRECTIONS
        = new Pos[] { new Pos(1, 0), new Pos(0, 1), new Pos(-1, 0), new Pos(0, -1) };

    // protected‚ðŽŽ‚µ‚Ä‚Ý‚é
    public MazeBase() {
        maze = new char[YMAX+1][XMAX+1];
        path = new ArrayList<>();
    }

    public void showMaze() {
        for (char[] row : maze) { System.out.println(new String(row)); }
    }

    static private boolean checkPos(Pos newPos, char[][] maze, ArrayList<Pos> p) {
        int x = newPos.x, y = newPos.y;
        if (x<START_POS.x || y<START_POS.y || x>GOAL_POS.x || y>GOAL_POS.y) {
            return false;
        }
        if (maze[y][x] == WALL) { return false; }
        if (p.contains(newPos)) { return false; }
        return true;
    }

    static private ArrayList<Pos> solveMazeByLoop(char[][] maze) {
        ArrayList<Pos> path = new ArrayList<>();
        path.add(START_POS);
        LinkedList<ArrayList<Pos>> frontier = new LinkedList<>();
        frontier.add(path);
        while (!frontier.isEmpty()) {
            path = frontier.poll();
            last = path.get(path.size()-1);
            if (GOAL_POS.equals(last)) { return path; }
            for (Pos d : DIRECTIONS) {
                Pos newPos = new Pos(last.y+d.y, last.x+d.x);
                if (!checkPos(newPos, maze, path)) { continue; }
                ArrayList<Pos> copy = new ArrayList<>(path);
                copy.add(newPos);
                frontier.add(copy);
            }
        }
        return new ArrayList<Pos>();
    }

    static private ArrayList<Pos> solveMazeByRecurHelper(char[][] maze,
                                                         ArrayList<Pos> path) {
        Pos last = path.get(path.size()-1);
        if (GOAL_POS.equals(last)) { return path; }
        for (Pos d : DIRECTIONS) {
            Pos newPos = new Pos(last.y+d.y, last.x+d.x);
            if (!checkPos(newPos, maze, path)) { continue; }
            ArrayList<Pos> copy = new ArrayList<>(path);
            copy.add(newPos);
            ArrayList<Pos> result = solveMazeByRecurHelper(maze, copy);
            if (!result.isEmpty()) { return result; }
        }
        return new ArrayList<Pos>();
    }

    static private ArrayList<Pos> solveMazeByRecur(char[][] maze) {
        ArrayList<Pos> path = new ArrayList<>();
        path.add(START_POS);
        return solveMazeByRecurHelper(maze, path);
    }

    static public ArrayList<Pos> solveMaze(char[][] maze) {
        return solveMazeByLoop(maze);
//        return solveMazeByRecur(maze);
    }
    public ArrayList<Pos> solveMaze() {
        this.path = solveMaze(maze);
        return path;
    }

    public abstract char[][] genMaze();
    protected abstract void initMaze();
}
