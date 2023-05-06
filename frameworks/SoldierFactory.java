package frameworks;

public abstract class SoldierFactory {
    public Soldier CreateNewSolider(String weapon, int x, int y) {
        return BuildASoldier(weapon, x, y);
    }

    public abstract Soldier BuildASoldier(String weapon, int x, int y);
}
