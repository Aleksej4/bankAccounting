package Front;

import data.Administrator;
import sql.SqlQueries;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;

public class CreateUserScreen {
    private JFrame frame;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField accountNumberField;

    public CreateUserScreen(Administrator administrator, JFrame mainFrame) {
        mainFrame.setVisible(false);
        frame = new JFrame("Create user");
        frame.setSize(500, 200);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        JLabel firstNameLabel = new JLabel("First name:");
        firstNameField = new JTextField(15);

        JLabel lastNameLabel = new JLabel("Last name:");
        lastNameField = new JTextField(15);

        JLabel accountNumberLabel = new JLabel("Account number (LTXXXXX):");
        accountNumberField = new JTextField(15);

        JButton createButton = new JButton("Create");
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String accountNumber = accountNumberField.getText();

                if (!administrator.addNewPersonAccountUser(accountNumber, firstName, lastName)){
                    JOptionPane.showMessageDialog(null, "Incorrect data, check all provided data and if new account number does not exists", "Notification", JOptionPane.ERROR_MESSAGE);
                } else {
                    frame.dispose();
                    mainFrame.setVisible(true);
                }
            }
        });

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                mainFrame.setVisible(true);
            }
        });

        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(firstNameLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        panel.add(firstNameField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(lastNameLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        panel.add(lastNameField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(accountNumberLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        panel.add(accountNumberField, constraints);

        constraints.gridwidth = 2;
        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(createButton, constraints);

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
