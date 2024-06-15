import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class LevelPanel extends GameState {

    private int currentQuestionIndex = 0;
    private int correctCount = 0;
    private int wrongCount = 0;
    private List<Question> questions;
    private JLabel questionLabel;
    private JButton[] optionButtons;
    private int level;
    private JLabel[] playerHearts;
    private JLabel enemyHeartLabel;
    private JLabel playerLabel;
    private JLabel enemyLabel;

    public LevelPanel(GameStateManager gsm, Game game, int chapter, int level) {
        super(gsm);
        this.level = level;
        panel.setLayout(new BorderLayout());

        try {
            String resourcePath = "assets/question/question_" + chapter + ".json";
            questions = QuizLoader.loadQuestions(resourcePath);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(panel, "Question file not found! Using default questions.");
            questions = QuizLoader.getDefaultQuestions(); // 使用預設問題集
        }

        filterQuestionsByLevel();
        Collections.shuffle(questions);

        // 加載背景圖片
        JLabel bgLabel = new JLabel(loadImageIcon("assets/image/bg/" + chapter + "_" + level + ".png"));
        bgLabel.setLayout(new BorderLayout());
        panel.add(bgLabel);

        // 設置問題面板
        JPanel questionPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(new Color(255, 255, 255, 200)); // 設置透明度為200的白色背景
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20); // 繪製圓角矩形背景
            }
        };
        questionPanel.setLayout(new BorderLayout());
        questionPanel.setOpaque(false);
        questionPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // 設置邊距

        questionLabel = new JLabel();
        questionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        questionLabel.setVerticalAlignment(SwingConstants.CENTER);
        questionLabel.setOpaque(false);
        questionLabel.setPreferredSize(new Dimension(400, 100)); // 設置最小和最大寬度為400像素，最小高度為100像素
        questionLabel.setMaximumSize(new Dimension(400, Integer.MAX_VALUE));
        questionLabel.setMinimumSize(new Dimension(400, 100));

        questionPanel.add(questionLabel, BorderLayout.CENTER);

        // 將問題面板放在一個額外的容器中，使其居中對齊
        JPanel questionContainer = new JPanel(new GridBagLayout());
        questionContainer.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;
        questionContainer.add(questionPanel, gbc);
        bgLabel.add(questionContainer, BorderLayout.CENTER);

        JPanel optionsPanel = new JPanel(new GridLayout(1, 4));
        optionButtons = new JButton[4];
        for (int i = 0; i < 4; i++) {
            optionButtons[i] = new JButton(Character.toString((char) ('A' + i)));
            optionButtons[i].addActionListener(new OptionButtonListener(i));
            optionsPanel.add(optionButtons[i]);
        }
        bgLabel.add(optionsPanel, BorderLayout.SOUTH);

        // 加載玩家和怪物圖片
        playerLabel = new JLabel(loadImageIcon("assets/image/player.png"));
        enemyLabel = new JLabel(loadImageIcon("assets/image/enemies/" + chapter + "_" + level + ".png"));
        bgLabel.add(playerLabel, BorderLayout.WEST);
        bgLabel.add(enemyLabel, BorderLayout.EAST);

        // 加載玩家和怪物的愛心圖示
        JPanel playerHeartsPanel = new JPanel(new GridLayout(1, getLoseCondition()));
        playerHearts = new JLabel[getLoseCondition()];
        for (int i = 0; i < getLoseCondition(); i++) {
            playerHearts[i] = new JLabel(loadImageIcon("assets/image/heart.png"));
            playerHeartsPanel.add(playerHearts[i]);
        }
        bgLabel.add(playerHeartsPanel, BorderLayout.NORTH);

        JPanel enemyHeartsPanel = new JPanel(new GridLayout(1, 1));
        enemyHeartLabel = new JLabel("X " + getWinCondition(), loadImageIcon("assets/image/heart.png"), JLabel.LEFT);
        enemyHeartsPanel.add(enemyHeartLabel);
        bgLabel.add(enemyHeartsPanel, BorderLayout.NORTH);

        displayNextQuestion();
    }

    private void filterQuestionsByLevel() {
        int startIndex = 0;
        int endIndex = 0;
        switch (level) {
            case 1:
                startIndex = 0;
                endIndex = 5;
                break;
            case 2:
                startIndex = 5;
                endIndex = 10;
                break;
            case 3:
                startIndex = 10;
                endIndex = 15;
                break;
            case 4:
                startIndex = 15;
                endIndex = 20;
                break;
            case 5:
                startIndex = 0;
                endIndex = questions.size();
                break;
        }
        questions = questions.subList(startIndex, Math.min(endIndex, questions.size()));
    }

    private void displayNextQuestion() {
        if (questions.isEmpty()) {
            checkGameOver();
            return;
        }

        Question question = questions.get(currentQuestionIndex);
        questionLabel.setText("<html>" + question.question.replace("\n", "<br>") + "</html>");
        questionLabel.revalidate(); // 重新驗證佈局
        questionLabel.repaint(); // 重新繪製
    }

    private void checkGameOver() {
        if (correctCount >= getWinCondition()) {
            JOptionPane.showMessageDialog(panel, "You Win!");
        } else {
            JOptionPane.showMessageDialog(panel, "You Lose!");
        }
        gsm.setState(GameStateManager.PLAY);
    }

    private int getWinCondition() {
        return level == 5 ? 15 : 5;
    }

    private int getLoseCondition() {
        return level == 5 ? 5 : 3;
    }

    private ImageIcon loadImageIcon(String path) {
        java.net.URL imgURL = getClass().getClassLoader().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Image not found: " + path);
            return null;
        }
    }

    @Override
    public void init() {}

    @Override
    public void handleInput() {}

    @Override
    public void update() {}

    @Override
    public void render() {}

    @Override
    public void cleanup() {
        // 清理資源
    }

    private class OptionButtonListener implements ActionListener {
        private int optionIndex;

        public OptionButtonListener(int optionIndex) {
            this.optionIndex = optionIndex;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (questions.isEmpty()) {
                checkGameOver();
                return;
            }

            Question question = questions.get(currentQuestionIndex);
            char correctAnswer = question.answer.charAt(0);
            char selectedAnswer = (char) ('A' + optionIndex);

            if (selectedAnswer == correctAnswer) {
                correctCount++;
                questions.remove(currentQuestionIndex);
            } else {
                wrongCount++;
                playerHearts[wrongCount - 1].setIcon(loadImageIcon("assets/image/grey_heart.png"));
            }

            if (correctCount >= getWinCondition() || wrongCount >= getLoseCondition()) {
                checkGameOver();
            } else {
                currentQuestionIndex = currentQuestionIndex % questions.size();
                displayNextQuestion();
            }
        }
    }
}
