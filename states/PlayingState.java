package states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.security.Key;
import java.awt.Image;

import frameworks.State;
import main.GamePanel;
import objects.bases.BlueBase;
import objects.bases.RedBase;


public class PlayingState implements State {

    private GamePanel gp;

    public PlayingState(GamePanel gp) {
        this.gp = gp;

    }

    @Override
    public void draw(Graphics g) {
        gp.redBase.draw(g);
        gp.blueBase.draw(g);

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        if (code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.PAUSED;
        }

        // Red control
        if (code == KeyEvent.VK_Z) {
            // upgrade base
            gp.redBase.upgradeBase();

        }
        if (code == KeyEvent.VK_X) {
            // create sword soldier
            gp.redBase.createSwordSoldier(200, 500);
        }
        if (code == KeyEvent.VK_C) {
            // create gun soldier
            
        }
        if (code == KeyEvent.VK_V) {
            // ultimate
            
        }
        


        // Blue control
        if (code == KeyEvent.VK_U) {
            // upgrade base
            gp.blueBase.upgradeBase();
            
        }
        if (code == KeyEvent.VK_I) {
            // create sword soldier
            gp.blueBase.createSwordSoldier(1080, 500);
        }
        if (code == KeyEvent.VK_O) {
            // create gun soldier
            
        }
        if (code == KeyEvent.VK_P) {
            // ultimate
            
        }
    }

    @Override
    public void update() {
        if (RedBase.HP <= 0 && RedBase.HP < BlueBase.HP) {
            gp.gameState = gp.END;
        }

        else if (BlueBase.HP <= 0 && BlueBase.HP < RedBase.HP) {
            gp.gameState = gp.END;
        }
        gp.redBase.update();
        gp.blueBase.update();
    }

}
