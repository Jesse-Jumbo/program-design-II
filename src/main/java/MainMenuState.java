import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.Map;

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
                if (isLevelProgressEmpty()) {
                    gsm.setState(GameStateManager.STORY, 0); // 播放 Chapter 0 的劇情
                } else {
                    gsm.setState(GameStateManager.PLAY);
                }
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

    private boolean isLevelProgressEmpty() {
        Gson gson = new Gson();
        File file = new File("level_progress.json");
        if (!file.exists()) {
            return true; // 文件不存在，視為空
        }
        try (FileReader reader = new FileReader(file)) {
            Type type = new TypeToken<Map<Integer, boolean[]>>() {}.getType();
            Map<Integer, boolean[]> levelProgress = gson.fromJson(reader, type);
            return levelProgress == null || levelProgress.isEmpty();
        } catch (IOException e) {
            e.printStackTrace();
            return true; // 讀取失敗，視為空
        }
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
