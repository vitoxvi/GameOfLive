
public class GameOfLife {

    // world size
    final static int X = 15;
    final static int Y = 5;

    final static int MAXROUNDS = 5;

    public static void main(String[] args) {

        boolean[][] world = initializeWorld();
        //boolean[][] world = initializeWorldRandomized();
        System.out.println("Game of Life\nInitial world:");
        showWorld(world);

        for (int round = 1; round <= MAXROUNDS; round++) {
            System.out.println("\nGeneration " + round);
            world = simulateGame(world);
            showWorld(world);
        }
    }

    private static boolean[][] simulateGame(boolean[][] world) {
        boolean[][] futureWorld = new boolean[Y][X];

        for (int y = 0; y < Y; y++) {
            for (int x = 0; x < X; x++) {
                //int neighbours = countNeighbours(world, y, x);
                int neighbours = checkForNeighbours(world, x, y);
                boolean currentCellState = world[y][x];
                boolean newCellState = isCellALife(currentCellState, neighbours);

                //if ( currentCellState != newCellState) {
                //    System.out.println("changed at ("+x+","+y+"): " + neighbours + " " + currentCellState + " --> " + newCellState);
                //}
                futureWorld[y][x] = newCellState;
            }
        }
        return futureWorld;
    }

    // Aufgabe_Conway_Wahrheitstabelle.pdf
    private static boolean isCellALife(boolean isCellLiving, int neighbours) {
        if (neighbours == 3) return true;
        if (neighbours == 2 && isCellLiving) return true;
        return false;
    }

    private static int countNeighbours(boolean[][] world, int x, int y) {
        int counter = 0;
        for (int i = x - 1; i <= x + 1; ++i) {
            for (int j = y - 1; j <= y + 1; ++j) {
                if (j < 0 || j >= world[0].length || i < 0 || i >= world.length) { // skip world border
                    continue;
                }
                if (world[i][j]) {
                    counter += 1;
                }
            }
        }

        if (world[x][y]) { // don't count the living cell itself
            counter -= 1;
        }
        return counter;
    }

    private static int checkForNeighbours(boolean[][] world, int x, int y) { // Variante ...
        int neighborCounter = 0;
        int[][] neighborPositions = {
                {1, -1},
                {1, 0},
                {1, 1},
                {0, -1},
                {0, 1},
                {-1, -1},
                {-1, 0},
                {-1, 1}
        };

        int max_x_index = world[0].length-1;
        int max_y_index = world.length-1;

        for (int[] neighbor : neighborPositions) {
            int neighborYPosition = y + neighbor[0];
            int neighborXPosition = x + neighbor[1];

            // check for world's end
            if (neighborYPosition < 0 || neighborYPosition > max_y_index || neighborXPosition < 0 || neighborXPosition > max_x_index) {
                // if position out of range go to the next position
                continue;
            }
            if (world[neighborYPosition][neighborXPosition]) {
                neighborCounter++;
            }
        }
        return neighborCounter;
    }

    private static boolean[][] initializeWorld() {
        boolean[][] world = new boolean[Y][X];

        // initalize world, all cells are dead
        for (int y = 0; y < Y; y++) {
            for (int x = 0; x < X; x++) {
                world[y][x] = false;
            }
        }
        // set some cells alive
        world[3][4] = true;
        world[3][5] = true;
        world[3][6] = true;

        return world;

    }

    private static boolean[][] initializeWorldRandomized() {
        boolean[][] world = new boolean[Y][X];

        for (int y = 0; y < Y; y++) {
            for (int x = 0; x < X; x++) {
                world[y][x] = Math.random() > 0.7;  // 30% alive
            }
        }
        return world;
    }

    private static void showWorld(boolean[][] world) {
        for (boolean[] line : world) {
            for (boolean cell : line) {
                if (cell) {
                    System.out.print(" X ");
                } else {
                    System.out.print(" - ");
                }
            }
            System.out.println();
        }
    }
}