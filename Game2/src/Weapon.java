import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class Weapon implements Attack {
    public LinkedList<Bullet> bullets;

    @Override
    public void toAttack(HealthPoint enemy) {
        ArrayList<Bullet> delete = new ArrayList<Bullet>();
        Ship ship = (Ship)enemy;
        for (int i = 0; i < bullets.size(); i++) {
            if(Math.hypot(ship.scale * (ship.x + ship.sx) - bullets.get(i).x, ship.scale * (ship.y + ship.sy) - bullets.get(i).y) <= ship.radius){
                bullets.get(i).toAttack(enemy);
                delete.add(bullets.get(i));
            }
        }
        bullets.removeAll(delete);
        //bullets.get(i).toAttack(enemy);
    }

    public Weapon(){
        bullets = new LinkedList<Bullet>();
    }

    public void draw(Graphics g){
        if(bullets.size() > 10){
            bullets.remove(0);
        }
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).draw(g);
        }
    }
    public void move(){
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).move();
        }
    }

    public void GameOver(){
        bullets.removeAll(bullets);
    }
}
