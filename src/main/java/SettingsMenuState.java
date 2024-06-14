import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsMenuState extends GameState {
    private JButton backButton;

    public SettingsMenuState(GameStateManager gsm, Game game) {
        super(gsm);
        panel.setLayout(new BorderLayout());

        backButton = new JButton("Back to Main Menu");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gsm.setState(GameStateManager.MENU);
            }
        });

        panel.add(backButton, BorderLayout.SOUTH);
    }

    @Override
    public void init() {
        // 初始化設置菜單狀態
    }

    @Override
    public void update() {
        // 更新設置狀態
    }

    @Override
    public void render() {
        // 繪製設置菜單
    }

    @Override
    public void handleInput() {
    }

    @Override
    public void cleanup() {
        // 清理資源
    }
}
