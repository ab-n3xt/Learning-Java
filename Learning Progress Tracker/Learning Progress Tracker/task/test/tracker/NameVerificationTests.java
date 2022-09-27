package tracker;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

public class NameVerificationTests {

    @Test
    @DisplayName("Given name 'A', result is false.")
    public void nameWithLessThanTwoCharacters() {
        String name = "A";
        assertFalse(Main.correctFirstName(name));
    }

    @Test
    @DisplayName("Given name 'Ai', result is true.")
    public void nameWithTwoCharactersOrMore() {
        String name = "Ai";
        assertTrue(Main.correctFirstName(name));
    }

    @Test
    @DisplayName("Given name '-Ai', result is false.")
    public void nameWithOneStartingHyphen() {
        String name = "-Ai";
        assertFalse(Main.correctFirstName(name));
    }

    @Test
    @DisplayName("Given name 'Ai-', result is false.")
    public void nameWithOneEndingHyphen() {
        String name = "Ai-";
        assertFalse(Main.correctFirstName(name));
    }

    @Test
    @DisplayName("Given name ''Ai', result is false.")
    public void nameWithOneStartingApostrophe() {
        String name = "'Ai";
        assertFalse(Main.correctFirstName(name));
    }

    @Test
    @DisplayName("Given name 'Ai'', result is false.")
    public void nameWithOneEndingApostrophe() {
        String name = "Ai'";
        assertFalse(Main.correctFirstName(name));
    }

    @Test
    @DisplayName("Given name 'Ab-Soul', result is true.")
    public void nameWithOneMiddleHyphen() {
        String name = "Ab-Soul";
        assertTrue(Main.correctFirstName(name));
    }

    @Test
    @DisplayName("Given name 'Ab-So-ul', result is true.")
    public void nameWithMoreThanOneMiddleHyphen() {
        String name = "Ab-So-ul";
        assertTrue(Main.correctFirstName(name));
    }

    @Test
    @DisplayName("Given name 'Ab--Soul', result is false.")
    public void nameWithTwoHyphensInARow() {
        String name = "Ab--Soul";
        assertFalse(Main.correctFirstName(name));
    }

    @Test
    @DisplayName("Given name 'O'Neil', result is true.")
    public void nameWithOneMiddleApostrophe() {
        String name = "O'Neil";
        assertTrue(Main.correctFirstName(name));
    }

    @Test
    @DisplayName("Given name 'O'Neil'Ana', result is true.")
    public void nameWithMoreThanOneMiddleApostrophe() {
        String name = "O'Neil'Ana";
        assertTrue(Main.correctFirstName(name));
    }

    @Test
    @DisplayName("Given name 'O''Neil', result is false.")
    public void nameWithTwoApostrophesInARow() {
        String name = "O''Neil";
        assertFalse(Main.correctFirstName(name));
    }

    @Test
    @DisplayName("Given name 'O-'Neil', result is false.")
    public void nameWithAnApostropheAndHyphenTogether() {
        String name = "O-'Neil";
        assertFalse(Main.correctFirstName(name));
    }

    @Test
    @DisplayName("Given 'aidan@gmail.ca', result is true.")
    public void emailWithNormalStructure() {
        String emailAddress = "aidan@gmail.ca";
        assertTrue(Main.correctEmailAddress(emailAddress));
    }

    @Test
    @DisplayName("Given '@gmail.ca', result is false.")
    public void emailWithoutName() {
        String emailAddress = "@gmail.ca";
        assertFalse(Main.correctEmailAddress(emailAddress));
    }

    @Test
    @DisplayName("Given 'aidangmail.ca', result is false.")
    public void emailWithoutAtCharacter() {
        String emailAddress = "aidangmail.ca";
        assertFalse(Main.correctEmailAddress(emailAddress));
    }

    @Test
    @DisplayName("Given 'aidan@.ca', result is false.")
    public void emailWithoutDomainName() {
        String emailAddress = "aidan@.ca";
        assertFalse(Main.correctEmailAddress(emailAddress));
    }

    @Test
    @DisplayName("Given 'aidan@gmailca', result is false.")
    public void emailWithoutDotCharacter() {
        String emailAddress = "aidan@gmailca";
        assertFalse(Main.correctEmailAddress(emailAddress));
    }

    @Test
    @DisplayName("Given 'aidan@gmail.', result is false.")
    public void emailWithoutDomainEnding() {
        String emailAddress = "aidan@gmail.";
        assertFalse(Main.correctEmailAddress(emailAddress));
    }
}