package main;

import javax.swing.*;

public class newMain {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // window can close properly
        window.setResizable(false);
        window.setTitle("2D Adventure");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null); // window displayed center of screen
        window.setVisible(true); // see window

        gamePanel.startGameThread();
    }
}
