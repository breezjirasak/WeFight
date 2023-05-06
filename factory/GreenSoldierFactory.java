package factory;

import frameworks.Soldier;
import frameworks.SoldierFactory;
import objects.soldiers.GunSoldier;
import objects.soldiers.SwordSoldier;

public class GreenSoldierFactory extends SoldierFactory {
    @Override
    public Soldier BuildASoldier(String weapon, int x, int y) {
        if (weapon == "Sword") {
            return new SwordSoldier(x,y, "green");
        } else if (weapon == "Gun") {
            return new GunSoldier(x,y, "green");
        } else {
            return null;
        }
    }
}
