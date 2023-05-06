package objects.bases;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import frameworks.Base;
import frameworks.Soldier;
import factory.RedSoldierFactory;

public class RedBase extends Base{
    private RedSoldierFactory factory = new RedSoldierFactory();
    public List<Soldier> soldiers = new ArrayList<Soldier>();
    public RedBase() {
        super();
    }

    public void createSwordSoldier(int x, int y) {
        soldiers.add(factory.CreateNewSolider("Sword", x, y));
    }

    public void createGunSoldier(int x, int y) {
        soldiers.add(factory.CreateNewSolider("Gun", x, y));
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics g) {

    }
}

