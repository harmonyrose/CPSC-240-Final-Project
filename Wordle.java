import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

// the color states of keys and tiles
enum LetterState {
    PRESENT,
    CORRECT,
    ABSENT,
    DEFAULT
}

// kinda just followed how the calculator example worked, open to reworking
enum ButtonType {
    // row 1
    Q,
    W,
    E,
    R,
    T,
    Y,
    U,
    I,
    O,
    P,

    // row 2
    A,
    S,
    D,
    F,
    G,
    H,
    J,
    K,
    L,

    // row 3
    ENTER,
    Z,
    X,
    C,
    V,
    B,
    N,
    M,
    BACK
}

// tile objects for words
class Tile extends JLabel {
    private LetterState type;

    public Tile() {
        this.type = LetterState.DEFAULT;
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

    //hopefully changes background after pressing enter?
    public void update() {
        if (this.type == LetterState.DEFAULT) {
            this.setBackground(new Color(129, 131, 132));
        } else if (this.type == LetterState.ABSENT) {
            this.setBackground(new Color(58, 58, 60));
        } else if (this.type == LetterState.PRESENT) {
            this.setBackground(new Color(83, 141, 78));
        } else if (this.type == LetterState.CORRECT) {
            this.setBackground(new Color(181, 159, 59));
        }
    }

    // possibly relevant for enter()
    public LetterState getType() {
        return type;
    }

    // possibly relevant for enter()
    public void setType(LetterState type) {
        this.type = type;
    }
}

class ButtonListener implements ActionListener {

    private ButtonType button;

    public ButtonListener(ButtonType button) {
        this.button = button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // check which button we are listening for
        switch (button) {
            case Q:
            case W:
            case E:
            case R:
            case T:
            case Y:
            case U:
            case I:
            case O:
            case P:
            case A:
            case S:
            case D:
            case F:
            case G:
            case H:
            case J:
            case K:
            case L:
            case Z:
            case X:
            case C:
            case V:
            case B:
            case N:
            case M:
                // when a letter, find the first empty label and add the letter to it.
                Tile emptyLabel = Wordle.getEmptyTile(Wordle.tiles);
                //TODO: stops at end of word but cannot continue, whoops.
                if (Wordle.tiles.indexOf(emptyLabel) == 5 | Wordle.tiles.indexOf(emptyLabel) == 10 | Wordle.tiles.indexOf(emptyLabel) == 15 | Wordle.tiles.indexOf(emptyLabel) == 20 | Wordle.tiles.indexOf(emptyLabel) == 15 | Wordle.tiles.indexOf(emptyLabel) == 25) {
                    break;
                } else {
                    JButton clicked = (JButton) e.getSource();
                    emptyLabel.setText(clicked.getText());
                }
                break;

            // for the other buttons, call the appropriate method
            case ENTER:
                Wordle.enter();
                break;
            case BACK:
                Wordle.back();
                break;
        }
    }
}

// creates keyboard buttons
class Key extends JButton {
    private LetterState type;

    public Key(String name) {
        this.type = LetterState.DEFAULT;
        this.setBackground(new Color(129, 131, 132));
        this.setFont(new Font("Arial", Font.BOLD, 12));
        this.setForeground(new Color(255, 255, 255));
        this.setBorderPainted(false);
        this.setText(name);
        this.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        this.setMargin(new Insets(0, 0, 0, 0));
        this.setPreferredSize(new Dimension(43, 58));
    }

    // possibly relevant for enter()
    public LetterState getType() {
        return type;
    }

    // possibly relevant for enter()
    public void setType(LetterState type) {
        this.type = type;
    }

    //hopefully changes background after pressing enter?
    public void update() {
        if (type == LetterState.DEFAULT) {
            setBackground(new Color(129, 131, 132));
        } else if (type == LetterState.ABSENT) {
            setBackground(new Color(58, 58, 60));
        } else if (type == LetterState.PRESENT) {
            setBackground(new Color(83, 141, 78));
        } else if (type == LetterState.CORRECT) {
            setBackground(new Color(181, 159, 59));
        }
    }
}

//actual gui
public class Wordle extends JFrame {
    static ArrayList<Tile> tiles = new ArrayList<>();
    static ArrayList<Key> keys = new ArrayList<>();

