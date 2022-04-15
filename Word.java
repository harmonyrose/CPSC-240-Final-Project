import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Word class is responsible for generating a random 5-letter word and determining if guesses are valid
 * @version 1.0
 * @author Noah Stafford (if anyone has questions LOL)
 */

public class Word {
    private String todaysWord;

    /**
     * calls getTodaysWord method to get random word
     */
    public Word() {
        this.todaysWord = generateWord();
    }

    /**
     * when called by game class, generates a random word from the five letter word file
     * @return random 5 letter word
     */
    public String generateWord() {
        ArrayList<String> wordList = new ArrayList();

        Scanner fileScanner = null;
        try {
            File file = new File("fiveLetterWords.txt");
            fileScanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (fileScanner.hasNext()) {
            wordList.add(fileScanner.next());
        }

        Collections.shuffle(wordList);
        return wordList.get(0);
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
   public ArrayList checkWord(String guess) {
       ArrayList<LetterState> letters = new ArrayList<>();
       boolean present=false; //when a letter isn't correct but it's still present in the word
       for (int a=0; a<5; a++)
       {
           present=false;
           if (guess.substring(a,a+1).equals(todaysWord.substring(a,a+1)))
           {
               letters.add(LetterState.CORRECT);
           }
           else
           {
               for (int b=0; b<5; b++)
               {
                   if (guess.substring(a,a+1).equals(todaysWord.substring(b,b+1)))
                   {
                       present = true;
                       letters.add(LetterState.PRESENT);
                   }
               }
               if (!present)
               {
                   letters.add(LetterState.ABSENT);
               }
           }
       }
       return letters;
   }
}
