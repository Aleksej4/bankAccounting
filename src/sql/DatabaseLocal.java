package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseLocal {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/bankaccounting";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    private static Connection connection = null;

    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            System.out.println("Connected to the database!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to connect to the database!");
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("DatabaseLocal connection closed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
