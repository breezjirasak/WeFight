package objects.soldiers;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.AlphaComposite;
import java.awt.image.BufferedImage;

import frameworks.Soldier;

public class SwordSoldier extends Soldier {

    public SwordSoldier(int x, int y, String side) {
        super(100, 25, x, y, side);

        getImage("swordsolider");
    }

    @Override
    public void update(Soldier enemy) {
        if (move) {
            if (side == "green") {
                positionX -= 1;
            } else if (side == "red") {
                positionX += 1;
            }
        }

        spriteCounter++;
        if (move) {
            if (isHit) {
                isHit = false;
            }

            if (spriteCounter > 15) {
                if (spriteNum == 1 || spriteNum == 3 || spriteNum == 4) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
        else {
            if (spriteCounter > 10) {
                spriteNum = 3;
                enemy.isHit = false;
            }
            if (spriteCounter > 30) {
                spriteNum = 4;
                enemy.isHit = true;
                enemy.takeDamage(getDamage());
                spriteCounter = 0;
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        BufferedImage image = null;

        if (spriteNum == 1) {
            image = walk1;
        }
        if (spriteNum == 2) {
            image = walk2;
        }

        if (spriteNum == 3) {
            image = hit1;
        }
        if (spriteNum == 4) {
            image = hit2;
        }

        double oneScale = (double) 50 / 100;
        double hpBarValue = oneScale * getHp();

        if(isHit == true) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
        }

        g2.drawImage(image, positionX, positionY, 120, 120, null);

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        g.setColor(new Color(35, 35, 35));
        g.fillRect(positionX + 32, positionY + 9, 52, 7);

        g.setColor(new Color(255, 0, 30));
        g.fillRect(positionX + 33, positionY + 10, (int) hpBarValue, 5);

    }

}
