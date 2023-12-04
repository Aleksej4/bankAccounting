package bankAccount;

import sql.SqlQueries;
import java.sql.Connection;


public class BankAccount implements Account {
    private String accountNumber;
    private final int bankAccountId;
    private int balance;
    private final SqlQueries sqlQueries = new SqlQueries();
    private final Connection connection;

    public BankAccount(Connection connection, int id) {
        this.connection = connection;
        this.bankAccountId = sqlQueries.getBankAccountId(connection, id);
        setAccountNumber();
        this.balance = sqlQueries.getBalance(connection, bankAccountId);
    }

    @Override
    public void setAccountNumber() {
        this.accountNumber = sqlQueries.getBankAccountNumber(connection, bankAccountId);
    }

    @Override
    public String getAccountNumber() {
        return accountNumber;
    }

    @Override
    public Integer getBalance() {
        return balance;
    }

    @Override
    public boolean deposit(int amount) {
        if (amount > 0) {
            balance += amount;
            return updateBalance(balance, this.bankAccountId);
        }
        return false;
    }

    @Override
    public boolean withdraw(int amount) {
        if (amount <= balance && amount > 0) {
            balance -= amount;
            return updateBalance(balance, this.bankAccountId);
        }
        return false;
    }

    public boolean updateBalance(int amount, int id) {
        if(sqlQueries.updateAccountBalance(connection, amount, id)){
            this.balance = sqlQueries.getBalance(connection, bankAccountId);
            return true;
        }

        return false;
    }

    @Override
    public boolean transferFunds(String accountNumber, int amount) {
        int accountId = sqlQueries.getBankAccountIdByNumber(connection, accountNumber);

        if (accountId == -1)
            return false;

        if(amount > 0 && amount <= balance){
            //balance = balance - amount;
            if (updateBalance(balance - amount, this.bankAccountId)){
                return updateBalance(sqlQueries.getBalance(connection, accountId) + amount, accountId);
            }
        }

        return false;
    }
}
