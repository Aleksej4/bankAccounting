package bankAccount;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BankAccount implements Account {
    private String accountNumber;
    private int balance = 0;
    private final String transactionLogPath = "C:\\Users\\Aleksej\\Desktop\\LogsBankingSystem\\";
    private String fileName;

    public BankAccount(String accountNumber) {
        setAccountNumber(accountNumber);
        setFileName();
    }

    private void setFileName() {
        this.fileName = accountNumber + ".csv";
    }

    private void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
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
            logTransactions("Deposited to the account: " + amount + "\nCurrent balance: " + balance + "\n");
            return true;
        }
        return false;
    }

    @Override
    public boolean withdraw(int amount) {
        if (amount <= balance && amount > 0) {
            balance -= amount;
            logTransactions("Withdraw from the account: " + amount + "\nCurrent balance: " + balance + "\n");
            return true;
        }
        return false;
    }

    @Override
    public void logTransactions(String log) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(transactionLogPath + fileName, true))) {
            writer.write(log + "Date: " + formattedDateTime + "\n\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
