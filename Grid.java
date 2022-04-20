import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

/**
 * where the game actually takes place. contains the tiles that contain guesses
 */
public class Grid extends JPanel {
    private ArrayList<Tile> tiles = new ArrayList<>();
    private Word word;
    private Stats stats;
    private ArrayList<Key> keys;
    private JFrame frame;

    /**
     * creates the grid space
     *
     * @param word  the word to compare guesses to
     * @param stats the stats of the username
     * @param keys  the keys to change color
     */
    public Grid(Word word, Stats stats, ArrayList<Key> keys, JFrame frame) {
        this.word = word;
        this.stats = stats;
        this.keys = keys;
        this.frame = frame;
        this.setBackground(new Color(18, 18, 19));
        this.setForeground(new Color(18, 18, 19));
        this.setSize(new Dimension(350, 420));
        this.setLayout(new GridLayout(6, 5, 5, 5));

        //create 6 rows of 5 tiles
        for (int i = 0; i < 30; i++) {
            Tile tile = new Tile();
            tiles.add(tile);
            this.add(tile);
        }
    }

    public void reset() {
        while (this.getComponentCount() > 0) {
            this.remove(0);
        }
        tiles.clear();
        for (int i = 0; i < 30; i++) {
            Tile tile = new Tile();
            tiles.add(tile);
            this.add(tile);
        }
    }

    /**
     * searches the tiles for an empty tile
     *
     * @return emptyLabel an empty tile to represent one in the arraylist or labeled "end" to signify the end of the rounds
     * @author Rachel Potter
     */
    public Tile getEmptyTile() {
        Tile emptyLabel = new Tile();
        for (int i = 0; i < 30; i++) {
            if (tiles.get(i).getText().equals("")) {
                emptyLabel = tiles.get(i);
                break;
            } else {
                emptyLabel.setText("end");
            }
        }
        return emptyLabel;
    }

    public ArrayList<Tile> getTiles() {
        return tiles;
    }

    /**
     * finds the guess and changes colors of tiles accordingly
     *
     * @author Rachel & Harmony
     */
    public void enter() {
        String guess = "";
        int start = 0; //where to start the "guess"
        Tile emptyLabel = getEmptyTile();
        ArrayList<Tile> tiles = getTiles();
        if (emptyLabel.getText().equals("end")) { // if round 6
            start = 25;
        } else {
            start = tiles.indexOf(emptyLabel) - 5;
        }
        if (start < 0) {
            JOptionPane.showMessageDialog(new JFrame(), "Please type a full word.", "Incomplete Word", JOptionPane.ERROR_MESSAGE);
        } else {
            for (int i = 0; i < 5; i++) {
                guess += tiles.get(start + i).getText();
            }
            if (guess.length() < 5) {
                JOptionPane.showMessageDialog(new JFrame(), "Please type a full word.", "Incomplete Word", JOptionPane.ERROR_MESSAGE);
            }
            if (word.isValid(guess)) {
                ArrayList results = word.checkWord(guess.toLowerCase());
                int counter = 0;
                for (int i = 0; i < 5; i++) {
                    if (results.get(i) == LetterState.CORRECT) {
                        counter++;
                    }
                    tiles.get(start + i).setType((LetterState) results.get(i));
                    tiles.get(start + i).update();
                    for (Key key : keys) {
                        if (key.getText().equals(guess.substring(i, i + 1))) {
                            key.setType((LetterState) results.get(i));
                            key.update();
                        }
                    }
                }
                if (counter == 5) { // all letters correct!
                    JOptionPane.showMessageDialog(new JFrame(), "Hooray! You won!", "Congrats!", JOptionPane.INFORMATION_MESSAGE);
                    stats.updateStats(true, this);
                    stats.saveStats();
                    new displayStats(stats);
                    frame.dispose();
                } else if (tiles.get(29).getType() != LetterState.DEFAULT) { //when last tile has been tested
                    JOptionPane.showMessageDialog(new JFrame(), "Sorry, the word was '" + word.getWord() + "'", "Game Ended", JOptionPane.INFORMATION_MESSAGE);
                    stats.updateStats(false, this);
                    stats.saveStats();
                    new displayStats(stats);
                    frame.dispose();
                }
            } else {
                JOptionPane.showMessageDialog(new JFrame(), "We don't recognize that word.", "Invalid Word", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * deletes letters so a guess can be edited before entering
     *
     * @author Rachel Potter
     */
    public void back() {
        ArrayList<Tile> tiles = getTiles();
        Tile emptyLabel = getEmptyTile();
        int indexToEmpty = 0;
        if (emptyLabel.getText().equals("end")) {
            indexToEmpty = 29;
        } else {
            indexToEmpty = tiles.indexOf(emptyLabel) - 1;
        }
        if (indexToEmpty >= 0 && tiles.get(indexToEmpty).getType() == LetterState.DEFAULT) { // makes sure to not delete a tile already filled in
            tiles.get(indexToEmpty).setText("");
        }
    }
}
