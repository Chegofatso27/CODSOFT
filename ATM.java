import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATM extends JFrame {
    private BankAccount userAccount;

    // GUI components
    private JButton withdrawButton;
    private JButton depositButton;
    private JButton checkBalanceButton;
    private JButton exitButton;
    private JLabel balanceLabel;

    public ATM(BankAccount userAccount) {
        this.userAccount = userAccount;
        initializeComponents();
    }

    private void initializeComponents() {
        setTitle("ATM Machine");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        withdrawButton = new JButton("Withdraw");
        depositButton = new JButton("Deposit");
        checkBalanceButton = new JButton("Check Balance");
        exitButton = new JButton("Exit");

        balanceLabel = new JLabel("Balance: R" + userAccount.getBalance());

        setLayout(new GridLayout(3, 1));

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout());

        buttonsPanel.add(withdrawButton);
        buttonsPanel.add(depositButton);
        buttonsPanel.add(checkBalanceButton);
        buttonsPanel.add(exitButton);

        add(balanceLabel);
        add(buttonsPanel);

        // Set button colors
        withdrawButton.setBackground(Color.RED);
        depositButton.setBackground(Color.GREEN);
        checkBalanceButton.setBackground(Color.BLUE);
        exitButton.setBackground(Color.YELLOW);

        // Add action listeners
        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleWithdraw();
            }
        });

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleDeposit();
            }
        });

        checkBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateBalanceLabel();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private void handleWithdraw() {
        String amountStr = JOptionPane.showInputDialog(this, "Enter the amount to withdraw:");
        try {
            double withdrawAmount = Double.parseDouble(amountStr);
            if (withdrawAmount > 0) {
                if (userAccount.withdraw(withdrawAmount)) {
                    JOptionPane.showMessageDialog(this, "Withdrawal successful. New balance: R" + userAccount.getBalance());
                    updateBalanceLabel();
                } else {
                    JOptionPane.showMessageDialog(this, "Insufficient funds. Withdrawal failed.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Invalid amount. Please enter a positive value.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid number.");
        }
    }

    private void handleDeposit() {
        String amountStr = JOptionPane.showInputDialog(this, "Enter the amount to deposit:");
        try {
            double depositAmount = Double.parseDouble(amountStr);
            if (depositAmount > 0) {
                userAccount.deposit(depositAmount);
                JOptionPane.showMessageDialog(this, "Deposit successful. New balance: R" + userAccount.getBalance());
                updateBalanceLabel();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid amount. Please enter a positive value.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid number.");
        }
    }

    private void updateBalanceLabel() {
        balanceLabel.setText("Balance: R" + userAccount.getBalance());
    }

    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount(1000.0); // initial balance of $1000
        ATM atm = new ATM(userAccount);
        SwingUtilities.invokeLater(() -> atm.setVisible(true));
    }
}
