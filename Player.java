/**
 * The Player class maintains and updates the position of the player sprite
 * @author Charles Doan
 * Due Date: 5/10/24
 * Period: 6
 * Teacher: Bailey
 * Collaborators: N/A
 */
import java.awt.geom.*;
public class Player extends Tile {
    /**
     * Constructs a Player object using the x and y coordinates as well
     * as the dimension of the bounding rectangle. The dimension is the
     * length and width, since a Tile is a square.
     * @param x the x coordinate
     * @param y the y coordinate
     * @param d the side length of the bounding rectangle of the square
     */
    public Player(int x, int y, int d) {
        super(x, y, d, Tile.PLAYER_COLOR);
    }

    /**
     * Alters the position of the player internally; the 
     * game screen still must be updated in order to reflect
     * the changes.
     * @param dx the change in the x coordiante
     * @param dy the change in the y coordinate.
     */
    public void move(int dx, int dy) {
        xCoor += dx;
        yCoor += dy;
    }

    /**
     * @return an Ellipse2D.Double that represents the player.
     */
    @Override public Ellipse2D.Double getShape() {
        return new Ellipse2D.Double(xCoor, yCoor, dimension, dimension);
    }
}
