import java.awt.*;

public class HealthBar implements Drawing {
    public HealthPoint hp;
    public int x;
    public int y;
    HealthBar(int x, int y, HealthPoint ship){
        this.x = x;
        this.y = y;
        this.hp = ship;
    }
    @Override
    public void draw(Graphics g) {
        g.drawRect(x,y,200,30);
        g.fillRect(x, y, 200 * hp.getHP() / hp.getMaxHP(), 30);

        g.setColor(Color.DARK_GRAY);
        g.setFont(new Font("Arial", Font.BOLD, 15));
        g.drawString(Integer.toString(Math.max(0,hp.getHP())), x+95, y+20);
    }
}
