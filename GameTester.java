import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D.Double;
public class GameTester {
    public static final int DIMENSION = 900;
    public static final int BUTTON_DIMENSION = 400;
    public static final int TITLE_BORDER_DISTANCEX = DIMENSION / 500 * 20;
    public static final int FONT_SIZE = 150;
    public static final int TITLE_BORDER_DISTANCEY = 170;
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
        easyButton.setPreferredSize(new Dimension(BUTTON_DIMENSION, BUTTON_DIMENSION / 2));
        easyButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent event) {
                System.out.println("Hi");
            }
        });
        hardButton.setPreferredSize(new Dimension(BUTTON_DIMENSION, BUTTON_DIMENSION / 2));
        buttonPanel.add(easyButton);
        buttonPanel.add(hardButton);
        introFrame.add(buttonPanel, BorderLayout.SOUTH);
        introFrame.setVisible(true);
    }

    public static void mainScreen() {
        JFrame mainFrame = new JFrame("Find the Way");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(DIMENSION, DIMENSION);
        mainFrame.setResizable(false);
        mainFrame.setVisible(true);
    }
}

class IntroScreen extends JComponent {
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setFont(new Font("Times New Roman", Font.ITALIC, GameTester.FONT_SIZE));
        g2.drawString("Find the Way!",
                      GameTester.TITLE_BORDER_DISTANCEX,
                      GameTester.TITLE_BORDER_DISTANCEY);

        
    }
}
