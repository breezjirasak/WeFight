package factory;

import frameworks.Soldier;
import frameworks.SoldierFactory;
import objects.soldiers.GunSoldier;
import objects.soldiers.SwordSoldier;

public class RedSoldierFactory extends SoldierFactory {
    @Override
    public Soldier BuildASoldier(String weapon, int x, int y) {
        if (weapon == "Sword") {
            return new SwordSoldier(x, y, "red");
        } else if (weapon == "Gun") {
            return new GunSoldier(x, y, "red");
        } else {
            return null;
        }
    }
}
