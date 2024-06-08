import java.util.List;

public class Question {
    public enum QuestionType {
        SINGLE_CHOICE, MULTIPLE_CHOICE
    }

    private QuestionType type;
    private List<String> options;  // Renamed from answers for clarity

    public Question(QuestionType type, List<String> options) {
        this.type = type;
        this.options = options;
    }

    public QuestionType getType() {
        return type;
    }

    public List<String> getOptions() {
        return options;
    }

    public boolean isValidAnswer(String answer) {
        return options.contains(answer);
    }

    public boolean isValidAnswer(List<String> answers) {
        return answers.stream().allMatch(options::contains);
    }
}
