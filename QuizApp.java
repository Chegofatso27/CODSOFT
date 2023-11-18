import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class QuizQuestion {
    String question;
    List<String> options;
    int correctOptionIndex;

    public QuizQuestion(String question, List<String> options, int correctOptionIndex) {
        this.question = question;
        this.options = options;
        this.correctOptionIndex = correctOptionIndex;
    }
}

class Quiz {
    List<QuizQuestion> questions;
    int currentQuestionIndex;
    int score;
    Timer timer;

    public Quiz(List<QuizQuestion> questions) {
        this.questions = questions;
        this.currentQuestionIndex = 0;
        this.score = 0;
        this.timer = new Timer();
    }

    public void startQuiz() {
        askQuestion();
    }

    private void askQuestion() {
    if (currentQuestionIndex < questions.size()) {
        QuizQuestion currentQuestion = questions.get(currentQuestionIndex);
        displayQuestion(currentQuestion);

        // Cancel the previous timer if any
        timer.cancel();
        timer.purge();

        // Create a new timer for the current question
        timer = new Timer();

        // Set a timer for each question
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Time's up! Moving to the next question.");
                processAnswer(-1); // No answer provided within the time limit
            }
        }, 15000); // 15 seconds to answer

        getUserAnswer();
    } else {
        endQuiz();
    }
}


    private void displayQuestion(QuizQuestion question) {
        System.out.println(question.question);
        for (int i = 0; i < question.options.size(); i++) {
            System.out.println((i + 1) + ". " + question.options.get(i));
        }
    }

    private void getUserAnswer() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Select an option: ");
        int userAnswer = scanner.nextInt();
        processAnswer(userAnswer - 1); // Adjusting for 0-based index
    }

    private void processAnswer(int userAnswer) {
        QuizQuestion currentQuestion = questions.get(currentQuestionIndex);
        if (userAnswer == currentQuestion.correctOptionIndex) {
            System.out.println("Correct!");
            score++;
        } else {
            System.out.println("Incorrect. The correct answer was: " +
                    currentQuestion.options.get(currentQuestion.correctOptionIndex));
        }

        // Cancel the timer
        timer.cancel();
        timer.purge();

        currentQuestionIndex++;
        askQuestion();
    }

    private void endQuiz() {
        System.out.println("Quiz finished!");
        System.out.println("Your final score: " + score + "/" + questions.size());
    }
}

public class QuizApp {
    public static void main(String[] args) {
        // Create quiz questions
        List<QuizQuestion> questions = new ArrayList<>();
        questions.add(new QuizQuestion("What is the capital of France?", Arrays.asList("Berlin", "Paris", "Madrid"), 1));
        questions.add(new QuizQuestion("Which planet is known as the Red Planet?", Arrays.asList("Mars", "Venus", "Jupiter"), 0));

        // Create and start the quiz
        Quiz quiz = new Quiz(questions);
        quiz.startQuiz();
    }
}
