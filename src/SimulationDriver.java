import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SimulationDriver {
    public static void main(String[] args) {
        // 1. Create questions
        List<String> mcOptions = Arrays.asList("A", "B", "C", "D");
        List<String> scOptions = Arrays.asList("Right", "Wrong");

        Question multipleChoiceQuestion = new Question(Question.QuestionType.MULTIPLE_CHOICE, mcOptions);
        Question singleChoiceQuestion = new Question(Question.QuestionType.SINGLE_CHOICE, scOptions);

        // 2. Configure the VotingService
        VotingService votingService = new VotingService();
        votingService.configureQuestion(multipleChoiceQuestion);
        votingService.configureQuestion(singleChoiceQuestion);

        // 3. Generate students and their answers
        Random random = new Random();
        List<Student> students = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            String studentId = "Student" + i;
            students.add(new Student(studentId));

            // Randomly decide whether to answer single-choice or multiple-choice
            if (random.nextBoolean()) {
                // Answer the multiple-choice question
                List<String> studentAnswers = new ArrayList<>();
                for (String option : mcOptions) {
                    if (random.nextBoolean()) {
                        studentAnswers.add(option);
                    }
                }
                votingService.submitAnswer(studentId, studentAnswers);
            } else {
                // Answer the single-choice question
                String studentAnswer = random.nextBoolean() ? "Right" : "Wrong";
                votingService.submitAnswer(studentId, studentAnswer);
            }
        }

        // 4. Display the results
        votingService.displayResults();
    }
}
