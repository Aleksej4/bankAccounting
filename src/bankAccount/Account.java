package bankAccount;

public interface Account {
    String getAccountNumber();

    void setAccountNumber();

    Integer getBalance();

    boolean deposit(int amount);

    boolean withdraw(int amount);

    boolean transferFunds(String accountNumber, int amount);
}
