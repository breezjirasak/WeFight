package objects.soldiers;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.awt.AlphaComposite;

import frameworks.Soldier;
import main.GamePanel;
import objects.bases.BlueBase;
import objects.bases.RedBase;

public class GunSoldier extends Soldier {

    public int bulletPosX;
    public int bulletPosY;

    public List<Bullet> bullets = new ArrayList<Bullet>();

    public GunSoldier(int x, int y, String side) {
        super(80, 35, x, y, side);

        getImage("gunsoldier");
        this.bulletPosX = x;
        this.bulletPosY = y;
    }

    public void checkDistanceToHit(int enemyX) {

        if (side == "blue") {
            if (positionX - 300 <= enemyX) {
                move = false;
            } else {
                move = true;
            }
        }

        else if (side == "red") {
            if (positionX + 300 >= enemyX) {
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
            if (spriteCounter > GamePanel.FPS) {
                spriteNum = 4;
                if (side == "red") {
                    bullets.add(new Bullet(positionX + 100, positionY + 25, "red"));
                } else if (side == "blue") {
                    bullets.add(new Bullet(positionX, positionY + 25, "blue"));
                }

                spriteCounter = 0;
            }
        }

        int index = 0;
        if (enemy != null) {
            if (!bullets.isEmpty()) {
                for (Bullet bullet : bullets) {
                    if ((bullet.side == "blue") && (bullet.positionX - 60 <= enemy.positionX)) {
                        bullets.remove(index);
                        enemy.isHit = true;
                        enemy.takeDamage(getDamage());
                        index++;
                        break;
                    }
                    else if ((bullet.side == "red") && (bullet.positionX - 60 >= enemy.positionX)) {
                        bullets.remove(index);
                        enemy.isHit = true;
                        enemy.takeDamage(getDamage());
                        index++;
                        break;
                    }
                    bullet.update();
                    index++;
                }
            }
        }

        else if (enemy == null) {
            if (!bullets.isEmpty()) {
                for (Bullet bullet : bullets) {
                    if ((bullet.side == "blue") && (bullet.positionX - 100 <= RedBase.positionOfFirstObject)) {
                        bullets.remove(index);
                        RedBase.isHit = true;
                        RedBase.takeDamage(getDamage());
                        index++;
                        break;
                    }
                    else if ((bullet.side == "red") && (bullet.positionX - 100 >= BlueBase.positionOfFirstObject)) {
                        bullets.remove(index);
                        BlueBase.isHit = true;
                        BlueBase.takeDamage(getDamage());
                        index++;
                        break;
                    }
                    bullet.update();
                    index++;
                }
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

        g.setColor(Color.black);
   
        if (!bullets.isEmpty()) {
            for (Bullet bullet : bullets) {
                bullet.draw(g);
            }
        }


    }

}
