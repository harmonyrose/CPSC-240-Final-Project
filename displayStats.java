import javax.swing.*;
import java.awt.*;

/**
 * displayStats creates a stats screen after the game has been played that lists stats and option buttons for user
 *
 * @version 1.0
 */
public class displayStats extends JFrame {
    private Stats stats;

    public displayStats(Stats stats) {
        this.stats = stats;
        initComponents();
    }

    private void initComponents() {
        JFrame frame = new JFrame("Statistics");
        GridBagConstraints c;
        JPanel panel;
        JProgressBar bar;
        JLabel label;

        Integer mostOften = 0;
        for (Integer round : stats.getRounds()) {
            if (round > mostOften) {
                mostOften = round;
            }
        }

        JLabel label1 = new JLabel();
        JLabel label2 = new JLabel();
        JLabel label3 = new JLabel();
        JLabel label4 = new JLabel();
        JLabel label5 = new JLabel();
        JLabel label6 = new JLabel();
        JLabel label7 = new JLabel();
        JLabel label8 = new JLabel();
        JLabel label9 = new JLabel();
        JButton button1;
        JButton button2;
        JButton button3;

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(400, 400));
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        panel = new JPanel();
        panel.setBackground(new Color(18, 18, 19));
        panel.setPreferredSize(new Dimension(120, 80));
        GridBagLayout panelLayout = new GridBagLayout();
        panelLayout.columnWidths = new int[]{60};
        panel.setLayout(panelLayout);

        label1.setFont(new Font("Arial", Font.BOLD, 24));
        label1.setText("STATISTICS");
        label1.setForeground(new Color(255, 255, 255));
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 4;
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.PAGE_START;
        panel.add(label1, c);

        label2.setFont(new Font("Arial", Font.PLAIN, 36));
        label2.setText(String.valueOf(stats.getGamesPlayed())); //played
        label2.setForeground(new Color(255, 255, 255));
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        c.fill = GridBagConstraints.BOTH;
        panel.add(label2, c);

        label3.setFont(new Font("Arial", Font.PLAIN, 36));
        label3.setText(String.valueOf(stats.getWinPercent())); //win%
        label3.setForeground(new Color(255, 255, 255));
        label3.setHorizontalAlignment(SwingConstants.CENTER);
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 1;
        c.fill = GridBagConstraints.BOTH;
        panel.add(label3, c);

        label4.setFont(new Font("Arial", Font.PLAIN, 36));
        label4.setText(String.valueOf(stats.getCurrentStreak())); //current streak
        label4.setForeground(new Color(255, 255, 255));
        label4.setHorizontalAlignment(SwingConstants.CENTER);
        c = new GridBagConstraints();
        c.gridx = 2;
        c.gridy = 1;
        c.fill = GridBagConstraints.BOTH;
        panel.add(label4, c);

        label5.setFont(new Font("Arial", Font.PLAIN, 36));
        label5.setText(String.valueOf(stats.getMaxStreak())); //max streak
        label5.setForeground(new Color(255, 255, 255));
        label5.setHorizontalAlignment(SwingConstants.CENTER);
        c = new GridBagConstraints();
        c.gridx = 3;
        c.gridy = 1;
        c.fill = GridBagConstraints.BOTH;
        panel.add(label5, c);

        label6.setFont(new Font("Arial", Font.PLAIN, 12));
        label6.setForeground(new Color(255, 255, 255));
        label6.setText("Played");
        label6.setHorizontalAlignment(SwingConstants.CENTER);
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 2;
        c.fill = GridBagConstraints.BOTH;
        panel.add(label6, c);

        label7.setFont(new Font("Arial", Font.PLAIN, 12));
        label7.setText("Win %");
        label7.setForeground(new Color(255, 255, 255));
        label7.setHorizontalAlignment(SwingConstants.CENTER);
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 2;
        c.fill = GridBagConstraints.BOTH;
        panel.add(label7, c);

        label8.setFont(new Font("Arial", Font.PLAIN, 12));
        label8.setText("Current Streak");
        label8.setForeground(new Color(255, 255, 255));
        label8.setHorizontalAlignment(SwingConstants.CENTER);
        c = new GridBagConstraints();
        c.gridx = 2;
        c.gridy = 2;
        c.fill = GridBagConstraints.BOTH;
        panel.add(label8, c);

