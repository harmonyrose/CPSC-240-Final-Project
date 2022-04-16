import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

class ButtonListener2 implements ActionListener {

        private JTextField input;
        private String user;

        public ButtonListener2(JTextField input){
            this.input = input;
    }

    @Override
        public void actionPerformed(ActionEvent e){
            user = input.getText();
            new File("stats/").mkdirs();
            try{
                File userStats = new File("stats/" + user);
                userStats.createNewFile();
            } catch (IOException f){
                f.printStackTrace();
                }
            Stats newUser = new Stats(user);
            try {
                PrintWriter writer = new PrintWriter("stats/" + user);
                newUser.saveStats(writer);
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
                }
        }



    }

    public class Login {
        public static void addButton(String text, JFrame f, JTextField textField){
            // add a button object
            JButton button = new JButton(text);
            button.addActionListener(new ButtonListener2(textField));
            f.getContentPane().add(button);
        }

        public static void main(String[] args) {
            // creates a window with a label, text field, and button
            JFrame frame = new JFrame("Login");

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            frame.setSize(new Dimension(600, 400));

            frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

            JLabel label = new JLabel("Enter your username");
            frame.getContentPane().add(label);

            JTextField field = new JTextField(10);
            frame.getContentPane().add(field);

            addButton("Enter",frame,field);

            frame.pack();
            frame.setVisible(true);
        }
    }

