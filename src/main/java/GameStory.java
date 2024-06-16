import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;


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
            // 解析JSON文件
            List<Chapter> chapters = mapper.readValue(new File("chapters.json"),
                    mapper.getTypeFactory().constructCollectionType(List.class, Chapter.class));

            // 命令行參數得到章節索引
            int chapterIndex = Integer.parseInt(args[0]);

            if (chapterIndex >= 0 && chapterIndex < chapters.size()) {
                playChapter(chapters.get(chapterIndex));
            }
        } catch (IOException | NumberFormatException e) {
            
        }
    }
            
        
    

    public static void playChapter(Chapter chapter) {
        // 先載入整個章節的圖片
        loadImages(chapter);

        for (int i = 1; i <= chapter.getCount(); i++) {
            
            // 如果是 PD2-s1 的第 46 張圖片
            if (chapter.getPath().equals("assets/chapter/PD2-s1") && i == 46) {
                Integer nextIndex = handleBranching(i, 47, 51, 53, 57, 58);
                i = nextIndex;
            } else if (chapter.getPath().equals("assets/chapter/PD2-s1") && i == 67) {
                Integer nextIndex = handleBranching(i, 68, 78, 80, 80, 67);
                i = nextIndex;
            } else {
                i++; // 繼續到下一張圖片
            }
        }
    }    


    private static int handleBranching(int currentIndex, int buttonAStart, int buttonAEnd, int buttonBStart, int buttonBEnd, int resumeIndex) {
        // 假設按鈕 A 和按鈕 B 的範圍
        int buttonAX1 = 50;
        int buttonAY1 = 50;
        int buttonAX2 = 150;
        int buttonAY2 = 150;

        int buttonBX1 = 200;
        int buttonBY1 = 50;
        int buttonBX2 = 300;
        int buttonBY2 = 150;

        // 模擬使用者點擊，實際應該由使用者輸入獲得 clickX 和 clickY
        int clickX = 100; // 模擬的X座標
        int clickY = 100; // 模擬的Y座標

        if (clickX >= buttonAX1 && clickX <= buttonAX2 && clickY >= buttonAY1 && clickY <= buttonAY2) {
            for (int j = buttonAStart; j <= buttonAEnd; j++) {
                String filePath = "assets/chapter/PD2-s1/" + j + ".png";
                System.out.println("Playing: " + filePath);
            }
            return resumeIndex; // 返回繼續播放的索引
        } else if (clickX >= buttonBX1 && clickX <= buttonBX2 && clickY >= buttonBY1 && clickY <= buttonBY2) {
            for (int j = buttonBStart; j <= buttonBEnd; j++) {
                String filePath = "assets/chapter/PD2-s1/" + j + ".png";
                System.out.println("Playing: " + filePath);
            }
            return resumeIndex; // 返回繼續播放的索引
        }

        return currentIndex; // 如果點擊位置不在任何按鈕的範圍內，繼續播放當前圖片
    }

    
    
    private static void loadImages(Chapter chapter) {
        // 載入章節圖片
        String basePath = chapter.getPath();
        int count = chapter.getCount();
        String type = chapter.getType();

        for (int i = 1; i <= count; i++) {
            String filePath = basePath + "/" + i + "." + type;
            System.out.println("Loading: " + filePath);
            loadImage(filePath); 
        }
    }

    private static BufferedImage loadImage(String filePath) {
        try {
            BufferedImage image = ImageIO.read(new File(filePath));
            return image;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
