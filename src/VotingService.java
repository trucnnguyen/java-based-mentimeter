import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VotingService {
    private Map<String, List<String>> multipleChoiceSubmissions = new HashMap<>();
    private Map<String, String> singleChoiceSubmissions = new HashMap<>();
    private Question multipleChoiceQuestion;
    private Question singleChoiceQuestion;

    public void configureQuestion(Question question) {
        if (question.getType() == Question.QuestionType.MULTIPLE_CHOICE) {
            this.multipleChoiceQuestion = question;
        } else if (question.getType() == Question.QuestionType.SINGLE_CHOICE) {
            this.singleChoiceQuestion = question;
        } else {
            throw new IllegalArgumentException("Unsupported question type.");
        }
    }

    public void submitAnswer(String studentId, List<String> answers) {
        if (multipleChoiceQuestion != null && multipleChoiceQuestion.isValidAnswer(answers)) {
            multipleChoiceSubmissions.put(studentId, answers);
        } else {
            throw new IllegalArgumentException("Invalid multiple choice answers.");
        }
    }

    public void submitAnswer(String studentId, String answer) {
        if (singleChoiceQuestion != null && singleChoiceQuestion.isValidAnswer(answer)) {
            singleChoiceSubmissions.put(studentId, answer);
        } else {
            throw new IllegalArgumentException("Invalid single choice answer.");
        }
    }

    public void displayResults() {
        displayMultipleChoiceResults();
        displaySingleChoiceResults();
    }

    private void displayMultipleChoiceResults() {
        if (multipleChoiceQuestion == null) return;

        Map<String, Integer> resultCount = new HashMap<>();
        for (String option : multipleChoiceQuestion.getOptions()) {
            resultCount.put(option, 0);
        }

        for (List<String> answers : multipleChoiceSubmissions.values()) {
            for (String answer : answers) {
                resultCount.put(answer, resultCount.get(answer) + 1);
            }
        }

        System.out.println("Multiple Choice Results:");
        for (Map.Entry<String, Integer> entry : resultCount.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
        System.out.println("Total Multiple Choice Responses: " + multipleChoiceSubmissions.size());
    }

    private void displaySingleChoiceResults() {
        if (singleChoiceQuestion == null) return;

        int rightCount = 0;
        int wrongCount = 0;

        for (String answer : singleChoiceSubmissions.values()) {
            if (answer.equals("Right")) {
                rightCount++;
            } else {
                wrongCount++;
            }
        }

        System.out.println("\nSingle Choice Results:");
        System.out.println("Right : " + rightCount);
        System.out.println("Wrong : " + wrongCount);
        System.out.println("Total Single Choice Responses: " + (rightCount + wrongCount));
    }
}
