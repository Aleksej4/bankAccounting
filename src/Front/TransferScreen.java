package Front;

import data.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;

public class TransferScreen{
    private JFrame frame;
    private JTextField accountNumberField;
    private JTextField amountField;

    public TransferScreen(Person person, JFrame parent, UserScreen userScreen) {
        parent.setVisible(false);

        frame = new JFrame("Transfer Money");
        frame.setSize(350, 150);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        JLabel accountNumberLabel = new JLabel("Enter Account Number:");
        accountNumberField = new JTextField(15);

        JLabel amountLabel = new JLabel("Enter Amount:");
        amountField = new JTextField(15);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                parent.setVisible(true);
            }
        });

        JButton transferButton = new JButton("Transfer");
        transferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String accountNumber = accountNumberField.getText();
                try {
                    int amount = Integer.parseInt(amountField.getText());

                    if (person.getBankAccount().transferFunds(accountNumber, amount)) {
                        frame.dispose();
                        parent.setVisible(true);
                        userScreen.refreshData(person);
                    } else {
                        JOptionPane.showMessageDialog(null, "Incorrect account number or amount!", "Notification", JOptionPane.ERROR_MESSAGE);
                    }
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "Provide numeric amount!", "Notification", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(accountNumberLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        panel.add(accountNumberField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(amountLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        panel.add(amountField, constraints);

        constraints.gridwidth = 2;
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(transferButton, constraints);

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}

