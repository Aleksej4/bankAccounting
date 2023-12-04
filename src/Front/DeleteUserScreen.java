package Front;

import data.Administrator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DeleteUserScreen {
    private JFrame frame;
    private JTextField accountNumberField;

    public DeleteUserScreen(Administrator administrator, JFrame mainFrame) {
        mainFrame.setVisible(false);
        frame = new JFrame("Delete user");
        frame.setSize(500, 200);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        JLabel accountNumberLabel = new JLabel("Account number (LTXXXXX):");
        accountNumberField = new JTextField(15);

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String accountNumber = accountNumberField.getText();

                if (!administrator.deleteUser(accountNumber)) {
                    JOptionPane.showMessageDialog(null, "Account number does not exists or incorrect format", "Notification", JOptionPane.ERROR_MESSAGE);
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
        panel.add(accountNumberLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        panel.add(accountNumberField, constraints);

        constraints.gridwidth = 2;
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(deleteButton, constraints);

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
