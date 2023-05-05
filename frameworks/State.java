package frameworks;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

public interface State {
    public void draw(Graphics g);
    public void keyPressed(KeyEvent e);
}
