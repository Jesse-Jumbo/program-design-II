import java.awt.*;
import javax.swing.*;

public class PlayState extends GameState {

    private boolean isStoryPanelAdded = false;
    private StoryPanel storyPanel;

    public PlayState(Game game) {
        super(game);
        game.setLayout(new BorderLayout()); // 使用 BorderLayout 佈局管理器

        JButton northButton = new JButton("North");
        game.add(northButton, BorderLayout.NORTH);

        JButton southButton = new JButton("South");
        game.add(southButton, BorderLayout.SOUTH);

        JButton eastButton = new JButton("East");
        game.add(eastButton, BorderLayout.EAST);

        JButton westButton = new JButton("West");
        game.add(westButton, BorderLayout.WEST);

        JButton centerButton = new JButton("Center");
        game.add(centerButton, BorderLayout.CENTER);

        // 顯示框架
        game.setVisible(true);
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
