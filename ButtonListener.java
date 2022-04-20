import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * responses to button presses
 *
 * @author Rachel & Harmony
 */
public class ButtonListener implements ActionListener {
    private ButtonType button;
    private Grid grid;
    private JTextField input;
    private String user;
    private JFrame frame;
    private Stats stats;

    /**
     * Acesses the keys being typed into the grid
     * @author Rachel Potter
     * @param button key pressed
     * @param grid   where they are being typed into
     */
    public ButtonListener(ButtonType button, Grid grid) {
        this.button = button;
        this.grid = grid;
    }

    /**
     * Accesses the username the player entered to the textfield
     * @author Harmony
     *
     * @param input player username
     * @param frame the current frame
     */
    public ButtonListener(JTextField input, JFrame frame) {
        this.input = input;
        this.user = input.getText();
        this.frame = frame;
    }
    /**
     * play again button allows user to play wordle again, restart stats button allows user to reset their stats to 0, and quit button closes the program
     *
     * @author Noah Stafford
     * @param stats the Stats the user created
     */
    public ButtonListener(Stats stats, JFrame frame) {
        this.stats = stats;
        this.frame = frame;
    }

    /**
     * if a Key, types it into the next empty tile
     * if from Login, creates a new stats file if the player is new, otherwise accesses their already existing file
     * @author Rachel & Harmony & Noah
     * @param e button press
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (button != null) {
            // check which button we are listening for
            switch (button) {
                case Q, W, E, R, T, Y, U, I, O, P, A, S, D, F, G, H, J, K, L, Z, X, C, V, B, N, M -> {
                    // when a letter, find the first empty label and add the letter to it.
                    Tile emptyLabel = grid.getEmptyTile();
                    JButton clicked = (JButton) e.getSource();
                    emptyLabel.setText(clicked.getText());
                }

                // for the other buttons, call the appropriate method
                case ENTER -> grid.enter();
                case BACK -> grid.back();
            }
        } else if (stats != null) {
            if (((JButton)e.getSource()).getText().equals("Quit")) {
                System.exit(0);
            } else if (((JButton)e.getSource()).getText().equals("Reset Stats")) {
                stats.reset();
            } else if (((JButton)e.getSource()).getText().equals("PLAY AGAIN")) {
                new Wordle(stats);
                frame.dispose();
            }
        }else if (input != null) {
            user = input.getText();
            Stats newUser = null;
            new File("stats/").mkdirs();
            try {
                File userStats = new File("stats/" + user);
                if (userStats.createNewFile()) {
                    newUser = new Stats(user);
                } else {
                    Scanner scanner = new Scanner(new File("stats/" + user));
                    newUser = new Stats(scanner);
                }
            } catch (IOException f) {
                f.printStackTrace();
            }
            newUser.saveStats();

            new Wordle(newUser);
            frame.dispose();
        } else {
            JOptionPane.showMessageDialog(new JFrame(), "Please type in a name.", "No Name Provided", JOptionPane.ERROR_MESSAGE);
        }
    }
}
