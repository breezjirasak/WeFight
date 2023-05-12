package objects.soldiers;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.AlphaComposite;
import java.awt.image.BufferedImage;

import frameworks.Soldier;
import main.GamePanel;
import objects.bases.BlueBase;
import objects.bases.RedBase;

public class SwordSoldier extends Soldier {

    public SwordSoldier(int x, int y, String side) {
        super(100, 25, x, y, side);

        getImage("swordsoldier");
    }

    public void checkDistanceToHit(int enemyX) {

        if (side == "blue") {
            if (positionX - 40 <= enemyX) {
                move = false;
            } else {
                move = true;
            }
        }

        else if (side == "red") {
            if (positionX + 40 >= enemyX) {
                move = false;
            } else {
                move = true;
            }

        }
    }

    @Override
    public void update(Soldier enemy, String enemySide) {
        if (move) {
            if (side == "blue") {
                positionX -= 60 / GamePanel.FPS;
            } else if (side == "red") {
                positionX += 60 / GamePanel.FPS;
            }
        }

        spriteCounter++;
        if (move) {
            if (isHit) {
                isHit = false;
            }

            if (spriteCounter > GamePanel.FPS / 4) {
                if (spriteNum == 1 || spriteNum == 3 || spriteNum == 4) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        } else {
            if (spriteCounter > GamePanel.FPS / 6) {
                spriteNum = 3;
                if (enemy == null) {
                    if (enemySide == "red") {
                        RedBase.isHit = false;
                    } else if (enemySide == "blue") {
                        BlueBase.isHit = false;
                    }
                } else {
                    enemy.isHit = false;
                }
            }
            if (spriteCounter > GamePanel.FPS / 2) {
                spriteNum = 4;
                if (enemy == null) {
                    if (enemySide == "red") {
                        RedBase.isHit = true;
                        RedBase.takeDamage(getDamage());
                    } else if (enemySide == "blue") {
                        BlueBase.isHit = true;
                        BlueBase.takeDamage(getDamage());
                    }
                } else {
                    enemy.isHit = true;
                    enemy.takeDamage(getDamage());
                }
                spriteCounter = 0;
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
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

        double oneScale = (double) 50 / maxHp;
        double hpBarValue = oneScale * getHp();

        if (isHit == true) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
        }

        g2.drawImage(image, positionX, positionY, 120, 120, null);

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));


        if (side == "blue") {
            g.setColor(new Color(35, 35, 35));
            g.fillRect(positionX + 39, positionY + 9, 52, 7);
            g.setColor(new Color(23, 97, 151));
            g.fillRect(positionX + 40, positionY + 10, (int) hpBarValue, 5);
        } else if (side == "red") {
            g.setColor(new Color(35, 35, 35));
            g.fillRect(positionX + 32, positionY + 9, 52, 7);
            g.setColor(new Color(255, 0, 30));
            g.fillRect(positionX + 33, positionY + 10, (int) hpBarValue, 5);
        }
    }

}
