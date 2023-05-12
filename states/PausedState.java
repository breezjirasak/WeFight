package states;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Font;

import frameworks.State;
import main.GamePanel;
import objects.bases.BlueBase;
import objects.bases.RedBase;
import frameworks.Soldier;

public class PausedState implements State {

    private GamePanel gp;
    private int commandNum = 1;

    public PausedState(GamePanel gp) {
        this.gp = gp;
    }

    public void resetGame() {
        RedBase.HP = gp.redBase.maxHp;
        gp.redBase.money = 0;
        gp.redBase.level = 1;
        gp.redBase.soldiersSword = new ArrayList<Soldier>();
        gp.redBase.soldiersGun = new ArrayList<Soldier>();
        gp.redBase.ultimate = true;

        BlueBase.HP = gp.redBase.maxHp;
        gp.blueBase.money = 0;
        gp.blueBase.level = 1;
        gp.blueBase.soldiersSword = new ArrayList<Soldier>();
        gp.blueBase.soldiersGun = new ArrayList<Soldier>();
        gp.blueBase.ultimate = true;

    }

    @Override
    public void draw(Graphics g) {
        gp.PLAYING.draw(g);

        // gp.setBackground(Color.pink);

        g.setFont(g.getFont().deriveFont(Font.BOLD, 96F));
        String text = "PAUSED";
        int x = gp.getXforCenteredText(text, g);
        int y = gp.HEIGHT / 3;

        g.setColor(Color.black);
        g.drawString(text, x + 5, y + 5);

        g.setColor(Color.white);
        g.drawString(text, x, y);

        g.setFont(g.getFont().deriveFont(Font.BOLD, 40F));

        text = "RESUME";
        x = gp.getXforCenteredText(text, g);
        y += 100;
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

        text = "RESTART";
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

        text = "MENU";
        x = gp.getXforCenteredText(text, g);
        y += 50;
        g.setColor(Color.black);
        g.drawString(text, x + 2, y + 2);
        g.setColor(Color.white);
        g.drawString(text, x, y);
        if (commandNum == 3) {
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
        if (commandNum == 4) {
            g.setColor(Color.black);
            g.drawString(">", x - 40 + 2, y + 2);
            g.setColor(Color.white);
            g.drawString(">", x - 40, y);
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.PLAYING;
        }

        if (code == KeyEvent.VK_UP) {
            if (commandNum == 2) {
                commandNum = 1;
            }

            else if (commandNum == 3) {
                commandNum = 2;
            }

            else if (commandNum == 4) {
                commandNum = 3;
            }
        }

        if (code == KeyEvent.VK_DOWN) {
            if (commandNum == 1) {
                commandNum = 2;
            }

            else if (commandNum == 2) {
                commandNum = 3;
            }

            else if (commandNum == 3) {
                commandNum = 4;
            }
        }

        if (code == KeyEvent.VK_ENTER) {
            if (commandNum == 1) {
                gp.gameState = gp.PLAYING;
            }

            if (commandNum == 2) {
                resetGame();
                gp.gameState = gp.PLAYING;
            }

            else if (commandNum == 3) {
                resetGame();
                gp.gameState = gp.MENU;
            }

            else if (commandNum == 4) {
                System.exit(0);
            }
        }

    }

    @Override
    public void update() {

    }

}
