package states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import frameworks.State;
import main.GamePanel;

public class MenuState implements State {

    private GamePanel gp;
    private int commandNum = 1;

    public MenuState(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void draw(Graphics g) {

        g.setFont(g.getFont().deriveFont(Font.BOLD, 96F));
        String text = "WE FIGHT";
        int x = gp.getXforCenteredText(text, g);
        int y = 200;

        g.setColor(Color.black);
        g.drawString(text, x + 5, y + 5);

        g.setColor(Color.white);
        g.drawString(text, x, y);

        g.setFont(g.getFont().deriveFont(Font.BOLD, 40F));

        text = "START GAME";
        x = gp.getXforCenteredText(text, g);
        y += 200;
        g.setColor(Color.black);
        g.drawString(text, x + 2, y + 2);
        g.setColor(Color.white);
        g.drawString(text, x, y);
        if (commandNum == 1) {
            g.setColor(Color.black);
            g.drawString(">", x - 40 + 2, y + 2);
            g.setColor(Color.white);
            g.drawString(">", x - 40, y);
        }

        text = "QUIT";
        x = gp.getXforCenteredText(text, g);
        y += 50;
        g.setColor(Color.black);
        g.drawString(text, x + 2, y + 2);
        g.setColor(Color.white);
        g.drawString(text, x, y);
        if (commandNum == 2) {
            g.setColor(Color.black);
            g.drawString(">", x - 40 + 2, y + 2);
            g.setColor(Color.white);
            g.drawString(">", x - 40, y);
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        if (code == KeyEvent.VK_UP) {
            if (commandNum == 2) {
                commandNum = 1;
            }
        }

        if (code == KeyEvent.VK_DOWN) {
            if (commandNum == 1) {
                commandNum = 2;
            }
        }

        if (code == KeyEvent.VK_ENTER) {
            if (commandNum == 1) {
                gp.gameState = gp.PLAYING;
            }

            if (commandNum == 2) {
                System.exit(0);
            }
        }

    }

    @Override
    public void update() {

    }

}
