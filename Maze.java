import javax.swing.*;
import java.awt.*;
public class Maze extends JComponent {
    public static final double CHANCE_OF_WALL = .4;
    public static final int CHANCE_OF_PATH = 60;
    private Tile[][] grid;
    private int tileDimensions;

    public Maze(Tile[][] grid, int dimension) {
        this.grid = grid;
        tileDimensions = GameTester.DIMENSION / dimension;
    }

    /**
     * Paints every Tile as listed in the 2D array of Tiles
     * @param g
     */
    public void paint(Graphics gs) {
        Graphics2D g = (Graphics2D) gs;
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                g.setColor(Color.BLACK);
                g.draw(grid[row][col].getShape());
                g.setColor(grid[row][col].getColor());
                g.fill(grid[row][col].getShape());
            }
        }
    }

    /**
     * Creates a maze by randomly populating the 2D array and then checking
     * maze to ensure it is solvable through a DFS algorithm.
     * @param gridDimension the dimension of the grid, used to determine the   
     *                      difficulty of the maze.
     */
    public void buildMaze() {
        boolean isValid = false;
        //General grid 
       // while (!isValid) {
            for (int row = 0; row < grid.length; row++) {
                for (int col = 0; col < grid[0].length; col++) {
                    double chance = Math.random();
                    if (chance < CHANCE_OF_WALL) {
                        if (grid.length == Tile.EASY_LENGTH) {
                            grid[row][col] = new TileEasy(
                                                col * Tile.EASY_DIMENSION,
                                                row * Tile.EASY_DIMENSION,
                                                Tile.EASY_DIMENSION,
                                                Tile.WALL);
                        } else {
                            grid[row][col] = new TileHard(
                                                col * Tile.HARD_DIMENSION,
                                                row * Tile.HARD_DIMENSION,
                                                Tile.HARD_DIMENSION,
                                                Tile.WALL);
                        }
                    } else {
                        if (grid.length == Tile.EASY_LENGTH) {
                            grid[row][col] = new TileEasy(
                                                col * Tile.EASY_DIMENSION,
                                                row * Tile.EASY_DIMENSION,
                                                Tile.EASY_DIMENSION,
                                                Tile.PATH);
                        } else {
                            grid[row][col] = new TileHard(
                                                col * Tile.HARD_DIMENSION,
                                                row * Tile.HARD_DIMENSION,
                                                Tile.HARD_DIMENSION,
                                                Tile.PATH);
                        }
                    }
                }
            }
     	    boolean[][] visitedArray = new boolean[grid.length][grid[0].length];
           // isValid = DFS(visitedArray, grid.length - 1, 0);
       // }
        //Start and end points
      /*   if (grid.length == Tile.EASY_DIMENSION) {
            grid[grid.length - 1][0] = new TileEasy(0,
                                                grid.length - 1,
                                                tileDimensions,
                                                Tile.START);
            grid[0][grid.length - 1] = new TileEasy(grid.length - 1,
                                                0,
                                                tileDimensions,
                                                Tile.END);
        } else {
            grid[grid.length - 1][0] = new TileHard(0,
                                                grid.length - 1,
                                                tileDimensions,
                                                Tile.START);
            grid[0][grid.length - 1] = new TileHard(grid.length - 1,
                                                0,
                                                tileDimensions,
                                                Tile.END);
        }*/
    }


    /** Runs DFS algorithm to check if maze is solvable
     * @param visited is 2d array of grid to mark visited tile
     * @param row is the row of the tile
     * @param col is the column of the tile
     * @return whether or not the maze is solvable
     */
    private boolean DFS(boolean[][] visited, int row, int col) {
        if (grid[row][col].getColor().equals(Tile.END) ) {
            return true;
        }

        visited[row][col] = true;

        int[][] moves = {
            {-1, 0}, {1, 0}, {0, -1}, {0, 1},   
            {-1, -1}, {-1, 1}, {1, -1}, {1, 1}  
        };

        for (int[] move : moves) {
            int newRow = row + move[0];
            int newCol = col + move[1];
    
            if (isValidMove(visited, newRow, newCol)) {
                return DFS(visited, newRow, newCol);
            }
        }

        return false;
    }

    /** Checks if the Tile is a valid place to visit
     * @param visited is 2D array of grid to mark visited tile
     * @param row is the row of the tile
     * @param col is the column of the tile
     * @return whether or not the Tile is available
     */
    private boolean isValidMove(boolean[][] visited, int row, int col) 
    { 
        int rows = grid.length; 
        int cols = grid[0].length; 
        return (row >= 0 && row < rows && col >= 0 && col < cols
        && !(grid[row][col].getColor().equals(Tile.WALL)) && !visited[row][col]); 
    }
}
