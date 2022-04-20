import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

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
    private ArrayList<Integer> rounds;

    public Stats(String fileName) {
        this.fileName = fileName;
        this.gamesPlayed = 0;
        this.winPercent = 0;
        this.currentStreak = 0;
        this.maxStreak = 0;
        this.rounds = new ArrayList<Integer>();
        this.rounds.add(0);
        this.rounds.add(0);
        this.rounds.add(0);
        this.rounds.add(0);
        this.rounds.add(0);
        this.rounds.add(0);
    }

    /**
     * Reads in a user's stats from a file
     *
     * @param in scanner to read the stats file
     */

    public Stats(Scanner in) {
        fileName = in.nextLine();
        gamesPlayed = in.nextInt();
        winPercent = in.nextInt();
        currentStreak = in.nextInt();
        maxStreak = in.nextInt();
        in.nextLine();
        rounds = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            rounds.add(in.nextInt());
        }
    }

    /**
     * Updates a user's stats after they play one round.
     *
     * @param isCorrect is passed to updateWinPercent and updateCurrentStreak
     */

    public void updateStats(boolean isCorrect, Grid grid) {
        updateGamesPlayed();
        updateWinPercent(isCorrect);
        updateCurrentStreak(isCorrect);
        updateMaxStreak();
        updateRounds(grid);
        updateRecentGame(grid);
    }

    /**
     * Saves the user's stats into their file
     */

    public void saveStats() {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("stats/" + fileName);
            writer.println(fileName);
            writer.println(gamesPlayed);
            writer.println(winPercent);
            writer.println(currentStreak);
            writer.println(maxStreak);
            writer.println();
            for (Integer round : rounds) {
                writer.println(round);
            }
            writer.println();
            writer.print(recentGame);
            writer.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Adds 1 to the number of games played after each game
     */
    public void updateGamesPlayed() {
        this.gamesPlayed++;
    }

    /**
     * Updates the user's win percentage based on whether they guessed the word
     *
     * @param isCorrect represents whether the user guessed the word correctly
     */

    public void updateWinPercent(boolean isCorrect) {
        if (isCorrect) {
            this.winPercent = (int) (((gamesPlayed - 1) * winPercent) + 100) / gamesPlayed;
        } else {
            this.winPercent = (int) ((gamesPlayed - 1) * winPercent) / gamesPlayed;
        }
    }

    /**
     * Add 1 to their current streak if they guessed the word correctly. Otherwise, reset their
     * streak to 0.
     *
     * @param isCorrect represents whether the user guessed the word correctly
     */

    public void updateCurrentStreak(boolean isCorrect) {
        if (isCorrect) {
            this.currentStreak++;
        } else {
            this.currentStreak = 0;
        }
    }

    /**
     * If the user's current streak is their longest running one, it becomes their max streak.
     * Otherwise, the max streak does not change
     */

    public void updateMaxStreak() {
        if (currentStreak > maxStreak) {
            this.maxStreak = currentStreak;
        }
    }

    /**
     * updates the emoji grid of the most recent game.
     *
     * @param grid the grid of letters from Wordle as to print them out
     */
    public void updateRecentGame(Grid grid) {
        recentGame = "Wordle " + gamesPlayed;
        ArrayList<Tile> tiles = grid.getTiles();
        Tile emptyLabel = grid.getEmptyTile();

        if (tiles.indexOf(emptyLabel) == 5) {
            recentGame += " 1/6\n";
        } else if (tiles.indexOf(emptyLabel) == 10) {
            recentGame += " 2/6\n";
        } else if (tiles.indexOf(emptyLabel) == 15) {
            recentGame += " 3/6\n";
        } else if (tiles.indexOf(emptyLabel) == 20) {
            recentGame += " 4/6\n";
        } else if (tiles.indexOf(emptyLabel) == 25) {
            recentGame += " 5/6\n";
        } else if (tiles.get(25).getType() == LetterState.CORRECT && tiles.get(26).getType() == LetterState.CORRECT && tiles.get(27).getType() == LetterState.CORRECT && tiles.get(28).getType() == LetterState.CORRECT && tiles.get(29).getType() == LetterState.CORRECT) {
            recentGame += " 6/6\n";
        } else {
            recentGame += " X/6\n";
        }
        for (Tile tile : tiles) {
            if (tile.getText().equals("")) {
                break;
            }
            switch (tile.getType()) {
                case ABSENT -> recentGame += "\u2B1B";
                case PRESENT -> recentGame += "\uD83D\uDFE8";
                case CORRECT -> recentGame += "\uD83D\uDFE9";
            }
            if (tiles.indexOf(tile) == 4 | tiles.indexOf(tile) == 9 | tiles.indexOf(tile) == 14 | tiles.indexOf(tile) == 19 | tiles.indexOf(tile) == 24)
                recentGame += "\n";
        }
    }

    /**
     * updates which round the player finished on
     *
     * @param grid the grid of letters from Wordle to examine the last round
     */
    public void updateRounds(Grid grid) {
        ArrayList<Tile> tiles = grid.getTiles();

        if (tiles.get(0).getType() == LetterState.CORRECT && tiles.get(1).getType() == LetterState.CORRECT && tiles.get(2).getType() == LetterState.CORRECT && tiles.get(3).getType() == LetterState.CORRECT && tiles.get(4).getType() == LetterState.CORRECT) {
            this.rounds.set(0, (rounds.get(0) + 1));
        } else if (tiles.get(5).getType() == LetterState.CORRECT && tiles.get(6).getType() == LetterState.CORRECT && tiles.get(7).getType() == LetterState.CORRECT && tiles.get(8).getType() == LetterState.CORRECT && tiles.get(9).getType() == LetterState.CORRECT) {
            this.rounds.set(1, (rounds.get(1) + 1));
        } else if (tiles.get(10).getType() == LetterState.CORRECT && tiles.get(11).getType() == LetterState.CORRECT && tiles.get(12).getType() == LetterState.CORRECT && tiles.get(13).getType() == LetterState.CORRECT && tiles.get(14).getType() == LetterState.CORRECT) {
            this.rounds.set(2, (rounds.get(2) + 1));
        } else if (tiles.get(15).getType() == LetterState.CORRECT && tiles.get(16).getType() == LetterState.CORRECT && tiles.get(17).getType() == LetterState.CORRECT && tiles.get(18).getType() == LetterState.CORRECT && tiles.get(19).getType() == LetterState.CORRECT) {
            this.rounds.set(3, (rounds.get(3) + 1));
        } else if (tiles.get(20).getType() == LetterState.CORRECT && tiles.get(21).getType() == LetterState.CORRECT && tiles.get(22).getType() == LetterState.CORRECT && tiles.get(23).getType() == LetterState.CORRECT && tiles.get(24).getType() == LetterState.CORRECT) {
            this.rounds.set(4, (rounds.get(4) + 1));
        } else if (tiles.get(25).getType() == LetterState.CORRECT && tiles.get(26).getType() == LetterState.CORRECT && tiles.get(27).getType() == LetterState.CORRECT && tiles.get(28).getType() == LetterState.CORRECT && tiles.get(29).getType() == LetterState.CORRECT) {
            this.rounds.set(5, (rounds.get(5) + 1));
        }
    }

    /**
     * Resets the user's stats to those of a new player
     */

    public void reset() {
        gamesPlayed = 0;
        winPercent = 0;
        currentStreak = 0;
        maxStreak = 0;
    }

    /**
     * @return current game streak
     */
    public int getCurrentStreak() {
        return currentStreak;
    }

    /**
     * @return Amount of games played
     */
    public int getGamesPlayed() {
        return gamesPlayed;
    }

    /**
     * @return Maximum game streak
     */
    public int getMaxStreak() {
        return maxStreak;
    }

    /**
     * @return Percent Wins the user has
     */
    public int getWinPercent() {
        return winPercent;
    }

    /**
     * @return arraylist of rounds the user has scored
     */
    public ArrayList<Integer> getRounds() {
        return rounds;
    }
}
