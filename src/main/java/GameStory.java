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

public class GameStory {

    public static class Chapter {
        private String path;
        private int count;
        private String type;

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            return;
        }

        ObjectMapper mapper = new ObjectMapper();
        try {
            InputStream inputStream = GameStory.class.getClassLoader().getResourceAsStream("assets/chapter/chapters.json");
            List<Chapter> chapters = mapper.readValue(inputStream,
                    mapper.getTypeFactory().constructCollectionType(List.class, Chapter.class));

            int chapterIndex = Integer.parseInt(args[0]);

            if (chapterIndex >= 0 && chapterIndex < chapters.size()) {
                playChapter(chapters.get(chapterIndex));
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public static void playChapter(Chapter chapter) {
        JFrame frame = new JFrame("Game Story");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        JPanel panel = new JPanel() {
            private BufferedImage image;
            private int currentIndex = 1;

            {
                loadNextImage();
                addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        currentIndex++;
                        if (currentIndex <= chapter.getCount()) {
                            loadNextImage();
                        } else {
                            frame.dispose(); // 关闭窗口并结束故事
                        }
                        repaint();
                    }
                });
            }

            private void loadNextImage() {
                String filePath = chapter.getPath() + "/" + currentIndex + "." + chapter.getType();
                image = loadImage(filePath);
                if (image == null) {
                    System.err.println("Failed to load image: " + filePath);
                }
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (image != null) {
                    g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };

        frame.add(panel);
        frame.setVisible(true);
    }

    private static BufferedImage loadImage(String filePath) {
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
}
