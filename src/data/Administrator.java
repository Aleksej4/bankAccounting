package data;

import bankAccount.Bank;

import java.util.Scanner;

public class Administrator extends Users {

    private final String adminCode = "XX123";

    public Administrator(String name, String lastName) {
        setName(name);
        setLastName(lastName);
    }

    public void addNewPersonAccountUser(Bank bank, String name, String lastName) {
        String accountNumber = generateAccountNumber(bank);
        Person person = new Person(name, lastName, accountNumber);
        bank.addAccountUser(person);
        bank.displayAccountUserInformation(accountNumber);
    }

    public void deleteUser(String accountNumber, Bank bank) {
        if (bank.getAccountUsers().containsKey(accountNumber)) {
            bank.getAccountUsers().remove(accountNumber);
            System.out.println("Account remove successfully");
        } else {
            System.out.println("Account number does not exists");
        }
    }

    private String generateAccountNumber(Bank bank) {
        String prefix = bank.getPrefix();
        String newAccountNumber;

        if (bank.getIdCounter() == null) {
            newAccountNumber = prefix + "00000";
            bank.setIdCounter(1);
        } else {
            int idCounter = bank.getIdCounter();
            newAccountNumber = prefix + String.format("%05d", idCounter);
            idCounter++;
            bank.setIdCounter(idCounter);
        }
        return newAccountNumber;
    }

    @Override
    public boolean logIn(String input) {
        return input.equals(adminCode);
    }
}
