import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GradeCalculator implements ActionListener {
    // Defining Variables
    JFrame f;
    JLabel label1;
    JLabel[] subLabel;
    JTextField[] marks;
    JButton submitButton;
    String[] subName = { "English", "Hindi", "Mathematics", "Science", "Social Science" };

    // Constructor
    public GradeCalculator(String s) {
        f = new JFrame(s);

        label1 = new JLabel("Enter Your Marks");
        JPanel p1 = new JPanel();
        p1.add(label1);

        JPanel p2 = new JPanel(new GridLayout(5, 2, 20, 10));
        subLabel = new JLabel[subName.length];
        marks = new JTextField[subName.length];

        for (int i = 0; i < subName.length; i++) {
            subLabel[i] = new JLabel(subName[i]);
            marks[i] = new JTextField();
            p2.add(subLabel[i]);
            p2.add(marks[i]);
        }

        JPanel p3 = new JPanel();
        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);
        p3.add(submitButton);

        f.add(p1, BorderLayout.NORTH);
        f.add(p2, BorderLayout.CENTER);
        f.add(p3, BorderLayout.SOUTH);
        f.add(new JPanel(), BorderLayout.EAST);
        f.add(new JPanel(), BorderLayout.WEST);
        f.setSize(400, 300);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Handling Event
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            boolean flag = false;
            int mark;
            try {
                for (int i = 0; i < subName.length; i++) {
                    mark = Integer.parseInt(marks[i].getText());
                    if (mark < 0 || mark > 100) {
                        flag = true;
                        break;
                    }
                }

                if (flag) {
                    checkValues();
                } else {
                    showResult();
                }
            } catch (NumberFormatException ex) {
                checkValues();
            }
        }
    }

    // Giving error
    public void checkValues() {
        JDialog err = new JDialog(f, "Error");
        JLabel lb = new JLabel("Please Enter Marks Correctly!!!");
        err.add(lb);
        err.setSize(250, 100);
        err.setLayout(new FlowLayout());
        err.setLocationRelativeTo(null);
        err.setVisible(true);
    }

    // Showing result frame
    public void showResult() {
        JFrame resultFrame = new JFrame("Result");
        resultFrame.setLayout(null);

        JLabel lb1 = new JLabel("Result:");
        lb1.setBounds(10, 10, 200, 20);

        JLabel lb2 = new JLabel("Total Marks:      " + totalMarksGot());
        lb2.setBounds(30, 50, 200, 30);
        JLabel lb3 = new JLabel("Percentage:      " + percentageGot() + " %");
        lb3.setBounds(30, 70, 200, 30);

        JLabel lb4 = new JLabel("Grade: " + gradeGot());
        lb4.setBounds(30, 120, 200, 30);
        lb4.setFont(new Font("Arial", Font.BOLD, 15));

        resultFrame.add(lb1);
        resultFrame.add(lb2);
        resultFrame.add(lb3);
        resultFrame.add(lb4);

        resultFrame.setSize(300, 250);
        resultFrame.setVisible(true);
        resultFrame.setLocationRelativeTo(null);
        resultFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public int totalMarksGot() {
        int result = 0;
        for (int i = 0; i < subName.length; i++) {
            result += Integer.parseInt(marks[i].getText());
        }
        return result;
    }

    public float percentageGot() {
        return (float) totalMarksGot() / subName.length;
    }

    public char gradeGot() {
        float per = percentageGot();
        if (per >= 85) {
            return 'A';
        } else if (per >= 70) {
            return 'B';
        } else if (per >= 55) {
            return 'C';
        } else if (per >= 40) {
            return 'D';
        } else {
            return 'E';
        }
    }

    // Calling constructor
    public static void main(String[] args) {
        new GradeCalculator("Grade Calculator");
    }
}
