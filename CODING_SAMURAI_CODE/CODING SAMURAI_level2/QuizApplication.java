import java.util.ArrayList;
import java.util.Scanner;

class Question {
    private String questionText;
    private String[] options;
    private char correctAnswer;

    public Question(String questionText, String[] options, char correctAnswer) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = Character.toUpperCase(correctAnswer);
    }

    public void displayQuestion() {
        System.out.println(questionText);
        char optionLabel = 'A';
        for (String option : options) {
            System.out.println(optionLabel + ". " + option);
            optionLabel++;
        }
    }

    public boolean isCorrect(char userAnswer) {
        return Character.toUpperCase(userAnswer) == correctAnswer;
    }

    public char getCorrectAnswer() {
        return correctAnswer;
    }

    public String getCorrectOptionText() {
        return options[correctAnswer - 'A'];
    }
}

public class QuizApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Question> questions = new ArrayList<>();

        // Sample questions
        questions.add(new Question("What is the capital of France?",
                new String[]{"Berlin", "Madrid", "Paris", "London"}, 'C'));
        questions.add(new Question("Which language is used for Android development?",
                new String[]{"Kotlin", "Swift", "C#", "Python"}, 'A'));
        questions.add(new Question("Which keyword is used to create a class in Java?",
                new String[]{"function", "class", "void", "public"}, 'B'));
        questions.add(new Question("Who developed the theory of relativity?",
                new String[]{"Isaac Newton", "Albert Einstein", "Galileo", "Stephen Hawking"}, 'B'));
        questions.add(new Question("What does HTML stand for?",
                new String[]{"HyperText Markup Language", "Home Tool Markup Language", "Hyperlinks and Text Markup Language", "None"}, 'A'));

        int score = 0;
        char[] userAnswers = new char[questions.size()];

        System.out.println(" Welcome to the Java Quiz!");
        System.out.println("Answer each question by typing A, B, C, or D.\n");

        // Quiz Loop
        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            System.out.println("Q" + (i + 1) + ":");
            q.displayQuestion();
            System.out.print("Your answer: ");
            char answer = scanner.next().charAt(0);
            userAnswers[i] = answer;

            if (q.isCorrect(answer)) {
                score++;
                System.out.println("Correct!\n");
            } else {
                System.out.println(" Incorrect.\n");
            }
        }

        // Result
        System.out.println("\n🎉 Quiz Completed!");
        System.out.println("Your Score: " + score + "/" + questions.size());

        // Show correct answers
        System.out.println("\n📋 Review Answers:");
        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            System.out.println("Q" + (i + 1) + ": " + (q.isCorrect(userAnswers[i]) ? "correct" : "wrong") +
                    " Correct Answer: " + q.getCorrectAnswer() + ". " + q.getCorrectOptionText());
        }

        scanner.close();
    }
}
