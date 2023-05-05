package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

    private GamePanel gp = new GamePanel();

    

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        gp.gameState.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    
}
