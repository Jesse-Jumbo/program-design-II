import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Game extends JFrame {
    private GameState currentState;
    private GameStateManager gsm;

    public Game() {
        initUI();
    }

    private void initUI() {
        gsm = new GameStateManager(this); // 將 Game 傳遞給 GameStateManager
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void start() {
        changeState(gsm.getInitialState());
    }

    public void changeState(GameState newState) {
        currentState = newState;
        setContentPane(currentState.getPanel());
        revalidate();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Game game = new Game();
            game.start();
        });
    }
}
