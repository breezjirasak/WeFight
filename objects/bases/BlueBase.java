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
import factory.BlueSoldierFactory;

public class BlueBase extends Base {

    public int maxHp;
    public static int HP = 1000;

    public static Soldier firstSoldier;
    public static int positionOfFirstObject = 1080;
    private int countTime;
    public static Cannon cannon = null;
    public boolean ultimate = true;

    public BlueBase(int x, int y) {
        super(x, y, new BlueSoldierFactory());

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
            base = ImageIO.read(new File("images/base/bluebase/base.png"));
            key1 = ImageIO.read(new File("images/widgets/U.png"));
            key2 = ImageIO.read(new File("images/widgets/I.png"));
            key3 = ImageIO.read(new File("images/widgets/O.png"));
            key4 = ImageIO.read(new File("images/widgets/P.png"));
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

        if (RedBase.cannon != null) {
            if (RedBase.cannon.hit) {
                for (Soldier soldier : soldiersSword) {
                    if (soldier.positionX <= RedBase.cannon.targetX + 200
                            && soldier.positionX >= RedBase.cannon.targetX - 200) {
                        soldier.setHp(0);
                    }
                }
                for (Soldier soldier : soldiersGun) {
                    if (soldier.positionX <= RedBase.cannon.targetX + 200
                            && soldier.positionX >= RedBase.cannon.targetX - 200) {
                        soldier.setHp(0);
                    }
                }
                RedBase.cannon = null;
            }
        }

        // increase money level per second
        countTime++;
        if (countTime >= GamePanel.FPS) {
            setMoney(getLevel());
            countTime = 0;
        }

        // update soldier

        if (!soldiersSword.isEmpty() && !soldiersGun.isEmpty()) {

            if (soldiersSword.get(0).positionX <= soldiersGun.get(0).positionX) {
                firstSoldier = soldiersSword.get(0);
            } else {
                firstSoldier = soldiersGun.get(0);
            }

            positionOfFirstObject = firstSoldier.positionX;
            for (Soldier soldier : soldiersSword) {
                soldier.update(RedBase.firstSoldier, "red");
                // check distance with enemy's position
                soldier.checkDistanceToHit(RedBase.positionOfFirstObject);

                // it will check the base position to hit the base
            }
            for (Soldier soldier : soldiersGun) {
                soldier.update(RedBase.firstSoldier, "red");
                // check distance with enemy's position
                soldier.checkDistanceToHit(RedBase.positionOfFirstObject);

                // it will check the base position to hit the base
            }

            // when soldier is dead then remove it
            if (firstSoldier.getHp() <= 0) {
                if (firstSoldier.getClass().getSimpleName().equals("SwordSoldier")) {
                    soldiersSword.remove(0);
                } else {
                    soldiersGun.remove(0);
                }
            }

        } else if (!soldiersSword.isEmpty() && soldiersGun.isEmpty()) {
            firstSoldier = soldiersSword.get(0);
            // The position of first soldier
            positionOfFirstObject = firstSoldier.positionX;
            for (Soldier soldier : soldiersSword) {
                soldier.update(RedBase.firstSoldier, "red");
                // check distance with enemy's position
                soldier.checkDistanceToHit(RedBase.positionOfFirstObject);

                // it will check the base position to hit the base
            }

            // when soldier is dead then remove it
            if (firstSoldier.getHp() <= 0) {
                soldiersSword.remove(0);
            }
        }

        else if (!soldiersGun.isEmpty() && soldiersSword.isEmpty()) {
            firstSoldier = soldiersGun.get(0);
            // The position of first soldier
            positionOfFirstObject = firstSoldier.positionX;
            for (Soldier soldier : soldiersGun) {
                soldier.update(RedBase.firstSoldier, "red");
                // check distance with enemy's position
                soldier.checkDistanceToHit(RedBase.positionOfFirstObject);

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
            positionOfFirstObject = 1080;
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
        g.drawString(text, positionX + 108, 80);

        int x = positionX + 98;
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
        g.fillRect(positionX + 108, 20, 352, 22);

        g.setColor(new Color(23, 97, 151));
        g.fillRect(positionX + 108, 20, (int) hpBarValue, 20);

    }
}
