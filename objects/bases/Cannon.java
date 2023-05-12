package objects.bases;

import java.awt.*;

public class Cannon {
    private int x;
    private int y;
    public int targetX = 700;
    public int targetY = 500;

    public int state = 1;

    public int count = 0;

    public boolean hit = false;

    public Cannon(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void update() {
        if (y <= 0) {
            count++;
            if (count > 30) {
            state = 2;
            x = targetX;
            }
        }

        if (state == 1) {
            y -= 15;
        } else if (state == 2) {
            y += 20;
        }

        if (y >= targetY && state == 2) {
            hit = true;
        }

    }

    public void draw (Graphics g) {
        g.setColor(Color.black);
        if (!(y >= targetY && state == 2)) {
            g.fillOval(x, y, 50, 50);
        }
    }
}
