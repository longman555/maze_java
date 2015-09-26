/**
 * u–_“|‚µ–@v‚ğg‚Á‚Ä–À˜H‚ğ©“®¶¬‚·‚é
 */
import java.util.Random;
import java.util.LinkedList;

public class Maze1 extends MazeBase {

    public Maze1() {
        genMaze();
    }

    protected void initMaze() {
        for (int j = 0; j <= YMAX; ++j) {
            for (int i = 0; i <= XMAX; ++i) {
                if (j < 2 || j > YMAX-2 || i < 2 || i > XMAX-2) {
                    maze[j][i] = TRAP;
                }
                else if ( (j == 2 || j == YMAX-2) && (i >=2 && i <= XMAX-2) 
                        || (i == 2 || i == XMAX-2) ) {
                    maze[j][i] = WALL;
                }
                else {
                    maze[j][i] = ROAD;
                }
            }
        }

        for (int j = 4; j <= YMAX-4; j += 2) {
            for (int i = 4; i <= XMAX-4; i += 2) { maze[j][i] = WALL; }
        }
        setStartAndGoal();
    }

    private static Pos getDirection(boolean firstRow) {
        return (firstRow?DIRECTIONS[rand.nextInt(4)]:DIRECTIONS[rand.nextInt(3)]);
    }

    public char[][] genMaze() {
        initMaze();

        for (int i = 4; i <= XMAX-4; i += 2) {
            while (true) {
                Pos pos = getDirection(true);
                if (maze[4+pos.y][i+pos.x] == ROAD) {
                    maze[4+pos.y][i+pos.x] = WALL;
                    break;
                }
            }
        }

        for (int j = 6; j <= YMAX-4; j += 2) {
            for (int i = 4; i <= XMAX-4; i += 2) {
                while (true) {
                    Pos pos = getDirection(false);
                    if (maze[j+pos.y][i+pos.x] == ROAD) {
                        maze[j+pos.y][i+pos.x] = WALL;
                        break;
                    }
                }
            }
        }
        return maze;
    }

    public static void main(String[] args) {
        Maze1 maze1 = new Maze1();
        maze1.showMaze(); System.out.println();
        LinkedList<Pos> path = maze1.solveMaze();
        System.out.printf("[main] Found Path Length -> %d\n", path.size());
        maze1.showSolved(); System.out.println();
    }
}
