import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuState extends GameState {
    private JButton startButton;
    private JButton settingsButton;

    public MainMenuState(GameStateManager gsm, Game game) {
        super(gsm);
        panel.setLayout(new GridLayout(2, 1));

        startButton = new JButton("Start Game");
        settingsButton = new JButton("Settings");

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gsm.setState(GameStateManager.PLAY);
            }
        });

        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gsm.setState(GameStateManager.SETTING);
            }
        });

        panel.add(startButton);
        panel.add(settingsButton);
    }

    @Override
    public void init() {
        // 初始化主菜單狀態
    }

    @Override
    public void update() {
        // 更新主菜單狀態
    }

    @Override
    public void render() {
        // 繪製主菜單
    }

    @Override
    public void handleInput() {
        // 處理輸入
    }

    @Override
    public void cleanup() {
        // 清理資源
    }
}
