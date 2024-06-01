
// import javax.swing.*;
import java.util.Random;
import java.util.Scanner;

public class numberGame {
    public static void main(String[] args) {
        Random rand = new Random();
        Scanner input = new Scanner(System.in);

        // initializing variables
        int totalRounds = 3;
        int totalAttempts = 7;
        int i = 1, score = 0;

        System.out.println("*****************************************************************");
        System.out.println("                  Welcome to Number Guessing Game                ");
        System.out.println("*****************************************************************");
        System.out.println();
        System.out.println("Guess computer generated random no. and release your stress :)");
        System.out.println();
        System.out.println();
        System.out.println("Important points");
        System.out.println("1) Computer will generate a random no between 1 to 100 and you have to guess it in 7 attempts");
        System.out.println("2) There will be 3 rounds . Score will be evaluated on the basis of Round Won !!");
        System.out.println();
        System.out.println("Ready to play !!! (y/n)");
        char c = input.next().charAt(0);
        System.out.println();

        if (c == 'y' || c == 'Y') {

            while (i <= totalRounds) {
                System.out.println("                 Round " + i);
                int attempts = 0, guessedNo;
                int randomNo = rand.nextInt(100) + 1;
                System.out.println("Guess No. between 1 to 100");
                do {
                    attempts++;
                    if (attempts == 6) {
                        System.out.println("Be careful !! Only 2 attemps lefts");
                    }
                    guessedNo = input.nextInt();
                    if (guessedNo < randomNo) {
                        System.out.println("its low , Guess higher one");
                    } else if (guessedNo > randomNo) {
                        System.out.println("its high , Guess lower one");
                    } else {
                        System.out.println("Awesome !!! You Guessed it right .");
                        score++;
                        break;
                    }
                } while (attempts <= totalAttempts);

                if (attempts > totalAttempts) {
                    System.out.println("Oops !!! Your attempts are over.");
                    System.out.println("No. was " + randomNo);
                }
                System.out.println();
                System.out.println();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i++;
            }
            System.out.println("Your score is  " + score);

            switch (score) {
                case 0:
                    System.out.println("                   Better luck next time !!!");
                    break;
                case 1:
                    System.out.println("                   Good performance !!!");
                    break;
                case 2:
                    System.out.println("                   Great performance !!!");
                    break;
                case 3:
                    System.out.println("                   Outstanding performance !!!");
                    break;
                default:
                    break;
            }

            System.out.println();
            System.out.println();
            System.out.println("                Hope you enjoyed this game !");
        } else {
            System.out.println("Hope to see you next time :)");
        }
        input.close();

    }

}