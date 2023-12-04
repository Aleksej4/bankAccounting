package data;

import java.sql.Connection;

public class Person extends BankUser {

    public Person(int userId, Connection connection) {
        super(connection, userId);
    }
}
