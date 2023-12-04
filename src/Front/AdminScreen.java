package Front;

import data.Administrator;
import data.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AdminScreen {

    private JFrame frame;
    private JLabel nameLabel;

    public AdminScreen(Administrator administrator, JFrame logInFrame) {
        frame = new JFrame("Account Information");
        frame.setSize(500, 200);
        frame.setLayout(new BorderLayout(10, 10));

        JPanel infoPanel = new JPanel(new GridLayout(3, 1, 5, 5));

        nameLabel = new JLabel("Full Name: " + administrator.getFullName());
        JLabel roleLabel = new JLabel("Role: Administrator");

        infoPanel.add(nameLabel);
        infoPanel.add(roleLabel);

        JPanel actionPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        JButton createBankUser = new JButton("Create bank user");
        JButton deleteBankUser = new JButton("Delete bank user");

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                logInFrame.setVisible(true);
            }
        });

        createBankUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateUserScreen createUserScreen = new CreateUserScreen(administrator, frame);
            }
        });

        deleteBankUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeleteUserScreen deleteUserScreen = new DeleteUserScreen(administrator, frame);
            }
        });


        actionPanel.add(createBankUser);
        actionPanel.add(deleteBankUser);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(infoPanel, BorderLayout.CENTER);
        contentPanel.add(actionPanel, BorderLayout.SOUTH);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        frame.add(contentPanel);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
