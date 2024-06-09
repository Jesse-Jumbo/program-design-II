import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuState extends GameState {
    private JButton startButton;
    private JButton settingsButton;
    private JButton exitButton;

    public MainMenuState(Game game) {
        super(game);
        game.setLayout(new FlowLayout());

        startButton = new JButton("Start Game");
        settingsButton = new JButton("Settings");
        exitButton = new JButton("Exit");

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.changeState(new PlayState(game));
                startButton.setText("Restart Game");
            }
        });

        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.changeState(new SettingsMenuState(game));
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.exit();
            }
        });

        game.add(startButton);
        game.add(settingsButton);
        game.add(exitButton);
    }

    @Override
    public void handleInput() {
        // 使用 Swing 事件處理
    }

    @Override
    public void update() {
        // 更新菜單狀態
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 1000, 600);
        g.setColor(Color.BLACK);
        g.drawString("Welcome to Program Design II", 450, 300);
    }
}
