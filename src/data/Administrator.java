package data;

import sql.SqlQueries;
import java.sql.Connection;


public class Administrator extends Users {
    private final Connection connection;
    private final SqlQueries sqlQueries = new SqlQueries();

    public Administrator(int id, Connection connection) {
        setId(id);
        this.connection = connection;
        setName(sqlQueries.getNameAdministrator(connection, id));
        setLastName(sqlQueries.getLastNameAdministrator(connection, id));
    }

    public Boolean addNewPersonAccountUser(String accountNumber, String firstName, String lastName) {

        if (firstName.equals("") || lastName.equals("") || accountNumber.equals("")){
            return false;
        } else if (!accountNumber.startsWith("LT")){
            return false;
        } else {
            int bankAccountId = sqlQueries.createBankAccount(connection, accountNumber);

            if (bankAccountId == -1)
                return false;

            int userLogInId = sqlQueries.createBankUserLogIn(connection, firstName, lastName);

            if (userLogInId == -1)
                return false;

            return sqlQueries.createBankUser(connection, firstName, lastName, userLogInId, bankAccountId);
        }
    }

    public Boolean deleteUser(String accountNumber) {
        int userId = sqlQueries.deleteBankUser(connection, accountNumber);

        if (userId == -1) {
            return false;
        }

        if (sqlQueries.deleteBankUserLogIn(connection, userId)){
            return sqlQueries.deleteBankAccount(connection, accountNumber);
        }

        return false;
    }
}
