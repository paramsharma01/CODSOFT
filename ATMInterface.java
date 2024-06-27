import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ATMInterface implements ActionListener {
    Account account;
    JFrame frame1;
    JLabel feedback;
    JLabel label3;
    String[] options = { "Deposit", "Withdrawal", "Check Balance", "End Session" };
    JButton[] buttons = new JButton[options.length];
    double depositedAmount;
    double withdrawalAmount;

    ATMInterface(Account ac) {
        this.account = ac;
        frame1 = new JFrame("ATM");
        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));

        JLabel label1 = new JLabel("Welcome");
        label1.setAlignmentX(Component.CENTER_ALIGNMENT);
        label1.setFont(new Font("Impact", Font.PLAIN, 40));
        JLabel label2 = new JLabel("to ATM");
        label2.setAlignmentX(Component.CENTER_ALIGNMENT);
        label2.setFont(new Font("Impact", Font.PLAIN, 30));
        panel1.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        panel1.add(label1);
        panel1.add(label2);

        JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        label3 = new JLabel("Choose any transaction");
        label3.setMaximumSize(new Dimension(400, 20));
        label3.setPreferredSize(new Dimension(200, 20));
        label3.setAlignmentX(Component.CENTER_ALIGNMENT);
        feedback = new JLabel();
        feedback.setAlignmentX(Component.CENTER_ALIGNMENT);
        feedback.setMaximumSize(new Dimension(400, 30));
        feedback.setPreferredSize(new Dimension(300, 30));

        panel2.add(label3);
        panel2.add(Box.createVerticalStrut(20));
        panel2.add(feedback);
        panel2.setBorder(BorderFactory.createEmptyBorder(30, 20, 30, 20));

        JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayout(2, 2, 20, 10));

        for (int i = 0; i < options.length; i++) {
            buttons[i] = new JButton(options[i]);
            buttons[i].addActionListener(this);
            panel3.add(buttons[i]);
        }
        panel3.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        
        frame1.add(panel3, BorderLayout.SOUTH);
        frame1.add(panel2, BorderLayout.CENTER);
        frame1.add(panel1, BorderLayout.NORTH);
        frame1.setSize(500, 400);
        frame1.setVisible(true);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setLocationRelativeTo(null);

    }

    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {
            case "Deposit":
                depositWindow();
                if (account.deposit(depositedAmount)) {
                    feedback.setText("Amount Deposited Successfully");
                } else {
                    feedback.setText("ERROR ! Amount can't be deposited");
                }

                break;
            case "Withdrawal":
                withdrawalWindow();
                if (account.withdraw(withdrawalAmount)) {
                    feedback.setText("Amount has been withdrawn");
                } else {
                    feedback.setText("ERROR! Amount can't be withdrawn");
                }

                break;

            case "Check Balance":
                feedback.setText("Your Account Balance is " + String.valueOf(account.checkBalance()));

                break;

            case "End Session":
                System.exit(0);
                break;
            default:
                break;
        }
    }

    public void depositWindow() {
        JDialog depositW = new JDialog(frame1, "Deposit Window", true);
        depositW.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel depositLabel = new JLabel("Enter Amount:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        depositW.add(depositLabel, gbc);

        JTextField depositText = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        depositW.add(depositText, gbc);

        JButton enterButton = new JButton("Enter");
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        enterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    depositedAmount = Double.parseDouble(depositText.getText());
                } catch (Exception exp) {
                    System.out.println(exp);
                }
                depositW.dispose();
            }
        });
        depositW.add(enterButton, gbc);
        depositW.setSize(400, 200);
        depositW.setLocationRelativeTo(frame1);
        depositW.setVisible(true);
    }

    public void withdrawalWindow() {
        JDialog withdrawW = new JDialog(frame1, "Withdrawal Window", true);
        withdrawW.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel withdrawLabel = new JLabel("Enter Amount:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        withdrawW.add(withdrawLabel, gbc);

        JTextField withdrawText = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        withdrawW.add(withdrawText, gbc);

        JButton enterButton = new JButton("Enter");
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        enterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    withdrawalAmount = Double.parseDouble(withdrawText.getText());
                } catch (Exception exp) {
                    System.out.println(exp);
                }
                withdrawW.dispose();
            }
        });

        withdrawW.add(enterButton, gbc);
        withdrawW.setSize(400, 200);
        withdrawW.setLocationRelativeTo(frame1);
        withdrawW.setVisible(true);

    }

    public static void main(String[] args) {
        Account a1 = new Account(1000.00);
        new ATMInterface(a1);
    }

}

class Account {
    private double balance;

    Account(double initBalance) {
        this.balance = initBalance;
    }

    public boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            return true;
        } else {
            return false;
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        } else {
            return false;
        }
    }

    public double checkBalance() {
        return balance;
    }

}