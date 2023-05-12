package objects.soldiers;

import java.awt.Graphics;

public class Bullet {
    public int positionX;
    public int positionY;
    public String side;

    public Bullet(int x, int y, String side) {
        this.positionX = x;
        this.positionY = y;
        this.side = side;
    }

    public void update() {
        if (side == "blue") {
            positionX -= 4;
        } else if (side == "red") {
            positionX += 4;
        }
    }

    public void draw(Graphics g) {
        g.fillOval(positionX, positionY + 50, 5, 5);
    }

}
