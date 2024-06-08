import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StoryGame extends JFrame implements MouseListener {
    private JLabel imageLabel;
    private ArrayList<ImageIcon> preludeImages;
    private ArrayList<ImageIcon> chapterImages;
    private int currentIndex;
    private boolean inPrelude;
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
        chapterImages = new ArrayList<>();
        loadImages(preludeImages, "PD2前情提要", 24);
        loadImages(chapterImages, "PD2第一章", 196);

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
        inPrelude = true; // 初始狀態為前情提要
        updateImage();
    }

    private void loadImages(ArrayList<ImageIcon> images, String folderName, int count) {
        for (int i = 1; i <= count; i++) {
            images.add(new ImageIcon(getClass().getResource("/" + folderName + "/" + i + ".png"))); // 使用相對路徑
        }
    }

    private void updateImage() {
        ArrayList<ImageIcon> currentImages = inPrelude ? preludeImages : chapterImages;
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

    @Override
    public void mouseClicked(MouseEvent e) {
        // 每次點擊左鍵時切換到下一張圖片
        if (e.getButton() == MouseEvent.BUTTON1) {
            currentIndex++;
            ArrayList<ImageIcon> currentImages = inPrelude ? preludeImages : chapterImages;
            if (currentIndex < currentImages.size()) {
                updateImage();
            } else {
                if (inPrelude) {
                    // 切換到第一章
                    inPrelude = false;
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
