import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsMenuState extends GameState {
    private JButton backButton;

    public SettingsMenuState(Game game) {
        super(game);
        game.setLayout(new FlowLayout());

        backButton = new JButton("Back to Main Menu");

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.changeState(new MainMenuState(game));
            }
        });

        game.add(backButton);
    }

    @Override
    public void handleInput() {
        // 使用 Swing 事件處理
    }

    @Override
    public void update() {
        // 更新設置狀態
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 1000, 600);
        g.setColor(Color.BLACK);
        g.drawString("Settings Menu", 450, 300);
    }
}
