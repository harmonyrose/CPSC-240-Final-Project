import javax.swing.*;
import java.awt.*;

public class GameRunner {
    public static void main(String[] args) {
        JFrame frame0 = new JFrame("Login");

        frame0.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame0.setSize(new Dimension(600, 400));

        frame0.getContentPane().setLayout(new BoxLayout(frame0.getContentPane(), BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Enter your username");
        frame0.getContentPane().add(label);

        JTextField field = new JTextField(10);
        frame0.getContentPane().add(field);

        Login.addButton("Enter",frame0,field);

        frame0.pack();
        frame0.setVisible(true);

        JFrame frame = new JFrame("Wordle");

        JPanel jPanel1 = new JPanel();
        Grid grid = new Grid();

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
            String name = Wordle.buttonName(b);
            Key key = new Key(name);
            key.setAlignmentX(i);
            ButtonListener listener = new ButtonListener(b, grid);
            key.addActionListener(listener);
            Wordle.keys.add(key);
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
            String name = Wordle.buttonName(b);
            Key key = new Key(name);
            key.setAlignmentX((i-10));
            ButtonListener listener = new ButtonListener(b, grid);
            key.addActionListener(listener);
            Wordle.keys.add(key);
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
        String name = Wordle.buttonName(b);
        Key enter = new Key(name);
        enter.setAlignmentX(0);
        enter.setPreferredSize(new Dimension(65, 58));
        ButtonListener listener = new ButtonListener(b, grid);
        enter.addActionListener(listener);
        Wordle.keys.add(enter);
        keyRow3.add(enter);

        for (int i = 20; i < 27; i++) {
            b = ButtonType.values()[i];
            name = Wordle.buttonName(b);
            Key key = new Key(name);
            key.setAlignmentX((i-20));
            listener = new ButtonListener(b, grid);
            key.addActionListener(listener);
            Wordle.keys.add(key);
            keyRow3.add(key);
        }

        b = ButtonType.values()[27];
        name = Wordle.buttonName(b);
        Key back = new Key(name);
        listener = new ButtonListener(b, grid);
        back.addActionListener(listener);
        back.setAlignmentX(9);
        back.setPreferredSize(new Dimension(65, 58));
        Wordle.keys.add(back);
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