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
    private final String menuOptions = "Enter an option below:\n" +
            "1 - List of books\n" +
            "c[book id] - Checkout a book\n" +
            "r[book id] - Return a book\n" +
            "0 - Quit\n";
    private final String checkoutSuccessMessage = "Thank you! Enjoy the book\n";
    private final String checkoutFailureMessage = "Sorry, that book is not available\n";
    private final String returnSuccessMessage = "Thank you for returning the book\n";
    private final String returnFailureMessage = "That is not a valid book to return\n";
    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    /**
     * We need to reset book data for next test
     * as it may has been mutated in the previous test
     */
    @After
    public void resetBookData() {
        BookManager.getInstance().initialiseBookData();
    }

    /**
     * Test we should see a welcome message when we start the application
     */
    @Test
    public void testShouldPrintWelcomeMessageWhenStartApplication() {
        // Provide a valid menu option to quit
        ByteArrayInputStream input = new ByteArrayInputStream("0".getBytes());
        System.setIn(input);

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
        // Provide a valid menu option to quit
        ByteArrayInputStream input = new ByteArrayInputStream("0".getBytes());
        System.setIn(input);

        BibliotecaApp.main(null);

        // By removing the welcome message the menu options string should be at
        // the beginning
        assertTrue(outContent.toString()
                        .replace(welcomeMessage, "")
                        .startsWith(menuOptions));
    }

    /**
     * Test we should print book list after we select correct menu option
     */
    @Test
    public void testShouldPrintBookListIfSelectCorrectOption() {
        // Provide correct menu option input
        // followed by selecting quit option
        ByteArrayInputStream input = new ByteArrayInputStream("1\n0\n".getBytes());
        System.setIn(input);

        BibliotecaApp.main(null);

        // By removing the welcome message & menu options, book list should be
        // at the beginning
        assertEquals(initialBookListString,
                outContent.toString()
                        .replace(welcomeMessage, "")
                        .replace(menuOptions, ""));
    }

    /**
     * Test we should print error message if incorrect option is selected
     */
    @Test
    public void testShouldPrintErrorIfSelectIncorrectOption() {
        // Provide an invalid menu option input to trigger error msg
        // followed by selecting quit option
        ByteArrayInputStream input = new ByteArrayInputStream("2\n0\n".getBytes());
        System.setIn(input);

        BibliotecaApp.main(null);

        // By removing the welcome message & menu options, error msg should be
        // at the beginning
        assertTrue(outContent.toString()
                .replace(welcomeMessage, "")
                .replace(menuOptions, "")
                .startsWith("Please select a valid option!\n")
        );
    }

    /**
     * Test we are able to checkout a book successfully when stock is sufficient
     * (and book id is valid)
     */
    @Test
    public void testShouldAbleToCheckoutBookSuccessfullyWhenStockSufficient() {
        // Provide an valid menu option input to trigger checking out a book
        // then quit
        ByteArrayInputStream input = new ByteArrayInputStream("1\nc1\n0\n".getBytes());
        System.setIn(input);

        BibliotecaApp.main(null);

        // By removing the welcome message, menu options and initial book list
        // we should have the success message left
        assertEquals(checkoutSuccessMessage,
                outContent.toString()
                .replace(welcomeMessage, "")
                .replace(menuOptions, "")
                .replace(initialBookListString, "")
        );
    }

    /**
     * Test we are not able to checkout a book when stock is insufficient
     */
    @Test
    public void testShouldNotBeAbleToCheckoutBookWhenStockInsufficient() {
        // Provide an invalid menu option input to trigger checking out a book
        // and checkout again (at this time the stock will be insufficient)
        // then quit
        ByteArrayInputStream input = new ByteArrayInputStream("1\nc3\nc3\n0\n".getBytes());
        System.setIn(input);

        BibliotecaApp.main(null);

        // By removing the welcome message, menu options and initial book list
        // we should have the failure message left
        assertEquals(checkoutFailureMessage,
                outContent.toString()
                        .replace(welcomeMessage, "")
                        .replace(menuOptions, "")
                        .replace(initialBookListString, "")
                        .replace(checkoutSuccessMessage, "")
        );
    }

    /**
     * Test we are not able to checkout a book when book id is invalid
     */
    @Test
    public void testShouldNotBeAbleToCheckoutBookWhenBookIdInvalid() {
        // Provide invalid menu option input to trigger checking out a book
        // one being book id does not exist
        // other one being invalid book id format (i.e. non-int)
        // then quit
        ByteArrayInputStream input = new ByteArrayInputStream("1\nc0\nca\n0\n".getBytes());
        System.setIn(input);

        BibliotecaApp.main(null);

        // By removing the welcome message, menu options and initial book list
        // we should have two failure messages left
        assertEquals(checkoutFailureMessage + checkoutFailureMessage,
                outContent.toString()
                        .replace(welcomeMessage, "")
                        .replace(menuOptions, "")
                        .replace(initialBookListString, "")
        );
    }

    /**
     * Test we are able to checkout a book successfully
     * (when book id is valid)
     */
    @Test
    public void testShouldAbleToCheckoutBookSuccessfully() {
        // Provide an valid menu option input to trigger checking out a book
        // then quit
        ByteArrayInputStream input = new ByteArrayInputStream("1\nr1\n0\n".getBytes());
        System.setIn(input);

        BibliotecaApp.main(null);

        // By removing the welcome message, menu options and initial book list
        // we should have the success message left
        assertEquals(returnSuccessMessage,
                outContent.toString()
                        .replace(welcomeMessage, "")
                        .replace(menuOptions, "")
                        .replace(initialBookListString, "")
        );
    }

    /**
     * Test we are not able to return a book when book id is invalid
     */
    @Test
    public void testShouldNotBeAbleToReturnBookWhenBookIdInvalid() {
        // Provide invalid menu option input to trigger returning a book
        // one being book id does not exist
        // other one being invalid book id format (i.e. non-int)
        // then quit
        ByteArrayInputStream input = new ByteArrayInputStream("1\nr0\nra\n0\n".getBytes());
        System.setIn(input);

        BibliotecaApp.main(null);

        // By removing the welcome message, menu options and initial book list
        // we should have two failure messages left
        assertEquals(returnFailureMessage + returnFailureMessage,
                outContent.toString()
                        .replace(welcomeMessage, "")
                        .replace(menuOptions, "")
                        .replace(initialBookListString, "")
        );
    }

    /**
     * Test we are able to return a book successfully
     * (when book id is valid)
     */
    @Test
    public void testShouldBeAbleToReturnBookSuccessfully() {
        // Provide an valid menu option input to trigger returning a book
        // then quit
        ByteArrayInputStream input = new ByteArrayInputStream("1\nr1\n0\n".getBytes());
        System.setIn(input);

        BibliotecaApp.main(null);

        // By removing the welcome message, menu options and initial book list
        // we should have the success message left
        assertEquals(returnSuccessMessage,
                outContent.toString()
                        .replace(welcomeMessage, "")
                        .replace(menuOptions, "")
                        .replace(initialBookListString, "")
        );
    }
}
