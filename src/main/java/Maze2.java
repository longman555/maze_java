/**
 * 「穴掘り法(道延ばし法)」を使って迷路を自動生成する
 */
import java.util.LinkedList;
import java.util.ArrayList;


public class Maze2 extends MazeBase {

    private ArrayList<Pos> sites = new ArrayList<>();

    public Maze2() { genMaze(); }

    protected void initMaze() {
        for (int j = 0; j <= YMAX; ++j) {
            for (int i = 0; i <= XMAX; ++i) {
                if (j<2 || j>YMAX-2 || i<2 || i>XMAX-2) { maze[j][i] = TRAP; }
                else { maze[j][i] = WALL; }
            }
        }
        int y = 0, x = 0;
        while (true) {
            y = rand.nextInt(17) + 4;
            if (y % 2 == 1) { break; }
        }

        while (true) {
            x = rand.nextInt(73) + 4;
            if (x % 2 == 1) { break; }
        }
        maze[y][x] = ROAD;
        sites.add(new Pos(x, y));
        setStartAndGoal();
    }

    private static Pos[] DIRS = new Pos[] {
        new Pos(0, -2), new Pos(2, 0), new Pos(0, 2), new Pos(-2, 0)
    };

    private static ArrayList<Pos[]> ALL_DIRS
        = new Permutation(DIRS).getAllPermutations();

    public char[][] genMaze() {
        initMaze();
        while (!sites.isEmpty()) {
            // Queueを使う？
            Pos site = sites.remove(rand.nextInt(sites.size()));
            Pos newPos = null;
            boolean found = false;
            for (Pos d : ALL_DIRS.get(rand.nextInt(ALL_DIRS.size()))) {
                newPos = new Pos(site.x+d.x, site.y+d.y);
                if (maze[newPos.y][newPos.x] == WALL) { found = true; break; }
            }
            if (! found) { continue; }
            maze[newPos.y][newPos.x] = ROAD;
            maze[(site.y+newPos.y)/2][(site.x+newPos.x)/2] = ROAD;
            sites.add(newPos);
            sites.add(site);
            site = newPos;
        }
        return maze;
    }

    public static void main(String[] args) {
        Maze2 maze2 = new Maze2();
        maze2.showMaze();
        maze2.solveMaze();
        maze2.showSolved();
    }
}
