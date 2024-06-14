import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class PlayState extends GameState {

    public PlayState(GameStateManager gsm, Game game) {
        super(gsm);
        panel.setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new GridLayout(0, 1, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for (int chapter = 1; chapter <= 5; chapter++) {
            JLabel chapterLabel = new JLabel("Chapter " + chapter);
            mainPanel.add(chapterLabel);

            for (int level = 1; level <= 5; level++) {
                JButton levelButton = new JButton("Chapter " + chapter + " - Level " + level);
                int finalChapter = chapter;
                int finalLevel = level;
                levelButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        gsm.setState(GameStateManager.LEVEL, finalChapter, finalLevel);
                    }
                });
                mainPanel.add(levelButton);
            }
        }

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        panel.add(scrollPane, BorderLayout.CENTER);
    }

    @Override
    public void init() {

    }

    @Override
    public void handleInput() {
    }

    @Override
    public void update() {
    }

    @Override
    public void render() {
    }

    @Override
    public void cleanup() {
        // 清理資源
    }
}
