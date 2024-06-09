import java.awt.*;
import javax.swing.*;

public class PlayState extends GameState {

    private boolean isStoryPanelAdded = false;
    private StoryPanel storyPanel;

    public PlayState(Game game) {
        super(game);
        game.setLayout(new BorderLayout()); // 使用 BorderLayout 佈局管理器
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
        if (!isStoryPanelAdded) {
            storyPanel = new StoryPanel();
            game.add(storyPanel, BorderLayout.CENTER);
            game.validate();
            isStoryPanelAdded = true;
        }
    }
}
