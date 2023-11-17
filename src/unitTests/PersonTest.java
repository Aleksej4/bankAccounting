package unitTests;

import data.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PersonTest {

    private Person underTest;

    @BeforeEach
    void beforeEach() {
        underTest = new Person("TestName", "TestLastName", "TestableAccountNumber");
    }

    @Test
    void setPersonCodeSuccessfullyTest() {
        String newPersonCode = "codeExample";
        boolean check = underTest.changePersonCode(newPersonCode, underTest.getPersonCode());
        Assertions.assertTrue(check);

    }

    @Test
    void setPersonCodeUnsuccessfullyTest() {
        String newPersonCode = "codeExample";
        boolean check = underTest.changePersonCode(newPersonCode, "anyCode");
        Assertions.assertFalse(check);
    }

    @Test
    void logInSuccessfullyTest() {
        String input = underTest.getPersonCode();
        Assertions.assertTrue(underTest.logIn(input));
    }

    @Test
    void logInUnsuccessfullyTest() {
        String input = "wrongPassword";
        Assertions.assertFalse(underTest.logIn(input));
    }
}