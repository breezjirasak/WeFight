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
    public static int positionOfFirstObject = 1100;
    private int countTime;


    public BlueBase(int x, int y) {
        super(x, y, new BlueSoldierFactory());

        this.maxHp = HP;

        getImage();
    }

    public void getImage() {
        try {
            base = ImageIO.read(new File("images/base/bluebase/base.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void takeDamage(int damage) {
        HP -= damage;
    }

    @Override
    public void update() {

        // increase money level per second
        countTime++;
        if (countTime >= GamePanel.FPS) {
            setMoney(getLevel());
            countTime = 0;
        }


        // update soldier
        if (!soldiers.isEmpty()) {
            firstSoldier = soldiers.get(0);
            // The position of first soldier
            positionOfFirstObject = firstSoldier.positionX;
            for (Soldier soldier : soldiers) {
                soldier.update(RedBase.firstSoldier, "red");
                // check distance with enemy's position
                soldier.checkDistanceToHit(RedBase.positionOfFirstObject);

                // it will check the base position to hit the base ...
                // if enemybase.firstobject == ....
                // then hit solder.update(base)

            }
            // when soldier is dead then remove it
            if (firstSoldier.getHp() <= 0) {
                soldiers.remove(0);
            }
        } else {
            // if there is no soldier it will be the position of base
            firstSoldier = null;
            positionOfFirstObject = 1080;
        }

    }

    @Override
    public void draw(Graphics g) {

        g.setFont(g.getFont().deriveFont(Font.BOLD, 50F));
        String text = "$ " + String.valueOf(getMoney());

        g.setColor(Color.black);
        g.drawString(text, positionX + 108, 100);

        g.drawImage(base, positionX, positionY, 480, 480, null);

        for (Soldier soldier : soldiers) {
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
