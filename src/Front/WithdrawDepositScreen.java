package Front;

import data.Person;
import sql.SqlQueries;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;

public class WithdrawDepositScreen {
    private JFrame frame;

    private JTextField amountField;

    private final SqlQueries sqlQueries = new SqlQueries();

    public WithdrawDepositScreen(String text, Person person, JFrame userFrame, UserScreen userScreen) {
        userFrame.setVisible(false);

        frame = new JFrame(text);
        frame.setSize(500, 150);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        JLabel amountLabel = new JLabel("Amount:");
        amountField = new JTextField(15);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                userFrame.setVisible(true);
            }
        });

        JButton submit = new JButton(text);
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int amount = Integer.parseInt(amountField.getText());
                    if (text.equals("Deposit")){
                        if (person.getBankAccount().deposit(amount)){
                            frame.dispose();
                            userFrame.setVisible(true);
                            userScreen.refreshData(person);
                        } else {
                            JOptionPane.showMessageDialog(null, "Provide correct amount!", "Notification", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        if (person.getBankAccount().withdraw(amount)){
                            frame.dispose();
                            userFrame.setVisible(true);
                            userScreen.refreshData(person);
                        } else {
                            JOptionPane.showMessageDialog(null, "Provide correct amount!", "Notification", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "Provide numeric amount!", "Notification", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(amountLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        panel.add(amountField, constraints);

        constraints.gridwidth = 2;
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(submit, constraints);

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}


