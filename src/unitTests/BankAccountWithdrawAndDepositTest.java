package unitTests;

import bankAccount.BankAccount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sql.DatabaseLocal;
import sql.SqlQueries;

import java.sql.Connection;

class BankAccountWithdrawAndDepositTest {

    private BankAccount underTest;
    private Connection connection;

    private int bankAccountId;
    private int bankUserId;
    private SqlQueries sqlQueries;

    @BeforeEach
    void setUp(){
        sqlQueries = new SqlQueries();
        connection = DatabaseLocal.getConnection();
        bankUserId = 6;
        bankAccountId = sqlQueries.getBankAccountId(connection, bankUserId);
        underTest = new BankAccount(connection, bankUserId);
        underTest.updateBalance(100, bankAccountId);
    }

    @Test
    void itShouldPassWhenWithdrawIsPerformed(){
        boolean result = underTest.withdraw(100);

        Assertions.assertTrue(result);
    }

    @Test
    void itShouldFailWhenWithdrawIsPerformedWithNegativeAmount(){
        boolean result = underTest.withdraw(-1);

        Assertions.assertFalse(result);
    }

    @Test
    void itShouldFailWhenWithdrawIsPerformedWithZeroAmount(){
        boolean result = underTest.withdraw(0);

        Assertions.assertFalse(result);
    }

    @Test
    void itShouldFailWhenWithdrawIsPerformedWithInsufficientAmount(){
        boolean result = underTest.withdraw(101);

        Assertions.assertFalse(result);
    }

    @Test
    void itShouldPassWhenDepositPerformed(){
        boolean result = underTest.deposit(100);

        Assertions.assertTrue(result);
    }

    @Test
    void itShouldFailWhenDepositPerformedWithNegativeAmount(){
        boolean result = underTest.deposit(-1);

        Assertions.assertFalse(result);
    }

    @Test
    void itShouldFailWhenDepositPerformedWithZeroAmount(){
        boolean result = underTest.deposit(0);

        Assertions.assertFalse(result);
    }
}
