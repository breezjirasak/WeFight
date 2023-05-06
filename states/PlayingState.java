package states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.Font;

import frameworks.Soldier;
import frameworks.State;
import main.GamePanel;
import objects.bases.RedBase;

public class PlayingState implements State{

    private GamePanel gp;
    

    public PlayingState(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void draw(Graphics g) {
        // gp.setBackground(Color.pink);

        // g.setFont(g.getFont().deriveFont(Font.BOLD, 96F));
        // String text = "PLAYING";
        // int x = gp.getXforCenteredText(text, g);
        // int y = gp.HEIGHT / 2;

        // g.setColor(Color.black);
        // g.drawString(text, x + 5, y + 5);

        // g.setColor(Color.white);
        // g.drawString(text, x, y);
        for (Soldier soldier:gp.redBase.soldiers) {
            soldier.draw(g);
        }

        for (Soldier soldier:gp.greenBase.soldiers) {
            soldier.draw(g);
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        if (code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.PAUSED;
        }

        if (code == KeyEvent.VK_X) {
            gp.redBase.createSwordSoldier(200, 450);
        }

        if (code == KeyEvent.VK_I) {
            gp.greenBase.createSwordSoldier(900, 450);
        }

    }

    @Override
    public void update() {
        for (Soldier soldier:gp.redBase.soldiers) {
            soldier.update();
        }

        for (Soldier soldier:gp.greenBase.soldiers) {
            soldier.update();
        }
    }
    
}
