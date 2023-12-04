package unitTests;

import bankAccount.BankAccount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sql.DatabaseLocal;
import sql.SqlQueries;

import java.sql.Connection;

class BankAccountTransferFundsTest {
    private BankAccount underTest;
    private Connection connection;
    private int bankAccountId;
    private int bankUserId;
    private SqlQueries sqlQueries;
    private String accountNumber;

    @BeforeEach
    void setUp(){
        sqlQueries = new SqlQueries();
        connection = DatabaseLocal.getConnection();
        bankUserId = 6;
        bankAccountId = sqlQueries.getBankAccountId(connection, bankUserId);
        underTest = new BankAccount(connection, bankUserId);
        underTest.updateBalance(100, bankAccountId);
        accountNumber = "LT00004";
    }

    @Test
    void itShouldPassWhenTransferIsPerformed(){
        boolean result = underTest.transferFunds(accountNumber, 100);

        Assertions.assertTrue(result);
    }

    @Test
    void itShouldFailWhenTransferIsPerformedWithInsufficientAmount(){
        boolean result = underTest.transferFunds(accountNumber, 101);

        Assertions.assertFalse(result);
    }

    @Test
    void itShouldFailWhenTransferIsPerformedWithZeroAmount(){
        boolean result = underTest.transferFunds(accountNumber, 0);

        Assertions.assertFalse(result);
    }

    @Test
    void itShouldFailWhenTransferIsPerformedWithNegativeAmount(){
        boolean result = underTest.transferFunds(accountNumber, -1);

        Assertions.assertFalse(result);
    }

    @Test
    void itShouldFailWhenTransferIsPerformedWithIncorrectAccountNUmber(){
        boolean result = underTest.transferFunds("LT01238", 30);

        Assertions.assertFalse(result);
    }

    @Test
    void itShouldFailWhenTransferIsPerformedWithEmptyAccountData(){
        boolean result = underTest.transferFunds("", 30);

        Assertions.assertFalse(result);
    }
}
