import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

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
     * Test we are able to checkout a book successfully when stock is sufficient
     * (and book id is valid)
     */
    @Test
    public void testShouldAbleToCheckoutBookSuccessfullyWhenStockSufficient() {
        BibliotecaApp app = new BibliotecaApp();
        List<ItemStock<Book>> books = new ArrayList<>();
        books.add(new ItemStock<>(new Book(1, "Book A", "Dafu", 2010), 1));
        app.loadBooksData(books);
        app.processBookCheckout("1");
        assertEquals("Book{id=1, title=Book A, author=Dafu, year=2010}, stock=0\n",
                app.getAllBooksString());

        assertEquals(Messages.CHECKOUT_SUCCESS_MESSAGE, outContent.toString());
    }

    /**
     * Test we are not able to checkout a book when stock is insufficient
     */
    @Test
    public void testShouldNotBeAbleToCheckoutBookWhenStockInsufficient() {
        BibliotecaApp app = new BibliotecaApp();
        List<ItemStock<Book>> books = new ArrayList<>();
        books.add(new ItemStock<>(new Book(1, "Book A", "Dafu", 2010), 0));
        app.loadBooksData(books);
        app.processBookCheckout("1");

        assertEquals(Messages.CHECKOUT_FAILURE_MESSAGE, outContent.toString());
    }

    /**
     * Test we are not able to checkout a book when book id is invalid
     */
    @Test()
    public void testShouldNotBeAbleToCheckoutBookWhenBookIdInvalid() {
        BibliotecaApp app = new BibliotecaApp();
        List<ItemStock<Book>> books = new ArrayList<>();
        books.add(new ItemStock<>(new Book(1, "Book A", "Dafu", 2010), 1));
        app.loadBooksData(books);
        // Two cases
        app.processBookCheckout("0");
        assertEquals(Messages.CHECKOUT_FAILURE_MESSAGE, outContent.toString());

        outContent.reset();
        app.processBookCheckout("a");
        assertEquals(Messages.CHECKOUT_FAILURE_MESSAGE, outContent.toString());
    }

    /**
     * Test we are able to checkout a book successfully
     * (when book id is valid)
     */
    @Test
    public void testShouldAbleToReturnBookSuccessfully() {
        BibliotecaApp app = new BibliotecaApp();
        List<ItemStock<Book>> books = new ArrayList<>();
        books.add(new ItemStock<>(new Book(1, "Book A", "Dafu", 2010), 1));
        app.loadBooksData(books);
        app.processBookReturn("1");

        assertEquals(Messages.RETURN_SUCCESS_MESSAGE, outContent.toString());
    }

    /**
     * Test we are not able to return a book when book id is invalid
     */
    @Test
    public void testShouldNotBeAbleToReturnBookWhenBookIdInvalid() {
        BibliotecaApp app = new BibliotecaApp();
        List<ItemStock<Book>> books = new ArrayList<>();
        books.add(new ItemStock<>(new Book(1, "Book A", "Dafu", 2010), 1));
        app.loadBooksData(books);
        // Two cases
        app.processBookReturn("0");
        assertEquals(Messages.RETURN_FAILURE_MESSAGE, outContent.toString());

        outContent.reset();
        app.processBookCheckout("a");
        assertEquals(Messages.CHECKOUT_FAILURE_MESSAGE, outContent.toString());
    }
}
