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

    public LevelPanel(GameStateManager gsm, Game game, int chapter, int level) {
        super(gsm);
        panel.setLayout(new BorderLayout());

        try {
            questions = QuizLoader.loadQuestions("../../../asset/question/question_" + chapter + ".json");
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        Collections.shuffle(questions);

        questionLabel = new JLabel();
        questionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(questionLabel, BorderLayout.CENTER);

        JPanel optionsPanel = new JPanel(new GridLayout(1, 4));
        optionButtons = new JButton[4];
        for (int i = 0; i < 4; i++) {
            optionButtons[i] = new JButton(Character.toString((char) ('A' + i)));
            optionButtons[i].addActionListener(new OptionButtonListener(i));
            optionsPanel.add(optionButtons[i]);
        }
        panel.add(optionsPanel, BorderLayout.SOUTH);

        displayNextQuestion();
    }

    private void displayNextQuestion() {
        if (currentQuestionIndex < questions.size()) {
            Question question = questions.get(currentQuestionIndex);
            questionLabel.setText("<html>" + question.question.replace("\n", "<br>") + "</html>");
            for (int i = 0; i < question.options.size(); i++) {
                optionButtons[i].setText(question.options.get(i));
            }
        } else {
            if (correctCount >= 3) {
                JOptionPane.showMessageDialog(panel, "You Win!");
            } else {
                JOptionPane.showMessageDialog(panel, "You Lose!");
            }
            gsm.setState(GameStateManager.PLAY);
        }
    }

    @Override
    public void init() {

    }

    @Override
    public void handleInput() {
    }

    @Override
    public void update() {
    }

    @Override
    public void render() {
    }

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
            Question question = questions.get(currentQuestionIndex);
            char correctAnswer = question.answer.charAt(0);
            char selectedAnswer = (char) ('A' + optionIndex);

            if (selectedAnswer == correctAnswer) {
                correctCount++;
            } else {
                wrongCount++;
            }

            currentQuestionIndex++;
            displayNextQuestion();
        }
    }
}
