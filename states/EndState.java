package states;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import frameworks.State;
import main.GamePanel;

public class EndState implements State{

    private GamePanel gp;
    

    public EndState(GamePanel gp) {
        this.gp = gp;
    }
    

    @Override
    public void draw(Graphics g) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }
    
}
