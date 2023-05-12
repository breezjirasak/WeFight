package objects.bases;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import frameworks.Base;
import frameworks.Soldier;
import main.GamePanel;
import factory.RedSoldierFactory;

public class RedBase extends Base {

    public int maxHp;
    public static int HP = 1000;

    public static Soldier firstSoldier;
    public static int positionOfFirstObject = 0;
    private int countTime;
    public static Cannon cannon = null;
    public boolean ultimate = true;

    public RedBase(int x, int y) {
        super(x, y, new RedSoldierFactory());

        this.maxHp = HP;
        getImage();
    }

    public void ultimate(int x, int y) {
        if (ultimate) {
            cannon = new Cannon(x, y);
        }
    }

    public void getImage() {
        try {
            base = ImageIO.read(new File("images/base/redbase/base.png"));
            key1 = ImageIO.read(new File("images/widgets/Z.png"));
            key2 = ImageIO.read(new File("images/widgets/X.png"));
            key3 = ImageIO.read(new File("images/widgets/C.png"));
            key4 = ImageIO.read(new File("images/widgets/V.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void takeDamage(int damage) {
        HP -= damage;
    }

    @Override
    public void update() {
        if (cannon != null) {
            cannon.update();
        }

        if (BlueBase.cannon != null) {
            if (BlueBase.cannon.hit) {
                for (Soldier soldier : soldiersSword) {
                    if (soldier.positionX <= BlueBase.cannon.targetX + 200 && soldier.positionX >= BlueBase.cannon.targetX - 200 ) {
                        soldier.setHp(0);
                    }
                }
                for (Soldier soldier : soldiersGun) {
                    if (soldier.positionX <= BlueBase.cannon.targetX + 200 && soldier.positionX >= BlueBase.cannon.targetX - 200 ) {
                        soldier.setHp(0);
                    }
                }
                BlueBase.cannon = null;
            }
        }

        // increase money ... per second
        countTime++;
        if (countTime >= GamePanel.FPS) {
            setMoney(getLevel());
            countTime = 0;
        }

        // update soldier
        if (!soldiersSword.isEmpty() && !soldiersGun.isEmpty()) {
            if (soldiersSword.get(0).positionX >= soldiersGun.get(0).positionX) {
                firstSoldier = soldiersSword.get(0);
            }
            else {
                firstSoldier = soldiersGun.get(0);
            }

            positionOfFirstObject = firstSoldier.positionX;
            for (Soldier soldier : soldiersSword) {
                soldier.update(BlueBase.firstSoldier, "blue");
                // check distance with enemy's position
                soldier.checkDistanceToHit(BlueBase.positionOfFirstObject);

                // it will check the base position to hit the base
            }
            for (Soldier soldier : soldiersGun) {
                soldier.update(BlueBase.firstSoldier, "blue");
                // check distance with enemy's position
                soldier.checkDistanceToHit(BlueBase.positionOfFirstObject);

                // it will check the base position to hit the base
            }

            // when soldier is dead then remove it
            if (firstSoldier.getHp() <= 0) {
                if (firstSoldier.getClass().getSimpleName().equals("SwordSoldier")) {
                    soldiersSword.remove(0);
                }
                else {soldiersGun.remove(0);}
            }

        }
        else if (!soldiersSword.isEmpty()) {

            firstSoldier = soldiersSword.get(0);
            // The position of first soldier
            positionOfFirstObject = firstSoldier.positionX;
            for (Soldier soldier : soldiersSword) {
                soldier.update(BlueBase.firstSoldier, "blue");
                // check distance with enemy's position
                soldier.checkDistanceToHit(BlueBase.positionOfFirstObject);

                // it will check the base position to hit the base
            }
            for (Soldier soldier : soldiersGun) {
                soldier.update(BlueBase.firstSoldier, "blue");
                // check distance with enemy's position
                soldier.checkDistanceToHit(BlueBase.positionOfFirstObject);

                // it will check the base position to hit the base
            }

            // when soldier is dead then remove it
            if (firstSoldier.getHp() <= 0) {
                soldiersSword.remove(0);
            }
        }
        else if (!soldiersGun.isEmpty()) {
            firstSoldier = soldiersGun.get(0);
            // The position of first soldier
            positionOfFirstObject = firstSoldier.positionX;
            for (Soldier soldier : soldiersSword) {
                soldier.update(BlueBase.firstSoldier, "blue");
                // check distance with enemy's position
                soldier.checkDistanceToHit(BlueBase.positionOfFirstObject);

                // it will check the base position to hit the base
            }
            for (Soldier soldier : soldiersGun) {
                soldier.update(BlueBase.firstSoldier, "blue");
                // check distance with enemy's position
                soldier.checkDistanceToHit(BlueBase.positionOfFirstObject);

                // it will check the base position to hit the base
            }

            // when soldier is dead then remove it
            if (firstSoldier.getHp() <= 0) {
                soldiersGun.remove(0);
            }

        }

        else {
            // if there is no soldier it will be the position of base
            firstSoldier = null;
            positionOfFirstObject = 180;
        }
    }

    @Override
    public void draw(Graphics g) {

        if (cannon != null) {
            cannon.draw(g);
        }


        g.setFont(g.getFont().deriveFont(Font.BOLD, 40F));
        String text = "$ " + String.valueOf(getMoney());

        g.setColor(Color.black);
        g.drawString(text, positionX + 20, 80);

        int x = positionX + 10;

        g.drawImage(key1, x, 90, 100, 100, null);
        text = "$ " + priceForUpgradeBase;
        g.setFont(g.getFont().deriveFont(Font.BOLD, 20F));
        g.drawString(text, x + 28, 190);

        x += 80;
        g.drawImage(key2, x, 90, 100, 100, null);
        text = "$ " + priceForSwordSoldier;
        g.setFont(g.getFont().deriveFont(Font.BOLD, 20F));
        g.drawString(text, x + 33, 190);

        x += 80;
        g.drawImage(key3, x, 90, 100, 100, null);
        text = "$ " + priceForGunSoldier;
        g.setFont(g.getFont().deriveFont(Font.BOLD, 20F));
        g.drawString(text, x + 28, 190);

        x += 80;
        g.drawImage(key4, x, 90, 100, 100, null);
        if (ultimate) {
            text = "1/1";
        }
        else {text = "0/1";}
        g.setFont(g.getFont().deriveFont(Font.BOLD, 20F));
        g.drawString(text, x + 32, 190);


        g.drawImage(base, positionX, positionY, 480, 480, null);

        for (Soldier soldier : soldiersSword) {
            soldier.draw(g);
        }

        for (Soldier soldier : soldiersGun) {
            soldier.draw(g);
        }

        double oneScale = (double) 350 / maxHp;
        double hpBarValue = oneScale * HP;

        g.setColor(new Color(35, 35, 35));
        g.fillRect(positionX + 20, 20, 352, 22);

        g.setColor(new Color(255, 0, 30));
        g.fillRect(positionX + 20, 20, (int) hpBarValue, 20);
    }
}
