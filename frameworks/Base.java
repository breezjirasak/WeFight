package frameworks;

import java.awt.Graphics;

public abstract class Base {
    private int hp;
    private int money;
    private int level;

    public Base() {
        this.hp = 1000;
        this.money = 0;
        this.level = 1;
    }

    public void baseUpgrade() {
        if (money >= (level * 3)) {
            level++;
            money -= level * 3;
        }
    }

    public void moneyIncrease() {
        money += level;
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

    public abstract void update(int enemyX, Soldier enemy);

    public abstract void draw(Graphics g);


}

