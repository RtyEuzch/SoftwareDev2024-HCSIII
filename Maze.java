public class Maze extends JComponent {
    public static final int CHANCE_OF_WALL = .4;
    public static final int CHANCE_OF_PATH = 60;
    private Tile[][] grid;
    private int tileDimensions;

    public Maze(Tile[][] grid, int dimension) {
        this.grid = grid;
        tileDimensions = dimension;
    }

    /**
     * Paints every Tile as listed in the 2D array of Tiles
     * @param g
     */
    public void paint(Graphics g) {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {

            }
        }
    }

    /**
     * Creates a maze by randomly populating the 2D array and then checking
     * maze to ensure it is solvable through a DFS algorithm.
     * @param gridDimension the dimension of the grid, used to determine the   
     *                      difficulty of the maze.
     */
    private void buildMaze() {
        boolean isValid = false;
        while (!isValid) {
            for (int row = 0; row < grid.length; row++) {
                for (int col = 0; col < grid[0].length; col++) {
                    double chance = Math.random();
                    if (chance < CHANCE_OF_WALL) {
                        if (!(row == grid.length - 1 && col == 0)
                            && !(row == 0 && col == grid[0].length - 1)) {
                            if (grid.length == Tile.EASY_DIMENSION) {
                                grid[row][col] = new TileEasy(
                                                    col * Tile.EASY_DIMENSION,
                                                    row * Tile.EASY_DIMENSION,
                                                    tileDimensions,
                                                    Tile.WALL);
                            } else {
                                grid[row][col] = new TileHard(
                                                    col * Tile.HARD_DIMENSION,
                                                    row * Tile.HARD_DIMENSION,
                                                    tileDimensions,
                                                    Tile.WALL);
                            }
                        }
                    } else {
                        if (!(row == grid.length - 1 && col == 0)
                            && !(row == 0 && col == grid[0].length - 1)) {
                            if (grid.length == Tile.EASY_DIMENSION) {
                                grid[row][col] = new TileEasy(
                                                    col * Tile.EASY_DIMENSION,
                                                    row * Tile.EASY_DIMENSION,
                                                    tileDimensions,
                                                    Tile.PATH);
                            } else {
                                grid[row][col] = new TileHard(
                                                    col * Tile.HARD_DIMENSION,
                                                    row * Tile.HARD_DIMENSION,
                                                    tileDimensions,
                                                    Tile.PATH);
                            }
                        }
                    }
                }
            }  
            isValid = DFS();
        }
    }

    /**
     * Runs a DFS algorithm on the grid created by the buildMaze method
     * and checks that the maze is solvable
     * @return true if the maze is solvable; false otherwise.
     */
    private boolean DFS() {
        return true;
    }
}
