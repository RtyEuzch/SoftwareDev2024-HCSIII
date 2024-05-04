import java.awt.geom.*;
public class Player extends Tile {
    public Player(int x, int y, int d) {
        super(x, y, d, Tile.PLAYER_COLOR);
    }

    public void move(int dx, int dy) {
        xCoor += dx;
        yCoor += dy;
    }

    @Override public Ellipse2D.Double getShape() {
        return new Ellipse2D.Double(xCoor, yCoor, dimension, dimension);
    }
}
