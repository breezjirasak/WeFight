package frameworks;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.awt.image.BufferedImage;

public abstract class Base {
    public int money = 0;
    public int level = 1;
    public int positionX;
    public int positionY;
    public static boolean isHit = false;

    public BufferedImage base;

    public int priceForSwordSoldier = 2;
    public int priceForGunSoldier = 4;
    public int priceForUpgradeBase = level * 3;

    public List<Soldier> soldiers = new ArrayList<Soldier>();
    private SoldierFactory factory;

    public Base(int x, int y, SoldierFactory factory) {
        this.positionX = x;
        this.positionY = y;
        this.factory = factory;
    }

    public void upgradeBase () {

        if (money >= priceForUpgradeBase) {
            level++;
            money -= priceForUpgradeBase;
        }
    }

    public void createSwordSoldier(int x, int y) {
        if (money >= priceForSwordSoldier) {
            soldiers.add(factory.CreateNewSolider("Sword", x, y));
            money -= priceForSwordSoldier;
        }
    }

    public void createGunSoldier(int x, int y) {
        if (money >= priceForGunSoldier) {
            soldiers.add(factory.CreateNewSolider("Gun", x, y));
            money -= priceForGunSoldier;
        }
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money += money;
    }

    public int getLevel() {
        return level;
    }

    public abstract void update();

    public abstract void draw(Graphics g);

}
