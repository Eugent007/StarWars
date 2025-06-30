import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

public class GamePanel extends JPanel implements KeyListener, ActionListener {
    public Ship orc;
    public Ship hunter;
    public Timer timer;
    public HealthBar HB1;
    public HealthBar HB2;
    private int GameOver = 0;
    public GamePanel() {
        timer = new Timer(50, this);
        timer.start();
        try {
            Image image_orc = ImageIO.read(new File("src/orc_ship.png"));
            orc = new Ship(1000, 200, 50, 0, image_orc, new Weapon(), 10, 0.38, 100, 1);

            Image image_hunter = ImageIO.read(new File("src/hunter_ship.png"));
            hunter = new Ship(50, 200, 20, 0, image_hunter, new Weapon(), 300, 0.3, 100, 2);

            Image image_bullet = ImageIO.read(new File("src/hunter_ship.png"));

            HB1 = new HealthBar(10, 10, hunter);
            HB2 = new HealthBar(10, 10, orc);

        } catch (IOException e){
            System.out.println("oblom");
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        orc.draw(g);
        hunter.draw(g);
        g.setColor(Color.GREEN);
        HB1.draw(g);
        g.setColor(Color.RED);
        HB2.x = getWidth() - 210;
        HB2.draw(g);
        g.setColor(Color.BLACK);

        if(GameOver == 1){
            g.setColor(Color.DARK_GRAY);
            g.setFont(new Font("Arial", Font.BOLD, 50));
            g.drawString("GAME OVER", getWidth()/2 - 200, getHeight()/2 - 50);
            g.drawString("ORC WINS!!!", getWidth()/2 - 200, getHeight()/2 + 50);
        }
        else if(GameOver == 2){
            g.setColor(Color.DARK_GRAY);
            g.setFont(new Font("Arial", Font.BOLD, 50));
            g.drawString("GAME OVER", getWidth()/2 - 200, getHeight()/2 - 50);
            g.drawString("HUNTER WINS!!!", getWidth()/2 - 200, getHeight()/2 + 50);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP){
            orc.direction.up = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            orc.direction.down = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            orc.direction.left = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            orc.direction.right = true;
        }

        if(e.getKeyCode() == KeyEvent.VK_W){
            hunter.direction.up = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_S){
            hunter.direction.down = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_A){
            hunter.direction.left = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_D){
            hunter.direction.right = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP){
            orc.direction.up = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            orc.direction.down = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            orc.direction.left = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            orc.direction.right = false;
        }

        if(e.getKeyCode() == KeyEvent.VK_W){
            hunter.direction.up = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_S){
            hunter.direction.down = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_A){
            hunter.direction.left = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_D){
            hunter.direction.right = false;
        }

        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            if (hunter != null) {
                hunter.shoot();
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_K){
            if (orc != null) {
                orc.shoot();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(orc != null) {
            orc.move();
            if(orc.weapon != null){
                orc.weapon.move();
                orc.weapon.toAttack(hunter);
                if(hunter != null && !hunter.isAlive()){
                    System.out.println("oops");
                    orc.weapon.GameOver();
                    GameOver = 1;
                    timer.stop();
                }
            }
        }
        if(hunter != null) {
            hunter.move();
            if(hunter.weapon != null){
                hunter.weapon.move();
                hunter.weapon.toAttack(orc);
                if(!orc.isAlive()){
                    System.out.println("killed");
             //       showGameOverMessage("GameOver!!!\nHunter wins!!!");
                    hunter.weapon.GameOver();
                    GameOver = 2;
                    timer.stop();
                }
            }
        }
        repaint();
    }
/*
    private void showGameOverMessage(String message) {
        // Вы можете использовать Graphics для отображения сообщения
        Graphics g = this.getGraphics();
        g.setColor(Color.GREEN);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.drawString(message, 10, 10);
    }

*/
}
