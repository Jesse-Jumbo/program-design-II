import java.awt.*;

public class PlayState extends GameState {

    public PlayState(Game game) {
        super(game);
        game.setLayout(null); // 清除佈局管理器
    }

    @Override
    public void handleInput() {
        // 處理遊戲中的輸入
    }

    @Override
    public void update() {
        // 更新遊戲狀態
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(0, 0, 1000, 600);
        g.setColor(Color.BLACK);
        g.drawString("Game is now playing...", 450, 300);
    }
}
