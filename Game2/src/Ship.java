import java.awt.*;

public class Ship extends Unit implements HealthPoint {
    public Weapon weapon;
    public int hp = 1;
    private int max_hp = 1;
    public double scale = 1;
    public int radius = 1;

    public double sx = 0;
    public double sy = 0;

    int damage;

    public Ship(int x, int y, int speed, double angle, Image image, Weapon weapon, int hp, double scale, int radius, int damage) {
        super(x, y, speed, angle, image);
        this.weapon = weapon;
        this.hp = hp;
        this.max_hp = hp;
        this.scale = scale;
        this.radius = radius;
        this.sx = image.getWidth(null) / 2.;
        this.sy = image.getWidth(null) / 2.;
        this.damage = damage;
    }

    @Override
    public void move() {
        int dy = (int)(Math.cos(angle) * speed);
        int dx = (int)(Math.sin(angle) * speed);
        if(direction.up){
            x += dx;
            y -= dy;
        }
        if(direction.down){
            x -= dx;
            y += dy;
        }
        if(direction.left){
            if(direction.down){
                angle += 0.1;
            }
            else {
                angle -= 0.1;
            }
        }
        if(direction.right){
            if(direction.down){
                angle -= 0.1;
            }
            else {
                angle += 0.1;
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.scale(scale, scale);
        g2d.rotate(angle, x + (image.getWidth(null) / 2.), y + (image.getHeight(null) / 2.));
        g.drawImage(image, x, y, null);
        g2d.rotate(-angle, x + (image.getWidth(null) / 2.), y + (image.getHeight(null) / 2.));
        g2d.scale(1 / scale, 1 / scale);
        if(weapon != null) {
            weapon.draw(g);
        }
    }

    @Override
    public int getHP() {
        return hp;
    }

    @Override
    public void setHP(int HealthPoint) {
        hp = HealthPoint;
    }

    @Override
    public int getMaxHP(){
        return max_hp;
    }

    @Override
    public boolean isAlive() {
        return hp > 0;
    }

    public void shoot() {
        int bulletDistance = 150;
        int bullet_speed = 60;

        int bulletX1 = (int)(scale*(x + image.getWidth(null) / 2. - bulletDistance * Math.cos(angle)));
        int bulletY1 = (int)(scale*(y + image.getHeight(null) / 2. - bulletDistance * Math.sin(angle)));
        weapon.bullets.add(new Bullet(bulletX1, bulletY1, bullet_speed, angle, null, damage));

        int bulletX2 = (int)(scale*(x + image.getWidth(null) / 2. + bulletDistance * Math.cos(angle)));
        int bulletY2 = (int)(scale*(y + image.getHeight(null) / 2. + bulletDistance * Math.sin(angle)));
        weapon.bullets.add(new Bullet(bulletX2, bulletY2, bullet_speed, angle, null, damage));
    }


    /*
    * public void shoot(){
        int width = image.getWidth(null);
        int height = image.getHeight(null);
        int bulletDistance = 100;

        int bulletX1 = (int) (x + (width / 2) - bulletDistance * Math.cos(Math.abs(angle)));
        int bulletY1 = (int) (y + (height / 2) - bulletDistance * Math.sin(Math.abs(angle)));
        weapon.bullets.add(new Bullet(bulletX1, bulletY1, speed * 3, angle, null, 1));

        int bulletX2 = (int) (x + (width / 2) + bulletDistance * Math.cos(Math.abs(angle)));
        int bulletY2 = (int) (y + (height / 2) + bulletDistance * Math.sin(Math.abs(angle)));
        weapon.bullets.add(new Bullet(bulletX2, bulletY2, speed * 3, angle, null, 1));

    }
    * */
}
