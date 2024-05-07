/**
 * The Tile class holds the general functionality of each tile
 * to implement into the Grid of the game
 * @author Tanishq Guin
 * Due Date: 5/10/24
 * Period: 3
 * Teacher: Bailey
 * Collaborators: Charles Doan, Murtaza Khan
 */

 import java.awt.*;
 public abstract class Tile
 {
     //instance variables
     protected int xCoor;
     protected int yCoor;
     protected int dimension;
     protected Color color;
     
     //constants 
     public static final Color PATH = Color.WHITE;
     public static final Color WALL = Color.BLACK;
     public static final Color START = Color.GREEN;
     public static final Color END = Color.RED;
     public static final Color PLAYER_COLOR = Color.BLUE;
     public static final int EASY_LENGTH = 15;
     public static final int HARD_LENGTH = 30;
     public static final int EASY_DIMENSION =
                    GameTester.DIMENSION / EASY_LENGTH;
     public static final int HARD_DIMENSION = 
                    GameTester.DIMENSION / HARD_LENGTH;
     
     /** Creates a Tile to use 
      *  @param x the x coordinate of the tile
      *  @param y the y coordinate of the tile
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
     
     /** 
      *  @return the dimension of the tile
      */
     public int getDimension()
     {
         return dimension;
     }
     
     /** 
      *  @return the x coordinate of the tile
      */
     public int getX()
     {
         return xCoor;
     }
     
     /** 
      *  @return the y coordinate of the tile
      */
     public int getY()
     {
         return yCoor;
     }

     /** Sets the x coordinate of the tile
      *  @return the x coordinate of the tile
      */
      public void setX(int x)
      {
          xCoor = x;
      }
      
      /** Sets the y coordinate of the tile
       *  @return the y coordinate of the tile
       */
      public void setY(int y)
      {
          yCoor = y;
      }
  
 
     /** Gets the bounding shape of the Tile, 
       * be it a TileEasy, TileHard, or Player.
      *  @return the bounding shape of the Tile.
      */	
     public abstract Shape getShape();
 }
