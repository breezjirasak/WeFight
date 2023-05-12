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

    public BufferedImage base, key1, key2, key3, key4;

    public int priceForSwordSoldier = 5;
    public int priceForGunSoldier = 10;
    public int priceForUpgradeBase = level * 10;

    public List<Soldier> soldiersSword = new ArrayList<Soldier>();
    public List<Soldier> soldiersGun = new ArrayList<Soldier>();
    private SoldierFactory factory;

    public Base(int x, int y, SoldierFactory factory) {
        this.positionX = x;
        this.positionY = y;
        this.factory = factory;
    }

    public void upgradeBase() {

        if (money >= priceForUpgradeBase) {
            level++;
            money -= priceForUpgradeBase;
            priceForUpgradeBase = level * 10;
        }
    }

    public void createSwordSoldier(int x, int y) {
        if (money >= priceForSwordSoldier) {
            soldiersSword.add(factory.CreateNewSolider("Sword", x, y));
            money -= priceForSwordSoldier;
        }
    }

    public void createGunSoldier(int x, int y) {
        if (money >= priceForGunSoldier) {
            soldiersGun.add(factory.CreateNewSolider("Gun", x, y));
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
