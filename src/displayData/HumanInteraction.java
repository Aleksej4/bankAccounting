package displayData;

import bankAccount.Account;
import bankAccount.Bank;
import data.BankUser;

import java.util.Scanner;

public class HumanInteraction {

    private final Scanner scanner = new Scanner(System.in);

    public int getUserInputInteger() {
        int input;
        while (true) {
            try {
                input = scanner.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Incorrect number format!!");
                scanner.nextLine();
            }
        }
        return input;
    }

    public String getUserInputString() {
        return scanner.nextLine();
    }

    public void displayPersonOptions() {
        System.out.println("Choose an option:");
        System.out.println("1 - Log in as bank user");
        System.out.println("2 - Log in as administrator");
        System.out.println("0 - Exit");
    }

    public void displayBankingMenu() {
        System.out.println("1 - Personal information");
        System.out.println("2 - Current balance");
        System.out.println("3 - Deposit money");
        System.out.println("4 - Withdraw money");
        System.out.println("5 - Transfer money");
        System.out.println("0 - Return to accounts");
    }

    public void displayPersonalInfo(BankUser user, Account account) {
        System.out.println("Full name: " + user.getFullName());
        System.out.println("Account number: " + account.getAccountNumber());
    }

    public void displayCurrentBalance(Account account) {
        System.out.println("Current balance: " + account.getBalance());
    }

    public void depositMoney(Account account) {
        System.out.println("Enter the amount to deposit: ");
        int amount = getUserInputInteger();
        scanner.nextLine();

        if (account.deposit(amount)) {
            System.out.println("Deposit successful. Current balance: " + account.getBalance());
        } else {
            System.out.println("Invalid amount!");
        }
    }

    public void withdrawMoney(Account account) {
        System.out.println("Enter the amount to withdraw: ");
        int amount = getUserInputInteger();
        scanner.nextLine();

        if (account.withdraw(amount)) {
            System.out.println("Withdrawal successful. Current balance: " + account.getBalance());
        } else {
            System.out.println("Invalid amount or insufficient balance!");
        }
    }

    public void transferMoney(BankUser user, Bank bank) {
        System.out.println("Enter the account number to transfer: ");
        String targetAccountNumber = scanner.nextLine();

        System.out.println("Enter the amount to transfer: ");
        int amount = getUserInputInteger();
        scanner.nextLine();

        if (bank.transferFunds(user, targetAccountNumber, amount)) {
            System.out.println("Transfer successful");
        } else {
            System.out.println("Transfer failed. Check account number or insufficient funds.");
        }
    }
}
