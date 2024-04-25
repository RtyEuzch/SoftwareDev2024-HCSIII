/**
 * The Tile class creates many tiles to implement into the Grid of the game
 * @author Tanishq Guin
 * Due Date: 5/10/24
 * Period: 3
 * Teacher: Bailey
 * Collaborators: Charles Doan, Murtaza Khan
 */

import java.awt.Color;

public class Tile
{
	//instance variables
	private int dimension;
	private Color color;
	
	/** Creates a Tile to use 
	 *  @param d the dimension of the tile
	 *  @param col the color of the tile
	 */
	public Tile(int d, Color col)
	{
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
}
