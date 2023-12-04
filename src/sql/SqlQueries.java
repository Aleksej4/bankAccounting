package sql;

import java.sql.*;

public class SqlQueries {

    public int getUserId(Connection connection, String username){
        String query = "SELECT Id FROM users WHERE Username = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("Id");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return -1;
    }

    public Boolean passwordCheck(Connection connection, int id, String password){
        String query = "SELECT * FROM users WHERE Id = ? AND Password = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int userType(Connection connection, int id){
        String query = "SELECT UserTypeId FROM users WHERE Id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("UserTypeId");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return -1;
    }

    public int getBankAccountId(Connection connection, int id){
        String query = "SELECT BankAccountId FROM bankuser WHERE Id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("BankAccountId");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return -1;
    }

    public String getBankAccountNumber(Connection connection, int id){
        String query = "SELECT AccountNumber FROM bankaccount WHERE Id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("AccountNumber");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public String getNameBankUser(Connection connection, int id){
        String query = "SELECT FirstName FROM bankuser WHERE Id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("FirstName");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public String getLastNameBankUser(Connection connection, int id){
        String query = "SELECT LastName FROM bankuser WHERE Id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("LastName");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public int getBalance(Connection connection, int id){
        String query = "SELECT Balance FROM bankaccount WHERE Id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("Balance");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return -1;
    }

    public int getBankUserIdByAccountNumber(Connection connection, String accountNumber){
        int backAccountId = getBankAccountIdByNumber(connection, accountNumber);

        String query = "SELECT Id FROM bankuser WHERE BankAccountId = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, backAccountId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("Id");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return -1;
    }

    public int getBankAccountIdByNumber(Connection connection, String accountNumber){
        String query = "SELECT Id FROM bankaccount WHERE AccountNumber = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, accountNumber);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("Id");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return -1;
    }

    public Boolean updateAccountBalance(Connection connection, int balance, int id){
        String query = "UPDATE bankaccount SET Balance = ? WHERE Id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, balance);
            preparedStatement.setInt(2, id);

            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    public String getNameAdministrator(Connection connection, int id){
        String query = "SELECT FirstName FROM administrator WHERE Id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("FirstName");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public String getLastNameAdministrator(Connection connection, int id){
        String query = "SELECT LastName FROM administrator WHERE Id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("LastName");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public Boolean checkBankAccount(Connection connection, String accountNumber){
        String query = "SELECT * FROM bankaccount WHERE AccountNumber = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, accountNumber);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }
    public int createBankAccount(Connection connection, String accountNumber){
        if (checkBankAccount(connection, accountNumber))
            return -1;

        String query = "INSERT INTO bankaccount (AccountNumber) VALUES (?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, accountNumber);
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0){
                return -1;
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return -1;
    }

    public int createBankUserLogIn(Connection connection, String firstName, String lastName){
        String username = firstName.toLowerCase();
        String password = lastName.toLowerCase();

        String query = "INSERT INTO users (Username, Password, UserTypeId) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setInt(3, 1);
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0){
                return -1;
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return -1;
    }

    public Boolean createBankUser(Connection connection, String firstName, String lastName, int userId, int bankAccountId){
        String query = "INSERT INTO bankuser (Id, FirstName, LastName, BankAccountId) VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2, firstName);
            preparedStatement.setString(3, lastName);
            preparedStatement.setInt(4, bankAccountId);

            int affectedRows = preparedStatement.executeUpdate();

            return affectedRows != 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int deleteBankUser(Connection connection, String bankAccountNumber){
        if (!checkBankAccount(connection, bankAccountNumber))
            return -1;

        int id = getBankUserIdByAccountNumber(connection, bankAccountNumber);

        int bankAccountId = getBankAccountIdByNumber(connection, bankAccountNumber);

        String query = "DELETE FROM bankuser WHERE BankAccountId = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, bankAccountId);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows != 0) {
                return id;
            }

            return -1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Boolean deleteBankUserLogIn(Connection connection, int id){
        String query = "DELETE FROM users WHERE Id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);

            int affectedRows = preparedStatement.executeUpdate();

            return affectedRows != 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Boolean deleteBankAccount(Connection connection, String accountNumber){
        String query = "DELETE FROM bankaccount WHERE AccountNumber = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, accountNumber);

            int affectedRows = preparedStatement.executeUpdate();

            return affectedRows != 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
