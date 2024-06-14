import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;

class Question {
    public String question;
    public List<String> options;
    public String answer;
    public String explanation;
}

public class QuizLoader {
    private static String questionPath;

    public static List<Question> loadQuestions(String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        questionPath = filePath;
        return mapper.readValue(new File(filePath), mapper.getTypeFactory().constructCollectionType(List.class, Question.class));
    }

    public static void main(String[] args) {
        try {
            List<Question> questions = loadQuestions(questionPath);
            for (Question q : questions) {
                System.out.println("Question: " + q.question);
                System.out.println("Options: " + q.options);
                System.out.println("Answer: " + q.answer);
                System.out.println("Explanation: " + q.explanation);
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
