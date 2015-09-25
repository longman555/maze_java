/**
 * u–_“|‚µ–@v‚ğg‚Á‚Ä–À˜H‚ğ©“®¶¬‚·‚é
 */
import java.util.Random;


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
        maze[START_POS.y][START_POS.x] = START;
        maze[GOAL_POS.y][GOAL_POS.x] = GOAL;
    }

//    private static Pos[] DIRECTIONS
//        = new Pos[] { new Pos(1, 0), new Pos(0, 1), new Pos(-1, 0), new Pos(0, -1) };
    private static Random rand = new Random();
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
        maze1.showMaze();
    }
}