        label9.setFont(new Font("Arial", Font.PLAIN, 12));
        label9.setText("Max Streak");
        label9.setForeground(new Color(255, 255, 255));
        label9.setHorizontalAlignment(SwingConstants.CENTER);
        c = new GridBagConstraints();
        c.gridx = 3;
        c.gridy = 2;
        c.fill = GridBagConstraints.BOTH;
        panel.add(label9, c);

        frame.add(panel);

        panel = new JPanel();
        panel.setPreferredSize(new Dimension(120, 150));
        panelLayout = new GridBagLayout();
        panel.setBackground(new Color(18, 18, 19));
        panelLayout.columnWidths = new int[]{60};
        panel.setLayout(panelLayout);

        label = new JLabel("GUESS DISTRIBUTION");
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setForeground(new Color(255, 255, 255));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 4;
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.PAGE_START;
        panel.add(label, c);

        label = new JLabel("1");
        label.setForeground(new Color(255, 255, 255));
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        label.setHorizontalAlignment(SwingConstants.RIGHT);
        label.setForeground(new Color(255, 255, 255));
        label.setHorizontalTextPosition(SwingConstants.CENTER);
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.LINE_START;
        panel.add(label, c);

        bar = new JProgressBar(0, mostOften);
        bar.setValue(stats.getRounds().get(0));
        bar.setStringPainted(true);
        bar.setBorderPainted(false);
        bar.setString(String.valueOf(stats.getRounds().get(0)));
        bar.setBackground(new Color(18, 18, 19));
        bar.setFont(new Font("Arial", Font.PLAIN, 14));
        bar.setForeground(new Color(83, 141, 78));        bar.setMinimumSize(new Dimension(250, 20));
        bar.setPreferredSize(new Dimension(250, 20));
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 1;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(0, 4, 4, 0);
        panel.add(bar, c);

        label = new JLabel("2");
        label.setForeground(new Color(255, 255, 255));
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        label.setHorizontalAlignment(SwingConstants.RIGHT);
        label.setHorizontalTextPosition(SwingConstants.CENTER);
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 2;
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.LINE_START;
        panel.add(label, c);

        bar = new JProgressBar(0, mostOften);
        bar.setValue(stats.getRounds().get(1));
        bar.setStringPainted(true);
        bar.setBorderPainted(false);
        bar.setString(String.valueOf(stats.getRounds().get(1)));
        bar.setBackground(new Color(18, 18, 19));
        bar.setFont(new Font("Arial", Font.PLAIN, 14));
        bar.setForeground(new Color(83, 141, 78));        bar.setMinimumSize(new Dimension(250, 20));
        bar.setPreferredSize(new Dimension(250, 20));
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 2;
        c.insets = new Insets(0, 4, 4, 0);
        panel.add(bar, c);

        label = new JLabel("3");
        label.setForeground(new Color(255, 255, 255));
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        label.setHorizontalAlignment(SwingConstants.RIGHT);
        label.setHorizontalTextPosition(SwingConstants.CENTER);
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 3;
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.LINE_START;
        panel.add(label, c);

        bar = new JProgressBar(0, mostOften);
        bar.setValue(stats.getRounds().get(2));
        bar.setStringPainted(true);
        bar.setBorderPainted(false);
        bar.setString(String.valueOf(stats.getRounds().get(2)));
        bar.setBackground(new Color(18, 18, 19));
        bar.setFont(new Font("Arial", Font.PLAIN, 14));
        bar.setForeground(new Color(83, 141, 78));        bar.setMinimumSize(new Dimension(250, 20));
        bar.setPreferredSize(new Dimension(250, 20));
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 3;
        c.insets = new Insets(0, 4, 4, 0);
        panel.add(bar, c);

        label = new JLabel("4");
        label.setForeground(new Color(255, 255, 255));
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        label.setHorizontalAlignment(SwingConstants.RIGHT);
        label.setHorizontalTextPosition(SwingConstants.CENTER);
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 4;
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.LINE_START;
        panel.add(label, c);

