/**
 * The Tile class creates many tiles to implement into the Grid of the game
 * @author Tanishq Guin
 * Due Date: 5/10/24
 * Period: 3
 * Teacher: Bailey
 * Collaborators: Charles Doan, Murtaza Khan
 */

import java.awt.Color;

public abstract class Tile
{
	//instance variables
	private int xCoor;
	private int yCoor;
	private int dimension;
	private Color color;
	
	//constants 
	public static final Color PATH = Color.WHITE;
	public static final Color WALL = Color.BLACK;
	public static final Color START = Color.GREEN;
     	public static final Color END = Color.RED;
	public static final int EASY_DIMENSION = 30;
	public static final int HARD_DIMENSION = 75;
	
	/** Creates a Tile to use 
	 *  @param x the x coordinate of the tile
	 * 	@param y the y coordinate of the tile
	 *  @param d the dimension of the tile
	 *  @param col the color of the tile
	 */
	public Tile(int x, int y, int d, Color col)
	{
		xCoor = x;
		yCoor = y;
		dimension = d;
		color = col;
	}
	
	/** Gets the color of the tile
	 *  @return the color of the tile
	 */
	public Color getColor()
	{
		return color;
	}
	
	/** Changes the color of the tile 
	 *  @param col the new color of the tile
	 */
	public void setColor(Color col)
	{
		color = col;
	}
	
	/** Gets the dimension of the tile
	 *  @return the dimension of the tile
	 */
	public int getDimension()
	{
		return dimension;
	}
	
	/** Gets the x coordinate of the tile
	 *  @return the x coordinate of the tile
	 */
	public int getX()
	{
		return xCoor;
	}
	
	/** Gets the y coordinate of the tile
	 *  @return the y coordinate of the tile
	 */
	public int getY()
	{
		return yCoor;
	}

	/** Gets the bounding shape of the Tile, 
 	 * be it a TileEasy, TileHard, or Player.
	 *  @return the bounding shape of the Tile.
	 */	
	public abstract Shape getShape();
}
