import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;


public class BibliotecaAppTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final String welcomeMessage = "Welcome to Biblioteca. " +
            "Your one-stop-shop for great book titles in Bangalore!\n";
    private final String initialBookListString =
            "id=1|title=Book A|author=Dafu|year=2010|stock=3\n" +
                    "id=2|title=Book B|author=Dafu|year=2011|stock=2\n" +
                    "id=3|title=Book C|author=Dafu|year=2012|stock=1\n";
    private final String menuOptions = "Enter a number from options below to make a " +
            "selection: \n" +
            "1 - List of books\n";

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    /**
     * Test we should see a welcome message when we start the application
     */
    @Test
    public void testShouldPrintWelcomeMessageWhenStartApplication() {
        BibliotecaApp.main(null);

        assertTrue(outContent.toString().startsWith(welcomeMessage));
    }

    /**
     * Test we should return correct string from book list
     */
    @Test
    public void testShouldReturnCorrectStringFromBookList() {
        assertEquals(initialBookListString, BibliotecaApp.getAllBooksString());
    }

    /**
     * Test we should return correct menu options string
     */
    @Test
    public void testShouldReturnCorrectMenuString() {
        assertEquals(menuOptions, BibliotecaApp.getMenuOptions());
    }

    /**
     * Test we should return print menu options after welcome message
     */
    @Test
    public void testShouldPrintMenuAfterWelcomeMessage() {
        BibliotecaApp.main(null);

        // By removing the welcome message the menu options string should be at
        // the beginning
        assertEquals(menuOptions,
                outContent.toString().replace(welcomeMessage, ""));
    }

    /**
     * Test we should print book list after we select correct menu option
     */
    @Test
    public void testShouldPrintBookListIfSelectCorrectOption() {
        // Provide correct menu option input
        ByteArrayInputStream input = new ByteArrayInputStream("1".getBytes());
        System.setIn(input);

        BibliotecaApp.main(null);

        // By removing the welcome message & menu options, book list should be
        // at the beginning
        assertEquals(initialBookListString,
                outContent.toString()
                        .replace(welcomeMessage, "")
                        .replace(menuOptions, ""));
    }
}
