import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Scanner;
import static java.time.LocalTime.now;

public class Stats {

    private String fileName;
    private int gamesPlayed;
    private int winPercent;
    private int currentStreak;
    private int maxStreak;
    private LocalDate nextWordle;

    public Stats(String fileName){
        this.fileName = fileName;
    }

    public Stats(Scanner in){
        gamesPlayed = Integer.parseInt(in.nextLine());
        winPercent = Integer.parseInt(in.nextLine());
        currentStreak = Integer.parseInt(in.nextLine());
        maxStreak = Integer.parseInt(in.nextLine());
        nextWordle = LocalDate.parse(in.nextLine());
    }

    public void updateStats(boolean isCorrect){
        updateGamesPlayed();
        updateWinPercent(isCorrect);
        updateCurrentStreak(isCorrect);
        updateMaxStreak();
        timeUntilNext();
    }

    public void saveStats(PrintWriter writer){
        writer.println(gamesPlayed);
        writer.println(winPercent);
        writer.println(currentStreak);
        writer.println(maxStreak);
        writer.println(nextWordle);
        writer.close();
    }

    public void updateGamesPlayed(){
        this.gamesPlayed++;
    }

    public void updateWinPercent(boolean isCorrect){
        if(isCorrect){
            this.winPercent = (int)(((gamesPlayed-1) * winPercent) + 100)/ gamesPlayed;
        }
        else{
            this.winPercent = (int)((gamesPlayed-1) * winPercent)/ gamesPlayed;
        }
    }

    public void updateCurrentStreak(boolean isCorrect){
        if(isCorrect){
            this.currentStreak++;
        }
        else{
            this.currentStreak=0;
        }
    }

    public void updateMaxStreak(){
        if(currentStreak > maxStreak){
            this.maxStreak = currentStreak;
        }
    }

    public void timeUntilNext(){
    }

}