        bar = new JProgressBar(0, mostOften);
        bar.setValue(stats.getRounds().get(3));
        bar.setStringPainted(true);
        bar.setBorderPainted(false);
        bar.setString(String.valueOf(stats.getRounds().get(3)));
        bar.setBackground(new Color(18, 18, 19));
        bar.setFont(new Font("Arial", Font.PLAIN, 14));
        bar.setForeground(new Color(83, 141, 78));        bar.setMinimumSize(new Dimension(250, 20));
        bar.setPreferredSize(new Dimension(250, 20));
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 4;
        c.insets = new Insets(0, 4, 4, 0);
        panel.add(bar, c);

        label = new JLabel("5");
        label.setForeground(new Color(255, 255, 255));
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        label.setHorizontalAlignment(SwingConstants.RIGHT);
        label.setHorizontalTextPosition(SwingConstants.CENTER);
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 5;
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.LINE_START;
        panel.add(label, c);

        bar = new JProgressBar(0, mostOften);
        bar.setValue(stats.getRounds().get(4));
        bar.setStringPainted(true);
        bar.setBorderPainted(false);
        bar.setString(String.valueOf(stats.getRounds().get(4)));
        bar.setBackground(new Color(18, 18, 19));
        bar.setFont(new Font("Arial", Font.PLAIN, 14));
        bar.setForeground(new Color(83, 141, 78));        bar.setMinimumSize(new Dimension(250, 20));
        bar.setPreferredSize(new Dimension(250, 20));
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 5;
        c.insets = new Insets(0, 4, 4, 0);
        panel.add(bar, c);

        label = new JLabel("6");
        label.setForeground(new Color(255, 255, 255));
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        label.setHorizontalAlignment(SwingConstants.RIGHT);
        label.setHorizontalTextPosition(SwingConstants.CENTER);
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 6;
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.LINE_START;
        panel.add(label, c);

        bar = new JProgressBar(0, mostOften);
        bar.setBorderPainted(false);
        bar.setValue(stats.getRounds().get(5));
        bar.setStringPainted(true);
        bar.setString(String.valueOf(stats.getRounds().get(5)));
        bar.setBackground(new Color(18, 18, 19));
        bar.setFont(new Font("Arial", Font.PLAIN, 14));
        bar.setForeground(new Color(83, 141, 78));        bar.setMinimumSize(new Dimension(250, 20));
        bar.setPreferredSize(new Dimension(250, 20));
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 6;
        c.insets = new Insets(0, 4, 4, 0);
        panel.add(bar, c);

        frame.add(panel);

        panel = new JPanel();
        panel.setBackground(new Color(18, 18, 19));
        panel.setPreferredSize(new Dimension(120, 150));
        panel.setLayout(new GridBagLayout());

        button1 = new JButton("PLAY AGAIN");
        button1.setBorderPainted(false);
        button1.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        button1.setBackground(new Color(83, 141, 78));
        button1.setForeground(new Color(255, 255, 255));
        button1.setFont(new Font("Arial", Font.BOLD, 20));
        button1.setPreferredSize(new Dimension(151, 52));
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.PAGE_START;
        panel.add(button1, c);
        button1.addActionListener(new ButtonListener(stats, frame));


        button2 = new JButton("Reset Stats");
        button2.setBorderPainted(false);
        button2.setBackground(new Color(58, 58, 60));
        button2.setForeground(new Color(255, 255, 255));
        button2.setFont(new Font("Arial", Font.BOLD, 14));
        button2.setPreferredSize(new Dimension(150, 25));
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.PAGE_START;
        c.insets = new Insets(0, 0, 0, 2);
        panel.add(button2, c);
        button2.addActionListener(new ButtonListener(stats, frame));

        button3 = new JButton("Quit");
        button3.setBorderPainted(false);
        button3.setBackground(new Color(58, 58, 60));
        button3.setForeground(new Color(255, 255, 255));
        button3.setFont(new Font("Arial", Font.BOLD, 14));
        button3.setPreferredSize(new Dimension(150, 25));
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 1;
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.PAGE_START;
        c.insets = new Insets(0, 2, 0, 0);
        panel.add(button3, c);
        button3.addActionListener(new ButtonListener(stats, frame));

        frame.add(panel);

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
