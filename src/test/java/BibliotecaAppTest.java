import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertTrue;


public class BibliotecaAppTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

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
    public void testShouldPrintWelcomeMessage() {
        BibliotecaApp.main(null);
        String welcomeMessage = "Welcome to Biblioteca. " +
                "Your one-stop-shop for great book titles in Bangalore!\n";
        assertTrue(outContent.toString().startsWith(welcomeMessage));
    }
}
