import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;


public class BibliotecaAppTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final String welcomeMessage = "Welcome to Biblioteca. " +
            "Your one-stop-shop for great book titles in Bangalore!\n";
    private final String initialBookListString =
            "id=1, title=Book A, stock=3\n" +
                    "id=2, title=Book B, stock=2\n" +
                    "id=3, title=Book C, stock=1\n";

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

    @Test
    public void testShouldPrintBookListAfterWelcomeMessage() {
        BibliotecaApp.main(null);

        // By removing the welcome message the book list message should be at
        // the beginning
        assertEquals(initialBookListString,
                outContent.toString().replace(welcomeMessage, ""));
    }
}
