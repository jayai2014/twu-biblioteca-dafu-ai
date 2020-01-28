import org.junit.Test;

import static org.junit.Assert.*;

public class InsufficientBookStockExceptionTest {
    /**
     * Test we should report correct message when constructing a new exception
     * instance
     */
    @Test
    public void testShouldReportCorrectMessage() {
        Book book = new Book(1, "Book A", "Dafu", 2011, 1);
        InsufficientBookStockException e = new InsufficientBookStockException(book);

        assertEquals("Insufficient book stock: " + book.toString(), e.getMessage());
    }
}
