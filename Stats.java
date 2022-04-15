import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import static java.time.LocalTime.now;

/**
 * The Stats class stores the user's achievements in Wordle and displays them to the user after each game.
 * It keeps track of the number of games they have played, their percent wins, current streak, and max streak.
 */

public class Stats {

    private String fileName;
    private int gamesPlayed;
    private int winPercent;
    private int currentStreak;
    private int maxStreak;
    private String recentGame;

    public Stats(String fileName){
        this.fileName = fileName;
    }

    /**
     * Reads in a user's stats from a file
     * @param in scanner to read the stats file
     */

    public Stats(Scanner in){
        gamesPlayed = Integer.parseInt(in.nextLine());
        winPercent = Integer.parseInt(in.nextLine());
        currentStreak = Integer.parseInt(in.nextLine());
        maxStreak = Integer.parseInt(in.nextLine());
    }

    /**
     * Updates a user's stats after they play one round.
     * @param isCorrect is passed to updateWinPercent and updateCurrentStreak
     */

    public void updateStats(boolean isCorrect){
        updateGamesPlayed();
        updateWinPercent(isCorrect);
        updateCurrentStreak(isCorrect);
        updateMaxStreak();
        updateRecentGame();
    }

    /**
     * Saves the user's stats into their file
     * @param writer writes to the user's stats file
     */

    public void saveStats(PrintWriter writer){
        writer.println(gamesPlayed);
        writer.println(winPercent);
        writer.println(currentStreak);
        writer.println(maxStreak);
        writer.println();
        writer.println(recentGame);
        writer.close();
    }

    /**
     * Adds 1 to the number of games played after each game
     */
    public void updateGamesPlayed() {
        this.gamesPlayed++;
    }

    /**
     * Updates the user's win percentage based on whether they guessed the word
     * @param isCorrect represents whether the user guessed the word correctly
     */

    public void updateWinPercent(boolean isCorrect){
        if(isCorrect){
            this.winPercent = (int)(((gamesPlayed-1) * winPercent) + 100)/ gamesPlayed;
        }
        else{
            this.winPercent = (int)((gamesPlayed-1) * winPercent)/ gamesPlayed;
        }
    }

    /**
     * Add 1 to their current streak if they guessed the word correctly. Otherwise, reset their
     * streak to 0.
     * @param isCorrect represents whether the user guessed the word correctly
     */

    public void updateCurrentStreak(boolean isCorrect){
        if(isCorrect){
            this.currentStreak++;
        }
        else{
            this.currentStreak=0;
        }
    }

    /**
     * If the user's current streak is their longest running one, it becomes their max streak.
     * Otherwise, the max streak does not change
     */

    public void updateMaxStreak(){
        if(currentStreak > maxStreak){
            this.maxStreak = currentStreak;
        }
    }

    /**
     * returns the emoji grid of the most recent game
     */
    public void updateRecentGame() {
        recentGame = "Wordle " + gamesPlayed;
        ArrayList<Tile> tiles = Grid.getTiles();
        Tile emptyLabel = Grid.getEmptyTile(tiles);

        if (tiles.indexOf(emptyLabel) == 5) {
            recentGame += " 1/6\n";
        } else if (tiles.indexOf(emptyLabel) == 10) {
            recentGame += " 2/6\n";
        } else if (tiles.indexOf(emptyLabel) == 15) {
            recentGame += " 3/6\n";
        } else if (tiles.indexOf(emptyLabel) == 15) {
            recentGame += " 4/6\n";
        } else if (tiles.indexOf(emptyLabel) == 15) {
            recentGame += " 5/6\n";
        } else if (tiles.indexOf(emptyLabel) == 15) {
            recentGame += " 6/6\n";
        } else {
            recentGame += " X/6\n";
        }
        for (Tile tile : tiles) {
            if (tile.getType() == LetterState.ABSENT) {
                recentGame = recentGame + "\u2B1B";
            } else if (tile.getType() == LetterState.PRESENT) {
                recentGame += "\uD83D\uDFE8";
            } else if (tile.getType() == LetterState.CORRECT) {
                recentGame += "\uD83D\uDFE9";
            }
            if (tiles.indexOf(tile) == 4 | tiles.indexOf(tile) == 9 | tiles.indexOf(tile) == 14 | tiles.indexOf(tile) == 19 | tiles.indexOf(tile) == 24 | tiles.indexOf(tile) == 29)
                recentGame += "\n";
        }
    }

    /**
     * Resets the user's stats to those of a new player
     */

    public void reset(){
        gamesPlayed = 0;
        winPercent = 0;
        currentStreak = 0;
        maxStreak = 0;
    }

}
