/**
 * The Maze class creates the 2D array necessary to hold the information of
 * the tiles in the grid. It also holds the JComponent necessary to hold the 
 * grid image itself. After storing the Tiles, it uses a method to display
 * each element in the 2D array as a graphic. It also builds a maze through 
 * randomly coloring each square either black (wall) or leaving it blank
 * and uses a DFS algorithm to check if the maze is solvable, repeating the 
 * maze creation process until the maze is solvable.
 * @author Charles Doan
 * Due Date: 5/10/24
 * Period: 6
 * Teacher: Bailey
 * Collaborators: Murtaza Khan, Tanishq Guin
 */
import javax.swing.*;
import java.awt.*;
public class Maze extends JComponent {
    public static final double CHANCE_OF_WALL = .5;
    public static final int CHANCE_OF_PATH = 60;
    public static final int EASY_SCALAR = 4;
    private Tile[][] grid;
    private int tileDimensions;
    private Player player;
    private JFrame thisFrame;

    /**
     * Initializes the 2D array, dimension of the tiles, and reference to 
     * the JFrame to which the Maze component is added (in order to close
     * the JFrame from inside the class).
     * @param grid the 2D array that stores all the Tiles and their 
     *              row and column number.
     * @param dimension the dimension of the grid, not the JFrame that 
     *                  bounds it (e.g. 15 is the dimension of a 
     *                  15 x 15 grid).
     * @param frame the reference to the JFrame to which the Maze is added.
     */
    public Maze(Tile[][] grid, int dimension, JFrame frame) {
        this.grid = grid;
        thisFrame = frame;
        if (dimension == Tile.EASY_LENGTH) {
            player = new Player(0,
            Tile.EASY_DIMENSION * (grid.length - 1), dimension * EASY_SCALAR);
            tileDimensions = Tile.EASY_DIMENSION;
        } else {
            player = new Player(0, 
            Tile.HARD_DIMENSION * (grid.length - 1), dimension);
            tileDimensions = Tile.HARD_DIMENSION;
        }
    }

    /**
     * Paints every Tile as listed in the 2D array of Tiles
     * @param gs the graphics "paintbrush" used to draw or fill
     *      the shapes it is called to draw.
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
        if (player.getX() == tileDimensions * (grid.length - 1)
            && player.getY() == 0) {
            victory();
        } 
        //Check if collided with walls or visited paths in hard mode
        if (tileDimensions == Tile.HARD_DIMENSION) {
            int playerRow = player.getY() / tileDimensions;
            int playerCol = player.getX() / tileDimensions;
            if (grid[playerRow][playerCol].getColor().equals(Tile.WALL)) {
                loss("You hit the wall!\nTake the L.");
            }
            if (grid[playerRow][playerCol].getColor()
                                          .equals(TileHard.VISITED)) {
                loss("No cheating!\nYou hit a visited square."); 
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
        while (!isValid) {
            for (int row = 0; row < grid.length; row++) {
                for (int col = 0; col < grid[0].length; col++) {
                    fillTile(row, col);
                }
            }
            //Start and end points
            fillStartAndEnd();

            //Checks if the maze is solvable
            boolean[][] visitedGrid = new boolean[grid.length][grid.length];
            isValid = DFS(visitedGrid, grid.length - 1, 0);
        }
    }

    /**
     * Fills in an individual Tile in the grid, given its row and column
     * in the 2D array
     * @param row the row of the Tile
     * @param col the column of the Tile
     */
    private void fillTile(int row, int col) {
        double chance = Math.random();
        if (chance < CHANCE_OF_WALL) {
            initializeTile(row, col, tileDimensions, Tile.WALL);
        } else {
            initializeTile(row, col, tileDimensions, Tile.PATH);
        }
    }

    /**
     * Helper method which nitializes the tile, based on the color,
     * position in the 2D array, and whether or not it is a Tile
     * in easy or hard mode.
     * @param row the row of the Tile
     * @param col the column of the Tile
     * @param dimension the side length of the Tile
     * @param color the color of the Tile
     */
    private void initializeTile(int row, int col, int dimension, Color color) {
        if (grid.length == Tile.EASY_LENGTH) {
            grid[row][col] = new TileEasy(col * dimension, row * dimension,
                                          dimension,
                                          color);            
        } else {
            grid[row][col] = new TileHard(col * dimension, row * dimension,
                                          dimension,
                                          color);   
        }
    }

    /**
     * Draws the starting and ending points of the maze at the
     * bottom left and top right corners, respectively.
     */
    private void fillStartAndEnd() {
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
                         && !visited[row][col]); 
    }

    /**
     * Moves the Player, updates their position in the actual grid, and 
     * then marks the color of the square the Player was last on with the 
     * color that matches the difficulty.
     * @param dx the change in the row position
     * @param dy the change in the column position
     *      Precondition: dx and dy will only be -1, 0, and +1
     */
    public void movePlayer(int dx, int dy) {
        int newX = player.getX() + tileDimensions * dx;
        int newY = player.getY() + tileDimensions * dy;
    
        int playerRow = player.getY() / tileDimensions;
        int playerCol = player.getX() / tileDimensions;
    
        int newPlayerRow = newY / tileDimensions;
        int newPlayerCol = newX / tileDimensions;
    
        // Check if the new position is within the grid bounds
        if (newPlayerRow >= 0 && newPlayerRow < grid.length
            && newPlayerCol >= 0 && newPlayerCol < grid[0].length) {
            // Check if the new position is a valid path (not a wall)
            if (grid.length == Tile.EASY_LENGTH) {
                if (grid[newPlayerRow][newPlayerCol].getColor() != Tile.WALL) {
                    grid[playerRow][playerCol].setColor(TileEasy.VISITED);
                    player.move(tileDimensions * dx, tileDimensions * dy);
                } 
                repaint();
            } else {
                grid[playerRow][playerCol].setColor(TileHard.VISITED);
                player.move(tileDimensions * dx, tileDimensions * dy);
                repaint();
            }
        }
    }

    /**
     * Indicates that the user has won the game and allows them to 
     * choose whether to quit the game or return to the main menu.
     *      Precondition: the Player has reached the red square at the
     *                      top-right corner.
     */
    private void victory() {
        String[] options = {"Quit", "Main Menu"};
        int choice = JOptionPane.showOptionDialog(
                    thisFrame,
                    "You won the game!\nWhat would you like to do?",
                    "Congratulations!",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
    
        if (choice == 0) 
            System.exit(0);
        else if (choice == 1) 
            thisFrame.dispose();
    }

    /**
     * Indicates that the user has lost the game and allows them to 
     * choose whether to quit the game or return to the main menu.
     *      Precondition: the Player has touched a visited Tile 
     *                      (colored magenta) or a wall (colored black).
     */
    private void loss(String message) {
        String[] options = {"Quit", "Main Menu"};
        int choice = JOptionPane.showOptionDialog(
                    thisFrame,
                    message,
                    "You lost!",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
    
        if (choice == 0) 
            System.exit(0);
        else if (choice == 1) 
            thisFrame.dispose();
    }
}
