import javax.swing.*;
import java.awt.*;

public abstract class GameState {
    protected GameStateManager gsm;
    protected JPanel panel;

    public GameState(GameStateManager gsm) {
        this.gsm = gsm;
        this.panel = new JPanel();
    }

    public abstract void init();
    public abstract void handleInput();
    public abstract void update();
    public abstract void render();
    public abstract void cleanup(); // 新增方法

    public JPanel getPanel() {
        return panel;
    }
}
