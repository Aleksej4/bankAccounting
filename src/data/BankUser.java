package data;

import bankAccount.BankAccount;
import sql.SqlQueries;

import java.sql.Connection;

public abstract class BankUser extends Users {
    private final BankAccount bankAccount;
    private final SqlQueries sqlQueries = new SqlQueries();

    public BankUser(Connection connection, int id) {
        setId(id);
        bankAccount = new BankAccount(connection, id);
        setName(sqlQueries.getNameBankUser(connection, id));
        setLastName(sqlQueries.getLastNameBankUser(connection, id));
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }
}
