import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * the GUI of the game itself
 *
 * @author Rachel Potter
 */
public class Wordle extends JFrame {
    private Word word;
    private Stats stats;
    private ArrayList<Key> keys = new ArrayList<>();
    private Grid grid;
    private JFrame frame;

    /**
     * builds the various separate objects and frame
     *
     * @param stats the statistics of the player
     */
    public Wordle(Stats stats) {
        this.stats = stats;
        this.word = new Word();
        this.frame = new JFrame("Wordle");
        this.grid = new Grid(word, stats, keys, frame);
        for (int i = 0; i < 28; i++) {
            ButtonType b = ButtonType.values()[i];
            String name = buttonName(b);
            Key key = new Key(name);
            key.addActionListener(new ButtonListener(b, grid));
            this.keys.add(key);
        }
        initComponents();
    }

    /**
     * sets the names of Keys
     *
     * @param b the key type
     * @return the name of the Key
     */
    public String buttonName(ButtonType b) {
        return switch (b) {
            // the letters
            case Q, W, E, R, T, Y, U, I, O, P, A, S, D, F, G, H, J, K, L, Z, X, C, V, B, N, M ->
                // return the button's name
                    b.name();

            // the other two
            case ENTER -> "ENTER";
            case BACK -> "BACK";
        };
    }

    public void initComponents() {
        JPanel jPanel1 = new JPanel();
        JPanel keyboard = new JPanel();

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setBackground(new Color(18, 18, 19));
        frame.setSize(new Dimension(500, 670));
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        jPanel1.setBackground(new Color(18, 18, 19));
        jPanel1.setSize(new Dimension(500, 462));
        jPanel1.setAlignmentX(Component.CENTER_ALIGNMENT);
        GridBagLayout jPanel1Layout = new GridBagLayout();
        jPanel1Layout.columnWidths = new int[]{350};
        jPanel1Layout.rowHeights = new int[]{420};
        jPanel1.setLayout(jPanel1Layout);

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;

        keyboard.setBackground(new Color(18, 18, 19));
        keyboard.setForeground(new Color(18, 18, 19));
        keyboard.setPreferredSize(new Dimension(500, 200));
        keyboard.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Q, W, E, R, T, Y, U, I, O, P
        JPanel keyRow1 = new JPanel();
        keyRow1.setBackground(new Color(18, 18, 19));
        keyRow1.setAlignmentX(0);
        keyRow1.setAlignmentY(0);
        keyRow1.setMaximumSize(new Dimension(484, 58));
        keyRow1.setMinimumSize(new Dimension(484, 58));
        keyRow1.setPreferredSize(new Dimension(484, 58));

        for (int i = 0; i < 10; i++) {
            Key key = keys.get(i);
            key.setAlignmentX(i);
            keyRow1.add(key);
        }

        // A, S, D, F, G, H, J, K, L
        JPanel keyRow2 = new JPanel();
        keyRow2.setBackground(new Color(18, 18, 19));
        keyRow2.setAlignmentX(0);
        keyRow2.setAlignmentY(1);
        keyRow2.setMaximumSize(new Dimension(441, 58));
        keyRow2.setMinimumSize(new Dimension(441, 58));
        keyRow2.setPreferredSize(new Dimension(441, 58));

        for (int i = 10; i < 19; i++) {
            Key key = keys.get(i);
            key.setAlignmentX(i - 10);
            keyRow2.add(key);
        }

        // ENTER, Z, X, C, V, B, N, M, BACK
        JPanel keyRow3 = new JPanel();
        keyRow3.setBackground(new Color(18, 18, 19));
        keyRow3.setAlignmentX(0);
        keyRow3.setAlignmentY(2);
        keyRow3.setMaximumSize(new Dimension(484, 58));
        keyRow3.setMinimumSize(new Dimension(484, 58));
        keyRow3.setPreferredSize(new Dimension(484, 58));

        Key enter = keys.get(19);
        enter.setAlignmentX(0);
        enter.setPreferredSize(new Dimension(65, 58));
        keyRow3.add(enter);

        for (int i = 20; i < 27; i++) {
            Key key = keys.get(i);
            key.setAlignmentX(i - 20);
            keyRow3.add(key);
        }

        Key back = keys.get(27);
        back.setAlignmentX(9);
        back.setPreferredSize(new Dimension(65, 58));
        keyRow3.add(back);

        //add the components
        jPanel1.add(grid);
        frame.add(jPanel1);
        keyboard.add(keyRow1);
        keyboard.add(keyRow2);
        keyboard.add(keyRow3);
        frame.add(keyboard);

        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(displayStats.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
}