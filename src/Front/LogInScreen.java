package Front;

import data.Administrator;
import data.Person;
import sql.SqlQueries;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class LogInScreen {
    private JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;

    private Connection connection;

    private final SqlQueries sqlQueries = new SqlQueries();

    public LogInScreen(Connection connection) {
        this.connection = connection;
        frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 150);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField(15);

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(15);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = String.valueOf(passwordField.getPassword());
                logIn(username, password);
            }
        });

        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(usernameLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        panel.add(usernameField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(passwordLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        panel.add(passwordField, constraints);

        constraints.gridwidth = 2;
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(loginButton, constraints);

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public void logIn(String username, String password){
        int user = userExists(username);
        if (user != -1){
            if (correctPassword(password, user)){
                switch (sqlQueries.userType(connection, user)){
                    case 1:
                        Person person = new Person(user, connection);
                        frame.setVisible(false);
                        UserScreen userScreen = new UserScreen(person, frame);
                        break;
                    case 2:
                        Administrator administrator = new Administrator(user, connection);
                        frame.setVisible(false);
                        AdminScreen adminScreen = new AdminScreen(administrator, frame);
                        break;
                    default:
                        break;
                }

            }else {
                JOptionPane.showMessageDialog(null, "Incorrect password!", "Notification", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Account user does not exists!", "Notification", JOptionPane.ERROR_MESSAGE);
        }
    }

    public int userExists(String username){
        return sqlQueries.getUserId(connection, username);
    }

    public Boolean correctPassword(String password, int id){
        return sqlQueries.passwordCheck(connection, id, password);
    }
}

