import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Word class is responsible for generating a random 5-letter word and determining if guesses are valid
 * @version 1.0
 */

public class Word {
    private String todaysWord;

    /**
     * calls getTodaysWord method to get random word
     * @param todaysWord word of the day for the player
     */
    public Word(String todaysWord) {
        this.todaysWord = getTodaysWord();
    }

    /**
     * when called by game class, generates a random word from the five letter word file
     * @return random 5 letter word
     */
    public String getTodaysWord() {
        Random random = new Random();
        int randomNum = random.nextInt((5757 - 1) + 1);
        int counter = 0;

        try {
            File file = new File("fiveLetterWords.txt");
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNext()) {
                fileScanner.next();
                if (randomNum == counter)
                {
                    todaysWord = fileScanner.next();
                }
                else
                {
                    counter++;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return todaysWord;
    }

    /**
     * determines if a player's guess is a valid word or not in the list
     * @return true if guess is a valid word, otherwise false
     */
    public boolean isValid(String userGuess) {
        try {
            File file = new File("fiveLetterWords.txt");
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNext()) {
                if (fileScanner.next().equalsIgnoreCase(userGuess))
                {
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

     /**
     * When a player's guess is already valid, it compares their guess to the correct word and how many letters they got correct.
     * @param guess the user's valid word that they guessed
     * @return an ArrayList of the correct/incorrect letters that the user guessed
     */
   // public ArrayList checkWord(String guess) {

    //}
}
