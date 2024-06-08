import javax.swing.*;
import java.awt.*;

public class Game extends JFrame implements Runnable {
    private GameState currentState;
    private boolean isRunning;

    public Game() {
        this.isRunning = true;
        this.currentState = new MainMenuState(this); // 初始狀態為主菜單
        setTitle("Program Design II");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void changeState(GameState newState) {
        this.currentState = newState;
    }

    @Override
    public void run() {
        while (isRunning) {
            currentState.handleInput();
            currentState.update();
            repaint();
            try {
                Thread.sleep(16); // 約 60 FPS
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        currentState.render(g);
    }

    public void exit() {
        isRunning = false;
        dispose();
    }

    public static void main(String[] args) {
        Game game = new Game();
        new Thread(game).start();
    }
}