import java.util.Random;
import java.util.Scanner;

public class NumberGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int minRange = 1;
        int maxRange = 100;
        int attempts = 5; // You can change the number of attempts
        int score = 0;
        boolean playAgain = true;

        System.out.println("Welcome to the Number Guessing Game!");
        
        while (playAgain) {
            int randomNumber = random.nextInt(maxRange - minRange + 1) + minRange;
            int remainingAttempts = attempts;

            System.out.println("I have selected a random number between " + minRange + " and " + maxRange + ". Try to guess it!");

            while (remainingAttempts > 0) {
                System.out.println("Attempts remaining: " + remainingAttempts);
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();

                if (userGuess < minRange || userGuess > maxRange) {
                    System.out.println("Please guess within the specified range.");
                } else if (userGuess == randomNumber) {
                    System.out.println("Congratulations! You guessed the correct number: " + randomNumber);
                    score++;
                    break;
                } else if (userGuess < randomNumber) {
                    System.out.println("Try a higher number.");
                } else {
                    System.out.println("Try a lower number.");
                }

                remainingAttempts--;
            }

            if (remainingAttempts == 0) {
                System.out.println("Out of attempts. The correct number was: " + randomNumber);
            }

            System.out.print("Do you want to play again? (yes/no): ");
            String playAgainResponse = scanner.next().toLowerCase();
            if (!playAgainResponse.equals("yes")) {
                playAgain = false;
            }
        }

        System.out.println("Game over! Your score is: " + score);
        System.out.println("Thanks for playing!");
    }
}
 