import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MusicPlayer {

    private AdvancedPlayer player;
    private Thread playerThread;
    private boolean loop;

    public void play(String soundFilePath, boolean loop) {
        stop(); // 停止當前音樂
        this.loop = loop;
        playerThread = new Thread(() -> {
            do {
                try (InputStream soundFile = getClass().getClassLoader().getResourceAsStream(soundFilePath);
                     BufferedInputStream bis = new BufferedInputStream(soundFile)) {
                    if (soundFile == null) {
                        System.err.println("Sound file not found: " + soundFilePath);
                        return;
                    }
                    player = new AdvancedPlayer(bis);
                    player.play();
                } catch (JavaLayerException | IOException e) {
                    e.printStackTrace();
                }
            } while (this.loop);
        });
        playerThread.start();
    }

    public void stop() {
        this.loop = false;
        if (player != null) {
            player.close();
        }
        if (playerThread != null) {
            playerThread.interrupt();
        }
    }

    public static void main(String[] args) {
        MusicPlayer player = new MusicPlayer();
        player.play("assets/sound/battle.mp3", true); // 確保使用 .mp3 文件
    }
}
