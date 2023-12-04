package unitTests;

import data.Administrator;
import org.junit.jupiter.api.*;
import sql.DatabaseLocal;

import java.sql.Connection;

class AdministratorDeleteUserTest {

    private Administrator underTest;
    private int adminId;
    private Connection connection;

    @BeforeEach
    void setUp(){
        connection = DatabaseLocal.getConnection();
        adminId = 3;
        underTest = new Administrator(adminId, connection);
    }

    @Test
    void itShouldPassDeletingBankUser(){
        String accountNumber = "LT11119";

        underTest.addNewPersonAccountUser(accountNumber, "TestingFirstName", "TestingLastName");
        Boolean result = underTest.deleteUser(accountNumber);

        Assertions.assertTrue(result);
    }

    @Test
    void itShouldFailProvidingNonExistingAccountNumber(){
        String accountNumber = "LT11119";

        Boolean result = underTest.deleteUser(accountNumber);

        Assertions.assertFalse(result);
    }

    @Test
    void itShouldFailProvidingEmptyData(){
        String accountNumber = "";

        Boolean result = underTest.deleteUser(accountNumber);

        Assertions.assertFalse(result);
    }
}
