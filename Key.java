import javax.swing.*;
import java.awt.*;

/**
 * the buttons that make up the keyboard
 *
 * @author Rachel Potter
 */
public class Key extends JButton {
    private LetterState type;

    public Key(String name) {
        this.type = LetterState.DEFAULT;
        this.setBackground(new Color(129, 131, 132));
        this.setFont(new Font("Arial", Font.BOLD, 12));
        this.setForeground(new Color(255, 255, 255));
        this.setBorderPainted(false);
        this.setText(name);
        this.setHorizontalTextPosition(SwingConstants.CENTER);
        this.setMargin(new Insets(0, 0, 0, 0));
        this.setPreferredSize(new Dimension(43, 58));
    }

    /**
     * changes the presence state of a letter
     * @param type the type to change to
     */
    public void setType(LetterState type) {
        this.type = type;
    }

    /**
     * updates the background based on the letter state
     */
    public void update() {
        switch (type) {
            case DEFAULT -> setBackground(new Color(129, 131, 132));
            case ABSENT -> setBackground(new Color(58, 58, 60));
            case PRESENT -> this.setBackground(new Color(181, 159, 59));
            case CORRECT -> this.setBackground(new Color(83, 141, 78));
        }
    }
}
