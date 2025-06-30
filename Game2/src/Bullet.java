import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Bullet extends Unit implements Attack{
    public int damage;
    Image image_bullet;

    public Bullet(int x, int y, int speed, double angle, Image image, int damage) {
        super(x, y, speed, angle, image);
        this.damage = damage;

        try {
            image_bullet = ImageIO.read(new File("src/bullet_1.png"));
        } catch (IOException e){
            System.out.println("oblom");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void draw(Graphics g) {
        g.fillOval(this.x, this.y, 15, 15);
    }

    @Override
    public void move() {
        int dy = (int)(Math.cos(angle) * speed);
        int dx = (int)(Math.sin(angle) * speed);

        x += dx;
        y -= dy;
    }

    @Override
    public void toAttack(HealthPoint enemy) {
        enemy.setHP(enemy.getHP() - damage);
    }
}
