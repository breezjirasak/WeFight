package main;

import java.awt.*;
import javax.swing.JPanel;

import frameworks.State;
import objects.bases.GreenBase;
import objects.bases.RedBase;
import states.EndState;
import states.MenuState;
import states.PlayingState;
import states.PausedState;

public class GamePanel extends JPanel {
    public int WIDTH = 1200;
    public int HEIGHT = 600;
    private int FPS = 60;

    public State gameState;
    public MenuState MENU = new MenuState(this);
    public PlayingState PLAYING = new PlayingState(this);
    public PausedState PAUSED = new PausedState(this);
    public EndState END = new EndState(this);

    public GreenBase greenBase = new GreenBase();
    public RedBase redBase = new RedBase();

    Thread gameThread;

    public GamePanel() {

        this.gameState = MENU;

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
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
        gameState.draw(g);
    }

    public int getXforCenteredText(String text, Graphics g) {
        int length = (int) g.getFontMetrics().getStringBounds(text, g).getWidth();
        int x = WIDTH / 2 - length / 2;
        return x;
    }
}
