package bankAccount;

import data.BankUser;

import java.util.HashMap;

public class Bank {
    private final HashMap<String, BankUser> accountUsers;
    private Integer idCounter = null;
    private final String PREFIX = "LT";

    public Bank() {
        this.accountUsers = new HashMap<>();
    }

    public HashMap<String, BankUser> getAccountUsers() {
        return accountUsers;
    }

    public String getPrefix() {
        return PREFIX;
    }

    public Integer getIdCounter() {
        return idCounter;
    }

    public void setIdCounter(Integer idCounter) {
        this.idCounter = idCounter;
    }

    public BankUser getAccountUser(String accountNumber) {
        return accountUsers.getOrDefault(accountNumber, null);
    }

    public void displayAccountUserInformation(String accountNumber) {
        System.out.println("\nFull name: " + getAccountUser(accountNumber).getFullName());
        System.out.println("Account number: " + getAccountUser(accountNumber).getBankAccount().getAccountNumber());
    }

    public void addAccountUser(BankUser accountUser) {
        accountUsers.put(accountUser.getBankAccount().getAccountNumber(), accountUser);
    }

    public boolean transferFunds(BankUser sourceAccountUser, String targetAccountNumber, int amount) {
        BankUser targetAccountUser = accountUsers.get(targetAccountNumber);

        if (targetAccountUser != null && sourceAccountUser.getBankAccount().withdraw(amount)) {
            targetAccountUser.getBankAccount().deposit(amount);
            return true;
        }
        return false;
    }
}
