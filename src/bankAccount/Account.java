package bankAccount;

public interface Account {
    String getAccountNumber();

    Integer getBalance();

    boolean deposit(int amount);

    boolean withdraw(int amount);

    void logTransactions(String log);
}
