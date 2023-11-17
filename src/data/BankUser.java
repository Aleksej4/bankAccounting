package data;

import bankAccount.BankAccount;

public abstract class BankUser extends Users {
    private final BankAccount bankAccount;

    public BankUser(String name, String lastName, String accountNumber) {
        setName(name);
        setLastName(lastName);
        bankAccount = new BankAccount(accountNumber);
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }
}
