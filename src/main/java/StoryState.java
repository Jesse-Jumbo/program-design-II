import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStream;
import java.util.List;

public class StoryState extends GameState {

    private int chapter;
    private int currentIndex = 1;
    private List<GameStory.Chapter> chapters;
    private BufferedImage image;

    public StoryState(GameStateManager gsm, int chapter) {
        super(gsm);
        this.chapter = chapter;
        loadChapters();
        loadNextImage();
        setupPanel();
    }

    private void loadChapters() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            InputStream inputStream = GameStory.class.getClassLoader().getResourceAsStream("assets/chapter/chapters.json");
            chapters = mapper.readValue(inputStream, mapper.getTypeFactory().constructCollectionType(List.class, GameStory.Chapter.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadNextImage() {
        if (currentIndex > chapters.get(chapter).getCount()) {
            gsm.setState(GameStateManager.PLAY); // 切换回游戏状态
            return;
        }
        String filePath = chapters.get(chapter).getPath() + "/" + currentIndex + "." + chapters.get(chapter).getType();
        image = loadImage(filePath);
        currentIndex++;
        panel.repaint();
    }

    private BufferedImage loadImage(String filePath) {
        try {
            BufferedImage image = ImageIO.read(GameStory.class.getClassLoader().getResource(filePath));
            if (image == null) {
                System.err.println("Image not found: " + filePath);
            }
            return image;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void setupPanel() {
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (image != null) {
                    g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
                }
            }
        };
        panel.setLayout(new BorderLayout());
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                loadNextImage();
            }
        });
    }

    @Override
    public void init() {
        panel.repaint();
    }

    @Override
    public void handleInput() {}

    @Override
    public void update() {}

    @Override
    public void render() {}

    @Override
    public void cleanup() {}
}
