package main;

import javax.swing.JFrame;

public class Game extends JFrame {
    private GamePanel gamePanel = new GamePanel();
    private KeyHandler keyHandler = new KeyHandler(gamePanel);

    public Game() {
        add(gamePanel);
        addKeyListener(keyHandler);
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        gamePanel.startGameThread();
    }
}
