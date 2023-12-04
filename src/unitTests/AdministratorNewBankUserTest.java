package unitTests;

import data.Administrator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sql.DatabaseLocal;

import java.sql.Connection;

class AdministratorNewBankUserTest {

    private Administrator underTest;
    private int adminId;
    private Connection connection;


    @BeforeEach
    void setUp(){
        connection = DatabaseLocal.getConnection();
        adminId = 3;
        underTest = new Administrator(adminId, connection);
        underTest.deleteUser("LT11119");
    }

    @Test
    void itShouldPassAddingNewBankUser(){
        String accountNumber = "LT11119";
        String firstName = "TestingFirstName";
        String lastName = "TestingLastName";

        Boolean result = underTest.addNewPersonAccountUser(accountNumber, firstName, lastName);

        Assertions.assertTrue(result);
    }

    @Test
    void itShouldFailAddingNewBankUserWithSameAccountNumber(){
        String accountNumber = "LT11119";
        String firstName = "TestingFirstName";
        String lastName = "TestingLastName";

        underTest.addNewPersonAccountUser(accountNumber, firstName, lastName);
        Boolean result = underTest.addNewPersonAccountUser(accountNumber, firstName, lastName);

        Assertions.assertFalse(result);
    }

    @Test
    void itShouldFailAddingNewBankUserWithWrongPrefixOfAccountNumber(){
        String accountNumber = "EN11119";
        String firstName = "TestingFirstName";
        String lastName = "TestingLastName";

        Boolean result = underTest.addNewPersonAccountUser(accountNumber, firstName, lastName);

        Assertions.assertFalse(result);
    }

    @Test
    void itShouldFailAddingNewBankUserWithEmptyNameData(){
        String accountNumber = "LT11119";
        String firstName = "";
        String lastName = "TestingLastName";

        Boolean result = underTest.addNewPersonAccountUser(accountNumber, firstName, lastName);

        Assertions.assertFalse(result);
    }

    @Test
    void itShouldFailAddingNewBankUserWithEmptyAccountNumberData(){
        String accountNumber = "";
        String firstName = "TestingFirstName";
        String lastName = "TestingLastName";

        Boolean result = underTest.addNewPersonAccountUser(accountNumber, firstName, lastName);

        Assertions.assertFalse(result);
    }

    @Test
    void itShouldFailAddingNewBankUserWithEmptyLastNameData(){
        String accountNumber = "LT11119";
        String firstName = "TestingFirstName";
        String lastName = "";

        Boolean result = underTest.addNewPersonAccountUser(accountNumber, firstName, lastName);

        Assertions.assertFalse(result);
    }

    @Test
    void itShouldFailAddingNewBankUserWithEmptyData(){
        String accountNumber = "";
        String firstName = "";
        String lastName = "";

        Boolean result = underTest.addNewPersonAccountUser(accountNumber, firstName, lastName);

        Assertions.assertFalse(result);
    }
}
