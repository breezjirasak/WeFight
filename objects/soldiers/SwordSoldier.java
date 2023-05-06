package objects.soldiers;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import frameworks.Soldier;

public class SwordSoldier extends Soldier {

    public SwordSoldier(int x, int y, String side) {
        super(100, 40, x, y, side);

        getImage("swordsolider");
    }

    @Override
    public void update() {
        if (side == "green") {
            positionX -= 1;
        } else if (side == "red") {
            positionX += 1;
        }

        spriteCounter++;
        if (spriteCounter > 15) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }

    @Override
    public void draw(Graphics g) {
        BufferedImage image = null;

        if (spriteNum == 1) {
            image = walk1;
        }
        if (spriteNum == 2) {
            image = walk2;
        }

        double oneScale = (double) 50 / 100;
        double hpBarValue = oneScale * getHp();

        g.drawImage(image, positionX, positionY, 120, 120, null);

        g.setColor(new Color(35, 35, 35));
        g.fillRect(positionX + 32, positionY + 9, 52, 7);

        g.setColor(new Color(255, 0, 30));
        g.fillRect(positionX + 33, positionY + 10, (int) hpBarValue, 5);

    }

}
