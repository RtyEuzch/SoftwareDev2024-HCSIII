import javax.swing.*;
import java.awt.*;
public class Maze extends JComponent {
    public static final double CHANCE_OF_WALL = .5;
    public static final int CHANCE_OF_PATH = 60;
    private Tile[][] grid;
    private int tileDimensions;
    private Player player;

    public Maze(Tile[][] grid, int dimension) {
        this.grid = grid;
        if (grid.length == Tile.EASY_LENGTH) {
            player = new Player(0,
            Tile.EASY_DIMENSION * grid.length - 1, dimension);
        } else {
            player = new Player(0, 
            Tile.HARD_DIMENSION * grid.length - 1, dimension);
        }
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
        g.setColor(Color.BLACK);
        g.draw(player.getShape());
        g.setColor(player.getColor());
        g.fill(player.getShape());
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
        while (!isValid) {
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
            //Start and end points
            if (grid.length == Tile.EASY_LENGTH) {
                grid[grid.length - 1][0] = new TileEasy(0,
                                        (grid.length - 1) * Tile.EASY_DIMENSION,
                                        tileDimensions,
                                        Tile.START);
                grid[0][grid.length - 1] = new TileEasy((grid.length - 1)
                                        * Tile.EASY_DIMENSION,
                                        0,
                                        tileDimensions,
                                        Tile.END); 
            } else {
                grid[grid.length - 1][0] = new TileHard(0,
                                        (grid.length - 1) * Tile.HARD_DIMENSION,
                                        tileDimensions,
                                        Tile.START);
                grid[0][grid.length - 1] = new TileHard((grid.length - 1)
                                        * Tile.HARD_DIMENSION,
                                        0,
                                        tileDimensions,
                                        Tile.END);
            } 
            boolean[][] visitedGrid = new boolean[grid.length][grid.length];
            isValid = DFS(visitedGrid, grid.length - 1, 0);
        }
    }


    /** Runs DFS algorithm to check if maze is solvable
     * @param visited is 2d array of grid to mark visited tile
     * @param row is the row of the tile
     * @param col is the column of the tile
     * @return whether or not the maze is solvable
     */
    private boolean DFS(boolean[][] visited, int row, int col) {
        if (grid[row][col].getColor().equals(Tile.END) ) 
            return true;

        visited[row][col] = true;

        int[][] moves = {
            {-1, 0}, {1, 0}, {0, -1}, {0, 1},   
            {-1, -1}, {-1, 1}, {1, -1}, {1, 1}  
        };

        for (int[] move : moves) {
            int newRow = row + move[0];
            int newCol = col + move[1];
            if (isValidMove(visited, newRow, newCol)) 
                if (DFS(visited, newRow, newCol)) return true;  
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
        return (row >= 0 && row < grid.length && col >= 0 
                         && col < grid.length
                         && !(grid[row][col].getColor().equals(Tile.WALL))
                         && !visited[row][col]); //
    }

    /**
     * Moves the Player, updates their position in the actual grid, and 
     * then marks the color of the square the Player was last on with the 
     * color that matches the difficulty.
     */

    public void movePlayer(int dx, int dy) {
        if (grid.length == Tile.EASY_LENGTH) {
            int playerRow = player.getY() / Tile.EASY_DIMENSION;
            int playerCol = player.getX() / Tile.EASY_DIMENSION;
            if ((playerRow + dy >= grid.length)
                || (playerCol + dx < 0)) return;
            grid[playerRow][playerCol].setColor(TileEasy.VISITED);
            player.move(Tile.EASY_DIMENSION * dx, Tile.EASY_DIMENSION * dy);
        } else {
            int playerRow = player.getY() / Tile.HARD_DIMENSION;
            int playerCol = player.getX() / Tile.HARD_DIMENSION;
            if ((playerRow + dy >= grid.length)
                || (playerCol + dx < 0)) return;
            grid[playerRow][playerCol].setColor(TileHard.VISITED);
            player.move(Tile.EASY_DIMENSION * dx, Tile.HARD_DIMENSION * dy);
        }
        repaint();
    }
}
