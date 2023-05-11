package main;

import java.awt.*;
import javax.swing.JPanel;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Image;

import frameworks.State;
import objects.bases.BlueBase;
import objects.bases.RedBase;
import states.EndState;
import states.MenuState;
import states.PlayingState;
import states.PausedState;

public class GamePanel extends JPanel {
    public int WIDTH = 1400;
    public int HEIGHT = 700;
    public static int FPS = 60; // can be 0 - 60

    private Image backGround;

    public State gameState;
    public MenuState MENU = new MenuState(this);
    public PlayingState PLAYING = new PlayingState(this);
    public PausedState PAUSED = new PausedState(this);
    public EndState END = new EndState(this);

    public BlueBase blueBase = new BlueBase(920, 200);
    public RedBase redBase = new RedBase(0, 200);

    Thread gameThread;

    public GamePanel() {

        this.gameState = MENU;

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);

        // if (FPS > 60 || FPS < 0) {
        //     throw new IllegalArgumentException("Value of FPS can be in range of 0 - 60 FPS");
        // }

        try {
            this.backGround = ImageIO.read(new File("images/background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void startGameThread() {
        // Update program FPS time in second
        gameThread = new Thread() {
            public void run() {

                while (gameThread != null) {
                    update();
                    repaint();
                    try {
                        Thread.sleep(1000 / FPS);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        gameThread.start();
    }

    public void update() {
        gameState.update();

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backGround, 0, 0, WIDTH, HEIGHT, null);

        gameState.draw(g);
    }

    public int getXforCenteredText(String text, Graphics g) {
        int length = (int) g.getFontMetrics().getStringBounds(text, g).getWidth();
        int x = WIDTH / 2 - length / 2;
        return x;
    }
}
