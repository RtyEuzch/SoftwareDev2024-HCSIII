import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
public class GameTester {
    public static final int DIMENSION = 500;
    public static void main(String[] args) {
        JFrame introFrame = new JFrame("Intro Screen");
        introFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        introFrame.setSize(DIMENSION, DIMENSION);
        introFrame.setVisible(true);
        introFrame.setResizable(false);
        IntroScreen introComponent = new IntroScreen();
        introFrame.add(introComponent);
    }
}

class IntroScreen extends JComponent {
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.GREEN);
        Rectangle2D.Double rec = new Rectangle2D.Double(0, 0, 39, 39);
        g2.fill(rec);
    }
}
