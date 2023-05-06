package objects.bases;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import frameworks.Base;
import frameworks.Soldier;
import main.GamePanel;
import factory.GreenSoldierFactory;

public class GreenBase extends Base {
    private GreenSoldierFactory factory = new GreenSoldierFactory();
    public List<Soldier> soldiers = new ArrayList<Soldier>();

    public Soldier soldierFront;

    public int frontX = 1200;
    private int countTime;


    public GreenBase() {
        super();
    }

    public void createSwordSoldier(int x, int y) {
        if (getMoney() >= 2) {
        soldiers.add(factory.CreateNewSolider("Sword", x, y));
        setMoney(-2);
        }
    }

    public void createGunSoldier(int x, int y) {
        if (getMoney() >= 4) {
        soldiers.add(factory.CreateNewSolider("Gun", x, y));
        setMoney(-2);
        }
    }

    @Override
    public void update(int enemyX, Soldier enemy) {

        countTime++;
        if (countTime >= GamePanel.FPS) {
            setMoney(getLevel());
            countTime = 0;
        }

        if (!soldiers.isEmpty()) {
            soldierFront = soldiers.get(0);
            frontX = soldierFront.positionX;
            for (Soldier soldier : soldiers) {
                soldier.update(enemy);

                if (soldier.positionX - 40 <= enemyX) {
                    soldier.move = false;
                } else {
                    soldier.move = true;
                }
            }
            if (soldierFront.getHp() <= 0) {
                soldiers.remove(0);
            }
        } else {
            frontX = 1200;
        }

    }

    @Override
    public void draw(Graphics g) {

        g.setFont(g.getFont().deriveFont(Font.BOLD, 96F));
        String text = "$ " + String.valueOf(getMoney());
        int x = 900;
        int y = 200;

        g.setColor(Color.black);
        g.drawString(text, x, y);


        for (Soldier soldier : soldiers) {
            soldier.draw(g);
        }
    }

    public void findFrontSoldier(Soldier soldier) {
        if (soldier.positionX < frontX) {
            frontX = soldier.positionX;
        }
    }
}