    public static String buttonName(ButtonType b) {
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

    public static Tile getEmptyTile(ArrayList<Tile> tiles) {
        Tile emptyLabel = new Tile();
        for(int i = 0; i < 30; i++) {
            if (tiles.get(i).getText().equals("")) {
                emptyLabel = tiles.get(i);
                break;
            }
        }
        return emptyLabel;
    }

    public static void enter() {
        //TODO: end suffering
    }

    public static void back() {
        Tile emptyLabel = getEmptyTile(tiles);

        int emptyIndex = tiles.indexOf(emptyLabel) - 1;
        if (emptyIndex > -1) {
            tiles.get(emptyIndex).setText("");
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Wordle");

        JPanel jPanel1 = new JPanel();
        JPanel grid = new JPanel();

        JPanel keyboard = new JPanel();

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setBackground(new Color(18, 18, 19));
        frame.setSize(new Dimension(500, 668));
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        jPanel1.setBackground(new Color(18, 18, 19));
        jPanel1.setForeground(new Color(18, 18, 19));
        jPanel1.setSize(new Dimension(500, 462));
        jPanel1.setAlignmentX(Component.CENTER_ALIGNMENT);
        GridBagLayout jPanel1Layout = new GridBagLayout();
        jPanel1Layout.columnWidths = new int[]{350};
        jPanel1Layout.rowHeights = new int[]{420};
        jPanel1.setLayout(jPanel1Layout);

        grid.setBackground(new Color(18, 18, 19));
        grid.setForeground(new Color(18, 18, 19));
        grid.setSize(new Dimension(350, 420));
        grid.setLayout(new GridLayout(6, 5, 5, 5));

        // create 6 rows of 5 tiles
        for (int i = 0; i < 30; i++) {
            Tile tile = new Tile();
            tiles.add(tile);
            grid.add(tile);
        }

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
            ButtonType b = ButtonType.values()[i];
            String name = buttonName(b);
            Key key = new Key(name);
            key.setAlignmentX(i);
            ButtonListener listener = new ButtonListener(b);
            key.addActionListener(listener);
            keys.add(key);
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
            ButtonType b = ButtonType.values()[i];
            String name = buttonName(b);
            Key key = new Key(name);
            key.setAlignmentX((i-10));
            ButtonListener listener = new ButtonListener(b);
            key.addActionListener(listener);
            keys.add(key);
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

        ButtonType b = ButtonType.values()[19];
        String name = buttonName(b);
        Key enter = new Key(name);
        enter.setAlignmentX(0);
        enter.setPreferredSize(new Dimension(65, 58));
        ButtonListener listener = new ButtonListener(b);
        enter.addActionListener(listener);
        keys.add(enter);
        keyRow3.add(enter);

        for (int i = 20; i < 27; i++) {
            b = ButtonType.values()[i];
            name = buttonName(b);
            Key key = new Key(name);
            key.setAlignmentX((i-20));
            listener = new ButtonListener(b);
            key.addActionListener(listener);
            keys.add(key);
            keyRow3.add(key);
        }

        b = ButtonType.values()[27];
        name = buttonName(b);
        Key back = new Key(name);
        listener = new ButtonListener(b);
        back.addActionListener(listener);
        back.setAlignmentX(9);
        back.setPreferredSize(new Dimension(65, 58));
        keys.add(back);
        keyRow3.add(back);

        //add the components
        jPanel1.add(grid);
        frame.add(jPanel1);
        keyboard.add(keyRow1);
        keyboard.add(keyRow2);
        keyboard.add(keyRow3);
        frame.add(keyboard);
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
        //TODO: errors confuse me.
        }
        frame.pack();
        frame.setVisible(true);
    }
}

