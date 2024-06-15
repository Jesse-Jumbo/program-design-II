import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

class Question {
    public String question;
    public String answer;
    public String explanation;

    public Question() {}

    public Question(String question, String answer, String explanation) {
        this.question = question;
        this.answer = answer;
        this.explanation = explanation;
    }
}

public class QuizLoader {

    private static String questionPath;
    public static List<Question> loadQuestions(String resourcePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        questionPath = resourcePath;
        try (InputStream inputStream = QuizLoader.class.getClassLoader().getResourceAsStream(resourcePath)) {
            if (inputStream == null) {
                throw new IOException("Resource not found: " + resourcePath);
            }
            return mapper.readValue(inputStream, mapper.getTypeFactory().constructCollectionType(List.class, Question.class));
        }
    }

    public static List<Question> getDefaultQuestions() {
        // 返回預設問題集
        Question q1 = new Question("Default Question 1", "A", "Explanation 1");
        Question q2 = new Question("Default Question 2", "B", "Explanation 2");
        Question q3 = new Question("Default Question 3", "C", "Explanation 3");
        Question q4 = new Question("Default Question 4", "D", "Explanation 4");
        return Arrays.asList(q1, q2, q3, q4);
    }

    public static void main(String[] args) {
        try {
            List<Question> questions = loadQuestions(questionPath);
            for (Question q : questions) {
                System.out.println("Question: " + q.question);
                System.out.println("Answer: " + q.answer);
                System.out.println("Explanation: " + q.explanation);
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
