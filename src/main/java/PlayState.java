import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class PlayState extends GameState {

    private Map<Integer, boolean[]> levelProgress;

    public PlayState(GameStateManager gsm, Game game) {
        super(gsm);
        panel.setLayout(new BorderLayout());

        loadLevelProgress();

        JPanel mainPanel = new JPanel(new GridLayout(0, 1, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for (int chapter = 1; chapter <= 5; chapter++) {
            JLabel chapterLabel = new JLabel("Chapter " + chapter);
            mainPanel.add(chapterLabel);

            for (int level = 1; level <= 5; level++) {
                JButton levelButton = new JButton("Chapter " + chapter + " - Level " + level);
                int finalChapter = chapter;
                int finalLevel = level;

                if (finalLevel == 5 && !isChapterCompleted(finalChapter)) {
                    levelButton.setBackground(Color.DARK_GRAY);
                    levelButton.setForeground(Color.LIGHT_GRAY);
                }

                levelButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (finalLevel == 5 && !isChapterCompleted(finalChapter)) {
                            JOptionPane.showMessageDialog(panel, "You must complete levels 1-4 of this chapter before accessing level 5.");
                        } else {
                            gsm.setState(GameStateManager.LEVEL, finalChapter, finalLevel);
                        }
                    }
                });

                mainPanel.add(levelButton);
            }
        }

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        panel.add(scrollPane, BorderLayout.CENTER);
    }

    private void loadLevelProgress() {
        Gson gson = new Gson();
        File file = new File("level_progress.json");
        if (file.exists()) {
            try (FileReader reader = new FileReader(file)) {
                Type type = new TypeToken<Map<Integer, boolean[]>>() {}.getType();
                levelProgress = gson.fromJson(reader, type);
                if (levelProgress == null) {
                    levelProgress = new HashMap<>();
                }
            } catch (IOException e) {
                levelProgress = new HashMap<>();
            }
        } else {
            levelProgress = new HashMap<>();
            saveLevelProgress(); // 如果檔案不存在，創建一個新的
        }
    }

    private void saveLevelProgress() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter("level_progress.json")) {
            gson.toJson(levelProgress, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isChapterCompleted(int chapter) {
        if (!levelProgress.containsKey(chapter)) {
            return false;
        }
        boolean[] levels = levelProgress.get(chapter);
        for (int i = 0; i < 4; i++) {
            if (!levels[i]) {
                return false;
            }
        }
        return true;
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
