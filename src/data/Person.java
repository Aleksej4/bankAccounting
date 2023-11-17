package data;

import java.util.Scanner;

public class Person extends BankUser {
    private String personCode;

    public Person(String name, String lastName, String accountNumber) {
        super(name, lastName, accountNumber);
        setPersonCode(name.toLowerCase() + "123");
    }

    public String getPersonCode() {
        return personCode;
    }

    public void setPersonCode(String personCode) {
        this.personCode = personCode;
    }

    public boolean changePersonCode(String newPersonCode, String currentCode) {
        if (currentCode.equals(getPersonCode())) {
            setPersonCode(newPersonCode);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean logIn(String input) {
        return input.equals(getPersonCode());
    }
}
