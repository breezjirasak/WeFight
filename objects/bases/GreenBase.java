package objects.bases;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import frameworks.Base;
import frameworks.Soldier;
import factory.GreenSoldierFactory;

public class GreenBase extends Base {
    private GreenSoldierFactory factory = new GreenSoldierFactory();
    public List<Soldier> soldiers = new ArrayList<Soldier>();

    public Soldier soldierFront;

    public int frontX = 1200;

    public GreenBase() {
        super();
    }

    public void createSwordSoldier(int x, int y) {
        soldiers.add(factory.CreateNewSolider("Sword", x, y));
    }

    public void createGunSoldier(int x, int y) {
        soldiers.add(factory.CreateNewSolider("Gun", x, y));
    }

    @Override
    public void update(int enemyX, Soldier enemy) {

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
