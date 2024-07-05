package main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    // screen settings
    final int originalTileSize = 16; // 16x16 tile, default size of characters, looks very small on modern screens
    final int scale = 3;

    final int tileSize = originalTileSize * scale; // 48, actual tile size
    final int maxScreenColumn = 16;
    final int maxScreenRow = 12; // ratio is 4 x 3
    final int screenWidth = tileSize * maxScreenColumn; // 48 * 16 = 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // 48 & 12 = 576 pixels

    Thread gameThread; // keeps program running until it's stopped

    public GamePanel() { //constructor

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); // improves game rendering performance
    }

    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start(); // calls run method
    }

    @Override
    public void run() { // automatically called by Runnable
        // TODO Auto-generated method stub, but not actually

        while(gameThread != null) {

            //System.out.println("The game loop is running");

            // 1 UPDATE: update information such as character position
            update();
            // 2 DRAW: draw the screen with the updated information
            repaint(); // calls paint method
        }
    }
    public void update() {

    }
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g; // converts to graphic's subclass
        g2.setColor(Color.white);
        g2.fillRect(100, 100, tileSize, tileSize);
        g2.dispose();
    }
}
