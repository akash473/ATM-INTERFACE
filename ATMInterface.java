import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ATMInterface extends JFrame implements ActionListener {
    private double balance = 1000.00; // Initial balance
    private ArrayList<String> transactions = new ArrayList<>(); // To store transaction history

    private JLabel balanceLabel;
    private JTextField amountField;
    private JTextArea transactionArea;
    private JButton depositButton, withdrawButton, checkBalanceButton, transactionHistoryButton, exitButton;

    public ATMInterface() {
        // Setting up the frame
        setTitle("ATM Interface");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Balance label
        balanceLabel = new JLabel("Balance: $" + balance);
        balanceLabel.setBounds(120, 20, 200, 30);
        add(balanceLabel);

        // Amount text field
        JLabel amountLabel = new JLabel("Enter Amount:");
        amountLabel.setBounds(50, 70, 100, 30);
        add(amountLabel);

        amountField = new JTextField();
        amountField.setBounds(150, 70, 150, 30);
        add(amountField);

        // Transaction area
        transactionArea = new JTextArea();
        transactionArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(transactionArea);
        scrollPane.setBounds(50, 220, 380, 120);
        add(scrollPane);

        // Deposit button
        depositButton = new JButton("Deposit");
        depositButton.setBounds(50, 120, 120, 30);
        depositButton.addActionListener(this);
        add(depositButton);

        // Withdraw button
        withdrawButton = new JButton("Withdraw");
        withdrawButton.setBounds(200, 120, 120, 30);
        withdrawButton.addActionListener(this);
        add(withdrawButton);

        // Check Balance button
        checkBalanceButton = new JButton("Check Balance");
        checkBalanceButton.setBounds(50, 170, 120, 30);
        checkBalanceButton.addActionListener(this);
        add(checkBalanceButton);

        // Transaction History button
        transactionHistoryButton = new JButton("Transaction History");
        transactionHistoryButton.setBounds(200, 170, 180, 30);
        transactionHistoryButton.addActionListener(this);
        add(transactionHistoryButton);

        // Exit button
        exitButton = new JButton("Exit");
        exitButton.setBounds(350, 120, 120, 30);
        exitButton.addActionListener(this);
        add(exitButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            double amount = Double.parseDouble(amountField.getText());
            if (e.getSource() == depositButton) {
                if (amount > 0) {
                    balance += amount;
                    transactions.add("Deposited: $" + amount);
                    JOptionPane.showMessageDialog(this, "Deposit Successful");
                } else {
                    JOptionPane.showMessageDialog(this, "Enter a valid amount");
                }
            } else if (e.getSource() == withdrawButton) {
                if (amount > 0 && amount <= balance) {
                    balance -= amount;
                    transactions.add("Withdrew: $" + amount);
                    JOptionPane.showMessageDialog(this, "Withdrawal Successful");
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid amount or Insufficient Balance");
                }
            } else if (e.getSource() == checkBalanceButton) {
                JOptionPane.showMessageDialog(this, "Current Balance: $" + balance);
            } else if (e.getSource() == transactionHistoryButton) {
                displayTransactionHistory();
            } else if (e.getSource() == exitButton) {
                JOptionPane.showMessageDialog(this, "Thank you for using the ATM");
                System.exit(0);
            }
            balanceLabel.setText("Balance: $" + balance);
            amountField.setText(""); // Clear the amount field after each operation
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number");
        }
    }

    private void displayTransactionHistory() {
        transactionArea.setText(""); // Clear previous history
        for (String transaction : transactions) {
            transactionArea.append(transaction + "\n");
        }
    }

    public static void main(String[] args) {
        new ATMInterface();
    }
}
