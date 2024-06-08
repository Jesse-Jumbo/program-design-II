import java.awt.*;

public abstract class GameState {
    protected Game game;

    public GameState(Game game) {
        this.game = game;
    }

    public abstract void handleInput();
    public abstract void update();
    public abstract void render(Graphics g);
}
