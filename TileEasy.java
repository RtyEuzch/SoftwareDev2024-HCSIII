/**
 * The TileEasy class creates the tiles specifically made for easy mode 
 * @author Tanishq Guin
 * Due Date: 5/10/24
 * Period: 3
 * Teacher: Bailey
 * Collaborators: Charles Doan, Murtaza Khan
 */

 import java.awt.*;
 import java.awt.geom.*;
 
 public class TileEasy extends Tile
 {
     //instance variables
     private Rectangle2D.Double rect;
     
     //constants
     public static final Color VISITED = Color.YELLOW; 
     
     /** Creates the Easy Tile
      *  @param x the x coordinate of the tile
      *  @param y the y coordinate of the tile
      *  @param d the dimensions of the tile
      */
     public TileEasy(int x, int y, int d, Color col)
     {
         super(x, y, d, col);
     }
     
    /** @return the bounding Shape of the Tile
	 */
	@Override public Rectangle2D.Double getShape() {
		return new Rectangle2D.Double(xCoor, yCoor, dimension, dimension);
	}
 }
