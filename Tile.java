import javax.swing.*;
import java.awt.*;

/**
 * the tiles that make up the grid
 */
public class Tile extends JLabel {
    private LetterState type;

    public Tile() {
        this.type = LetterState.DEFAULT;
        this.setOpaque(true);
        this.setBackground(new Color(18, 18, 19));
        this.setFont(new Font("Arial", Font.BOLD, 36));
        this.setForeground(new Color(255, 255, 255));
        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.setBorder(BorderFactory.createLineBorder(new Color(58, 58, 60), 2));
        this.setText("");
        this.setHorizontalTextPosition(SwingConstants.CENTER);
        this.setMinimumSize(new Dimension(20, 20));
        this.setPreferredSize(new Dimension(62, 62));
    }

    /**
     * updates the background based on the letter state
     */
    public void update() {
        switch (type) {
            case DEFAULT -> this.setBackground(new Color(18, 18, 19));
            case ABSENT -> this.setBackground(new Color(58, 58, 60));
            case PRESENT -> this.setBackground(new Color(181, 159, 59));
            case CORRECT -> this.setBackground(new Color(83, 141, 78));
        }
    }

    /**
     * changes the presence state of a letter
     *
     * @param type the type to change to
     */
    public void setType(LetterState type) {
        this.type = type;
    }

    /**
     * updates the background based on the letter state
     */
    public LetterState getType() {
        return type;
    }
}
