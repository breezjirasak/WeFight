package frameworks;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public abstract class Soldier {
    private int hp;
    public int maxHp;
    private int damage;
    public String side;
    public int positionX;
    public int positionY;
    public boolean move = true;
    public boolean isHit = false;

    public BufferedImage walk1, walk2, hit1, hit2;
    public int spriteCounter = 0;
    public int spriteNum = 1;

    public Soldier(int hp, int damage, int x, int y, String side) {
        this.hp = hp;
        this.maxHp = hp;
        this.damage = damage;
        this.positionX = x;
        this.positionY = y;
        this.side = side;

    }

    public void getImage(String weapon) {
        try {
            walk1 = ImageIO.read(new File("images/soldier/" + weapon + "/" + side + "walk1.png"));
            walk2 = ImageIO.read(new File("images/soldier/" + weapon + "/" + side + "walk2.png"));
            hit1 = ImageIO.read(new File("images/soldier/" + weapon + "/" + side + "hit1.png"));
            hit2 = ImageIO.read(new File("images/soldier/" + weapon + "/" + side + "hit2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void takeDamage(int damage) {
        hp -= damage;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getDamage() {
        return damage;
    }

    public abstract void checkDistanceToHit(int enemyX);

    public abstract void update(Soldier enemy, String enemySide);

    public abstract void draw(Graphics g);
}
