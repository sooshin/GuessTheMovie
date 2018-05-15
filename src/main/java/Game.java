public class Game {
    private String movieName;

    Game(String movieName) {
        this.movieName = movieName;
    }

    /**
     * Replace each letter with an underscore, "_".
     * Shows how many letters the movie title is made up of.
     */
    public String displayUnderscores(String movieName) {
        return movieName.replaceAll("[a-zA-Z]", "_");
    }

    /**
     * Used when String movieName contains a letter.
     * Reveals the correct position in the word.
     *
     * @param underscore the movie title that is replaced by underscores "_"
     * @param movieName a movie title the computer randomly picks
     * @param letter one letter the user guess
     */
    public String revealLetters(String underscore, String movieName, String letter) {
        StringBuilder builder = new StringBuilder(underscore);
        int fromIndex = 0;
        try {
            while (movieName.indexOf(letter, fromIndex) != -1) {
                int index = movieName.indexOf(letter, fromIndex);
                builder.setCharAt(index, letter.charAt(0));
                fromIndex = index + 1;
            }
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Error: Enter a letter instead of a space character.");
        }
        return builder.toString();
    }
}
