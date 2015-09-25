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
            Pos last = path.get(path.size()-1);
            if (GOAL_POS.equals(last)) { return path; }
            for (Pos d : DIRECTIONS) {
                Pos newPos = new Pos(last.x+d.x, last.y+d.y);
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
            Pos newPos = new Pos(last.x+d.x, last.y+d.y);
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
        if (path.isEmpty()) { System.out.println("empty!"); }
        return path;
    }

    static public void showMaze(char[][] maze) {
        for (char[] row : maze) { System.out.println(new String(row)); }
    }

    public void showMaze() { showMaze(maze); }

    public void showSolved() {
        char[][] maze2 = new char[maze.length][maze[0].length];
        for (int j = 0; j < maze.length; ++j) {
            for (int i = 0; i < maze[j].length; ++i) {
                maze2[j][i] = maze[j][i];
            }
        }
        for (Pos pos : path) {
            maze2[pos.y][pos.x] = SELF;
        }
        maze2[START_POS.y][START_POS.x-2] = START;
        maze2[START_POS.y][START_POS.x-1] = '>';
        maze2[GOAL_POS.y][GOAL_POS.x+1] = '<';
        maze2[GOAL_POS.y][GOAL_POS.x+2] = GOAL;
        showMaze(maze2);
    }

    public abstract char[][] genMaze();
    protected abstract void initMaze();
}
