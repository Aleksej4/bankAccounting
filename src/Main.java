import bankAccount.Account;
import bankAccount.Bank;
import data.Administrator;
import data.BankUser;
import displayData.HumanInteraction;

import java.util.Map;

public class Main {

    public static void banking(HumanInteraction humanInteraction, BankUser user, Bank bank) {
        int option = 1;
        Account account = user.getBankAccount();

        System.out.println("Welcome " + user.getFullName());
        System.out.println("Choose an option");
        while (!(option == 0)) {
            humanInteraction.displayBankingMenu();
            option = humanInteraction.getUserInputInteger();
            humanInteraction.getUserInputString();

            switch (option) {
                case 1:
                    humanInteraction.displayPersonalInfo(user, account);
                    break;
                case 2:
                    humanInteraction.displayCurrentBalance(account);
                    break;
                case 3:
                    humanInteraction.depositMoney(account);
                    break;
                case 4:
                    humanInteraction.withdrawMoney(account);
                    break;
                case 5:
                    humanInteraction.transferMoney(user, bank);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Choose right option!");
                    break;
            }
        }
    }

    public static void adminManaging(HumanInteraction humanInteraction, Administrator administrator, Bank bank) {
        int option = 1;

        System.out.println("Welcome " + administrator.getFullName());
        System.out.println("Choose an option");
        while (!(option == 0)) {
            System.out.println("1 - Personal information");
            System.out.println("2 - Create new bank user");
            System.out.println("3 - Show users list");
            System.out.println("4 - Delete user");
            System.out.println("0 - Return to accounts");
            option = humanInteraction.getUserInputInteger();
            humanInteraction.getUserInputString();

            switch (option) {
                case 1:
                    System.out.println("Full name: " + administrator.getFullName());
                    break;
                case 2:
                    System.out.println("Enter new user first name");
                    String firstName = humanInteraction.getUserInputString();
                    System.out.println("Enter last name");
                    String lastName = humanInteraction.getUserInputString();
                    administrator.addNewPersonAccountUser(bank, firstName, lastName);
                    break;
                case 3:
                    if (!(bank.getAccountUsers() == null)) {
                        for (Map.Entry<String, BankUser> entry : bank.getAccountUsers().entrySet()) {
                            bank.displayAccountUserInformation(entry.getKey());
                        }
                    } else {
                        System.out.println("There are no bank users yet");
                    }
                    break;
                case 4:
                    System.out.println("Enter account number you want to delete");
                    administrator.deleteUser(humanInteraction.getUserInputString(), bank);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Choose right option!");
                    break;
            }
        }
    }

    public static void main(String[] args) {
        Bank bank = new Bank();
        Administrator administrator = new Administrator("Admin", "adminLast");
        HumanInteraction humanInteraction = new HumanInteraction();

        int option = 1;
        while (!(option == 0)) {
            humanInteraction.displayPersonOptions();
            option = humanInteraction.getUserInputInteger();

            switch (option) {
                case 1:
                    System.out.println("Enter bank account number you want to log in");
                    humanInteraction.getUserInputString();
                    String accountNumber = humanInteraction.getUserInputString();
                    BankUser person = bank.getAccountUser(accountNumber);
                    if (!(person == null)) {
                        System.out.println("Enter password");
                        if (person.logIn(humanInteraction.getUserInputString())) {
                            banking(humanInteraction, person, bank);
                        } else {
                            System.out.println("Incorrect password");
                        }
                    } else {
                        System.out.println("This account does not exist");
                    }
                    break;
                case 2:
                    System.out.println("Enter password");
                    humanInteraction.getUserInputString();
                    String password = humanInteraction.getUserInputString();
                    if (administrator.logIn(password)) {
                        adminManaging(humanInteraction, administrator, bank);
                    } else {
                        System.out.println("Incorrect password");
                    }
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Choose right option!");
                    break;
            }
        }
    }
}