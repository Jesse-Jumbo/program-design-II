import javax.swing.*;

public abstract class GameState {
    protected GameStateManager gsm;
    protected JPanel panel;
    protected MusicPlayer musicPlayer;

    public GameState(GameStateManager gsm) {
        this.gsm = gsm;
        this.panel = new JPanel();
        musicPlayer = new MusicPlayer();
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
