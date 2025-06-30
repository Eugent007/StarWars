import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    private GamePanel gamePanel;
    public GameFrame(int size) throws HeadlessException {
        setFocusable(true);
        setBounds(100, 100, size, size);
        setVisible(true);
        gamePanel = new GamePanel();
        add(gamePanel);
        addKeyListener(gamePanel);
    }
}
