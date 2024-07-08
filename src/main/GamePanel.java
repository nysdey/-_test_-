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

    // FPS
    int FPS = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread; // keeps program running until it's stopped

    //set player's default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    public GamePanel() { //constructor

        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // this refers to current object
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); // improves game rendering performance
        this.addKeyListener(keyH);
        this.setFocusable(true); // GamePanel can be "focused" to receive key input
    }

    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start(); // calls run method
    }

    @Override
    public void run() { // automatically called by Runnable
        // TODO Auto-generated method stub, but not actually

        double drawInterval = 1000000000/FPS; // in nanoseconds = 1 sec; 0.01666 seconds
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(gameThread != null) {

           /*long currentTime = System.nanoTime(); // returns the current value of the running Java Virtual Machine's high-res time source
            System.out.println("Current Time:" + currentTime);*/
            // 1 UPDATE: update information such as character position
            update();
            // 2 DRAW: draw the screen with the updated information
            repaint(); // calls paint method

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;

                if(remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime); // causes the currently executing thread to sleep, done in mili
                nextDrawTime += drawInterval;
            }

            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    /* in Java, the upper left corner is X:0 Y:0
    x value increases to the right
    y value increases downwards
     */

    public void update() {

        if(keyH.upPressed == true) {
            playerY -= playerSpeed;
        }

        else if(keyH.downPressed ==true) {
            playerY += playerSpeed;
        }

        else if(keyH.leftPressed == true) {
            playerX -= playerSpeed;
        }

        else if(keyH.rightPressed == true) {
            playerX += playerSpeed;
        }

    }
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g; // converts to graphic's subclass
        g2.setColor(Color.white);
        g2.fillRect(playerX, playerY, tileSize, tileSize); // changed from fixed number to variable, player position
        g2.dispose(); // works without but good to save memory
    }
}
