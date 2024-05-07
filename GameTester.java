/**
 * The GameTester class creates the JFrames necessary to run the program.
 * The first JFrame is the main menu, which allows the player to choose
 * between the easy and hard modes. The second JFrame is the actual game, 
 * where the GameTester class is responsible for adding the listeners 
 * necessary to allow the user to control the player using the keyboard
 * keys that surround the 'k' key.
 * @author Charles Doan
 * Due Date: 5/10/24
 * Period: 6
 * Teacher: Bailey
 * Collaborators: N/A
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D.Double;
public class GameTester {
    public static final int DIMENSION = 900;
    public static final int BUTTON_DIMENSION = 400;
    public static final int TITLE_BORDER_DISTANCEX = DIMENSION / 500 * 20;
    public static final int IMAGE_BORDERX = TITLE_BORDER_DISTANCEX * 4;
    public static final int LINE_BORDERX = IMAGE_BORDERX * 4;
    public static final int LINE_LENGTH = 250;
    public static final int ARROW_WING = 50;
    public static final int DIAMETER = 150;
    public static final int FONT_SIZE = 150;
    public static final int TITLE_BORDER_DISTANCEY = 170;
    public static final Color LIGHT_BLUE = new Color(135, 206, 235);

    /**
     * The main method that executes the procedural code and the 
     * game.
     * @param args the command-line arguments (unused)
     */
    public static void main(String[] args) {
        //Create the JFrame and set its settings
        JFrame introFrame = new JFrame("Intro Screen");
        introFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        introFrame.setSize(DIMENSION, DIMENSION);
        introFrame.setResizable(false);

        //Add JComponent
        IntroScreen introComponent = new IntroScreen();
        introFrame.add(introComponent);

        //JPanel with buttons
        JPanel buttonPanel = new JPanel();
        JButton easyButton = new JButton("Easy");
        JButton hardButton = new JButton("Hard");

        //Configure the Buttons
        easyButton.setPreferredSize(
            new Dimension(BUTTON_DIMENSION, BUTTON_DIMENSION / 2));
        easyButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent event) {
                mainScreen(Tile.EASY_LENGTH);
            }
        });
        hardButton.setPreferredSize(
            new Dimension(BUTTON_DIMENSION, BUTTON_DIMENSION / 2));
        hardButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent event) {
                mainScreen(Tile.HARD_LENGTH);
            }
        });
        buttonPanel.add(easyButton);
        buttonPanel.add(hardButton);
        introFrame.add(buttonPanel, BorderLayout.SOUTH);
        introFrame.setSize(DIMENSION, DIMENSION);
        introFrame.setVisible(true);
    }

    /**
     * Creates the JFrame that holds the game itself, grid, and player.
     * It adds the KeyAdapter necessary to allow the user to control
     * the player, registering different motions depending on what
     * key the player presses.
     * @param dimension the dimension of the grid being created
     *                  (e.g. 15 x 15 for easy mode).
     */
    public static void mainScreen(int dimension) {
        JFrame mainFrame = new JFrame("Find the Way");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(DIMENSION, DIMENSION);
        mainFrame.setResizable(false);
        Maze maze = new Maze(new Tile[dimension][dimension],
                                      dimension, mainFrame);
        maze.buildMaze();
        mainFrame.add(maze);
        mainFrame.addKeyListener(new KeyAdapter() {
            @Override public void keyPressed(KeyEvent ev) {
                switch (ev.getKeyChar()) {
                    case 'i': maze.movePlayer(0, -1); break;
                    case 'o': maze.movePlayer(1, -1); break;
                    case 'l': maze.movePlayer(1, 0); break;
                    case '.': maze.movePlayer(1, 1); break;
                    case ',': maze.movePlayer(0, 1); break;
                    case 'm': maze.movePlayer(-1, 1); break;
                    case 'j': maze.movePlayer(-1, 0); break;
                    case 'u': maze.movePlayer(-1, -1); break;
                }
            }
        });
        mainFrame.setVisible(true);
        mainFrame.setSize(DIMENSION, DIMENSION + mainFrame.getInsets().top);
        mainFrame.setVisible(true);
    }
}

/**
 * The IntroScreen class creates the JFrame for the main menu.
 * It uses Graphics to draw the images and display text.
 * @author Charles Doan
 * Due Date: 5/10/24
 * Period: 6
 * Teacher: Bailey
 * Collaborators: N/A
 */
class IntroScreen extends JComponent {
    /**
     * Draws the display image of the main menu.
     * @param g the Graphics object used to draw/fill the shapes.
     */
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setFont(new Font("Times New Roman",
                            Font.ITALIC, GameTester.FONT_SIZE));
        g2.drawString("Find the Way!",
                      GameTester.TITLE_BORDER_DISTANCEX,
                      GameTester.TITLE_BORDER_DISTANCEY);
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(5));
        g2.drawOval(GameTester.IMAGE_BORDERX,
                    GameTester.TITLE_BORDER_DISTANCEY * 2, 
                    GameTester.DIAMETER, 
                    GameTester.DIAMETER);
        paintArrow(g2);
        //Drawing the End square
        g2.setColor(Tile.END);
        g2.fillRect(GameTester.LINE_BORDERX + GameTester.LINE_LENGTH + 
                    (GameTester.LINE_BORDERX
                        - GameTester.DIAMETER - GameTester.IMAGE_BORDERX),
                    GameTester.TITLE_BORDER_DISTANCEY * 2,
                    GameTester.DIAMETER, GameTester.DIAMETER);
    }

    /**
     * A helper method that draws the light blue arrow in the display
     * @param g2 the Graphics2D object that serves as a "paintbrush" to
     *              draw the arrow with.
     */
    private void paintArrow(Graphics2D g2) {
        //Drawing the blue arrow
        //Draw the "main line" of the arrow
        g2.setColor(GameTester.LIGHT_BLUE);
        g2.drawLine(GameTester.LINE_BORDERX,
                    GameTester.TITLE_BORDER_DISTANCEY * 2 
                                + GameTester.DIAMETER / 2, 
                    GameTester.LINE_BORDERX + GameTester.LINE_LENGTH,
                    GameTester.TITLE_BORDER_DISTANCEY * 2 
                                + GameTester.DIAMETER / 2);
        //Draw the "wings" of the arrow
        g2.drawLine(GameTester.LINE_BORDERX + GameTester.LINE_LENGTH,
                    GameTester.TITLE_BORDER_DISTANCEY * 2 
                                + GameTester.DIAMETER / 2,
                    GameTester.LINE_BORDERX + GameTester.LINE_LENGTH
                                            - GameTester.ARROW_WING,
                    GameTester.TITLE_BORDER_DISTANCEY * 2 
                                + GameTester.DIAMETER / 2
                                - GameTester.ARROW_WING);
        g2.drawLine(GameTester.LINE_BORDERX + GameTester.LINE_LENGTH,
                    GameTester.TITLE_BORDER_DISTANCEY * 2 
                                + GameTester.DIAMETER / 2,
                    GameTester.LINE_BORDERX + GameTester.LINE_LENGTH
                                            - GameTester.ARROW_WING,
                    GameTester.TITLE_BORDER_DISTANCEY * 2 
                                + GameTester.DIAMETER / 2
                                + GameTester.ARROW_WING);
    }
}
