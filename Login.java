import javax.swing.*;
import java.awt.*;

/**
 * Login is responsible for allowing player to enter their username to login to game
 */
public class Login extends JFrame {

    public Login() {
        initComponents();
    }

    private void initComponents() {
        JFrame frame = new JFrame("Login");
        JPanel panel;
        GridBagConstraints c;
        JLabel label1 = new JLabel("WORDLE");
        JLabel label2 = new JLabel("Please enter your username:");
        JTextField jTextField1 = new JTextField(20);
        JButton jButton1 = new JButton("ENTER");

        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(18,18,19));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(260, 200);

        label1.setFont(new Font("Arial", Font.BOLD, 36));
        label1.setForeground(new Color(255, 255, 255));
        label1.setAlignmentX(0.5F);
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(6, 6, 6, 6);
        panel.add(label1, c);

        label2.setFont(new Font("Arial", Font.PLAIN, 14));
        label2.setForeground(new Color(255, 255, 255));
        label2.setAlignmentX(0.5F);
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(6, 6, 6, 6);
        panel.add(label2, c);

        jTextField1.setFont(new Font("Arial", Font.BOLD, 14));
        jTextField1.setSize(200, 18);
        jTextField1.setBorder(BorderFactory.createEmptyBorder());
        jTextField1.setAlignmentX(0.5F);
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(6, 6, 6, 6);
        panel.add(jTextField1, c);

        jButton1.setFont(new Font("Arial", Font.BOLD, 20));
        jButton1.setBackground(new Color(83, 141, 78));
        jButton1.setBorderPainted(false);
        jButton1.setForeground(new Color(255, 255, 255));
        jButton1.setPreferredSize(new Dimension(151, 52));
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 3;
        jButton1.addActionListener(new ButtonListener(jTextField1, frame));
        panel.add(jButton1, c);
        frame.add(panel);

        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
}

