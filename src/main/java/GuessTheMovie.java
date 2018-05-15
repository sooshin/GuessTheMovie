import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

public class GuessTheMovie {

    public static void main (String [] args) {
        File file = new File("movies.txt");
        ArrayList<String> moviesList = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                moviesList.add(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find that file");
        }

        // randomly picks a movie title
        int numOfMovies = moviesList.size();
        Random generator = new Random();
        int randomInteger = generator.nextInt(numOfMovies);  // 0 ... (numOfMovies - 1)
        String randomMovie = moviesList.get(randomInteger);
        System.out.println("answer: " + randomMovie);////////////

        // Shows how many letters the movie title is made up of.
        Game game = new Game(randomMovie);
        String underscores = game.displayUnderscores(randomMovie);
        System.out.println("You are guessing:" + underscores);

        System.out.println("You have guessed (0) wrong letters: ");
        StringBuilder wrongLetters = new StringBuilder("");
        int wrongNum = 0;
        boolean hasWon = false;
        while (wrongNum < 10 && !hasWon) {
            System.out.println("Guess a letter: ");
            Scanner in = new Scanner(System.in); //
            try {
                String guess = in.nextLine();
                if (guess.length() != 1) {
                    System.out.println("Enter only one letter.");
                } else if (!Character.isLetter(guess.charAt(0))) {
                    System.out.println("Enter a letter instead of a number.");
                }
                boolean containsLetter = randomMovie.contains(guess);
                if (containsLetter) {
                    underscores = game.revealLetters(underscores, randomMovie, guess);
                    if (underscores.equals(randomMovie)) {
                        hasWon = true;
                    } else {
                        System.out.println("You are guessing: " + underscores);
                    }
                } else {
                    wrongNum++;
                    wrongLetters.append(" ").append(guess);
                    System.out.println("You have guessed (" + wrongNum + ") wrong letters:" + wrongLetters);
                }
            } catch (NoSuchElementException e) {
                System.out.println("No line was found");
            } catch (IllegalStateException e) {
                System.out.println("The scanner is closed");
            }
        }

        if (wrongNum < 10) {
            System.out.println("You win!");
            System.out.println("You have guessed '" + randomMovie + "' correctly.");
        } else {
            System.out.println("GAME OVER ... You lose!");
            System.out.println("The movie title was: " + randomMovie);
        }
    }
}
