package states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import frameworks.State;
import main.GamePanel;


public class PlayingState implements State {

    private GamePanel gp;

    public PlayingState(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void draw(Graphics g) {

        gp.setBackground(Color.white);

        gp.redBase.draw(g);
        gp.greenBase.draw(g);

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
        gp.redBase.update(gp.greenBase.frontX, gp.greenBase.soldierFront);
        gp.greenBase.update(gp.redBase.frontX, gp.redBase.soldierFront);
    }

}
