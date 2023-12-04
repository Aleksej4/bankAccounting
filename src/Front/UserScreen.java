package Front;

import data.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class UserScreen {

    private JFrame frame;
    private JLabel nameLabel;
    private JLabel accountNumberLabel;
    private JLabel balanceLabel;

    public UserScreen(Person person, JFrame logInFrame) {
        frame = new JFrame("Account Information");
        frame.setSize(500, 200);
        frame.setLayout(new BorderLayout(10, 10));

        JPanel infoPanel = new JPanel(new GridLayout(3, 1, 5, 5));

        nameLabel = new JLabel("Full Name: " + person.getFullName());
        accountNumberLabel = new JLabel("Account Number: " + person.getBankAccount().getAccountNumber());
        balanceLabel = new JLabel("Balance: " + person.getBankAccount().getBalance() + "€");

        infoPanel.add(nameLabel);
        infoPanel.add(accountNumberLabel);
        infoPanel.add(balanceLabel);

        JPanel actionPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        JButton transferButton = new JButton("Transfer Money");
        JButton withdrawButton = new JButton("Withdraw");
        JButton depositButton = new JButton("Deposit");

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
               logInFrame.setVisible(true);
            }
        });

        transferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TransferScreen transferScreen = new TransferScreen(person, frame, UserScreen.this);
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WithdrawDepositScreen withdrawDepositScreen = new WithdrawDepositScreen("Withdraw", person, frame, UserScreen.this);
            }
        });

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WithdrawDepositScreen withdrawDepositScreen = new WithdrawDepositScreen("Deposit", person, frame, UserScreen.this);
            }
        });

        actionPanel.add(transferButton);
        actionPanel.add(withdrawButton);
        actionPanel.add(depositButton);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(infoPanel, BorderLayout.CENTER);
        contentPanel.add(actionPanel, BorderLayout.SOUTH);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        frame.add(contentPanel);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public void refreshData(Person person) {
        balanceLabel.setText("Balance: " + person.getBankAccount().getBalance() + "€");
    }
}
