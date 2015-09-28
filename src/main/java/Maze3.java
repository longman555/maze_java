/**
 * 「壁のばし法」を使って迷路を自動生成する
 */
import java.util.ArrayList;

public class Maze3 extends MazeBase {

    private ArrayList<Pos> sites = new ArrayList<>();

    public Maze3() { genMaze(); }

    @Override
    protected void initMaze() {
        for (int j = 0; j <= YMAX; ++j) {
            for (int i = 0; i <= XMAX; ++i) {
                if (j < 2 || j > YMAX-2 || i < 2 || i > XMAX-2) {
                    maze[j][i] = TRAP;
                }
                else if ( j == 2 || j == YMAX-2 || i == 2 || i == XMAX-2 ) {
                    maze[j][i] = WALL;
                }
                else {
                    maze[j][i] = ROAD;
                }
            }
        }

        for (int i = 4; i <= XMAX-4; i += 2) {
            sites.add(new Pos(i, 2)); sites.add(new Pos(i, YMAX-2));
        }

        for (int j = 4; j <= YMAX-4; j += 2) {
            sites.add(new Pos(2, j)); sites.add(new Pos(XMAX-2, j));
        }

        setStartAndGoal();
    }

    static Pos[] DIRTS = new Pos[] {
        new Pos(-2, 0), new Pos(0, -2), new Pos(0, 2), new Pos(2, 0)
    };

    static ArrayList<Pos[]> ALL_DIRTS
        = new Permutation<Pos>(DIRTS).getAllPermutations();

    @Override
    public char[][] genMaze() {
        initMaze();

        while (!sites.isEmpty()) {
            Pos site = sites.remove(rand.nextInt(sites.size()));
            while (true) {
                Pos newPos = null; boolean found = false;
                for (Pos d : ALL_DIRTS.get(rand.nextInt(ALL_DIRTS.size()))) {
                    newPos = new Pos(site.x+d.x, site.y+d.y);
                    if (maze[newPos.y][newPos.x] == ROAD) { found = true; break; }
                }
                if (!found) { break; }
                maze[newPos.y][newPos.x] = WALL;
                maze[(site.y+newPos.y)/2][(site.x+newPos.x)/2] = WALL;
                sites.add(site);
                sites.add(newPos);
                site = newPos;
            }
        }
        return maze;
    }

    public static void main(String[] args) {
        Maze3 maze3 = new Maze3();
        maze3.showMaze();
        maze3.solveMaze();
        maze3.showSolved();
    }
}
