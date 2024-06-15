import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.net.URL;

public class StoryGame extends JFrame implements MouseListener {
    private JLabel imageLabel;
    private ArrayList<ImageIcon> preludeImages;
    private ArrayList<ImageIcon> chapter1Images;
    private ArrayList<ImageIcon> chapter2Images;
    private int currentIndex;
    private int currentChapter;
    private Map<Integer, ImageIcon> scaledImageCache;

    public StoryGame() {
        // 初始化 JFrame
        setTitle("Program Design 2");
        setSize(960, 540); // 設置初始窗口大小為較小尺寸
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true); // 允許調整窗口大小

        // 初始化圖片列表
        preludeImages = new ArrayList<>();
        chapter1Images = new ArrayList<>();
        chapter2Images = new ArrayList<>();
        loadImages(preludeImages, "assets/chapter/PD2-previou", 24, "png");
        loadImages(chapter1Images, "assets/chapter/PD2-s1", 196, "png");
        loadImages(chapter2Images, "assets/chapter/PD2-s2", 224, "jpg");

        // 初始化 JLabel 並顯示第一張圖片
        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setVerticalAlignment(JLabel.CENTER);
        add(imageLabel);

        // 初始化圖片緩存
        scaledImageCache = new HashMap<>();

        // 註冊滑鼠點擊事件
        addMouseListener(this);

        currentIndex = 0;
        currentChapter = 0; // 初始狀態為前情提要
        updateImage();
    }

    private void loadImages(ArrayList<ImageIcon> images, String folderName, int count, String extension) {
        for (int i = 1; i <= count; i++) {
            String path = folderName + "/" + i + "." + extension;
            URL imgURL = getClass().getClassLoader().getResource(path);
            if (imgURL != null) {
                images.add(new ImageIcon(imgURL));
            } else {
                System.err.println("Couldn't find file: " + path);
            }
        }
    }

    private void updateImage() {
        ArrayList<ImageIcon> currentImages = getCurrentImages();
        if (scaledImageCache.containsKey(currentIndex)) {
            // 如果圖片已在緩存中，直接使用緩存的圖片
            imageLabel.setIcon(scaledImageCache.get(currentIndex));
        } else {
            // 如果圖片不在緩存中，進行縮放並添加到緩存中
            ImageIcon originalIcon = currentImages.get(currentIndex);
            Image originalImage = originalIcon.getImage();
            Image scaledImage = originalImage.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            scaledImageCache.put(currentIndex, scaledIcon);
            imageLabel.setIcon(scaledIcon);
        }
    }

    private ArrayList<ImageIcon> getCurrentImages() {
        switch (currentChapter) {
            case 0: return preludeImages;
            case 1: return chapter1Images;
            case 2: return chapter2Images;
            default: return new ArrayList<>();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // 每次點擊左鍵時切換到下一張圖片
        if (e.getButton() == MouseEvent.BUTTON1) {
            currentIndex++;
            ArrayList<ImageIcon> currentImages = getCurrentImages();
            if (currentIndex < currentImages.size()) {
                updateImage();
            } else {
                if (currentChapter == 0) {
                    // 切換到第一章
                    currentChapter = 1;
                    currentIndex = 0;
                    scaledImageCache.clear(); // 清空緩存
                    updateImage();
                } else if (currentChapter == 1) {
                    // 切換到第二章
                    currentChapter = 2;
                    currentIndex = 0;
                    scaledImageCache.clear(); // 清空緩存
                    updateImage();
                } else {
                    JOptionPane.showMessageDialog(this, "The End");
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StoryGame game = new StoryGame();
            game.setVisible(true);
        });
    }
}

