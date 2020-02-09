import org.junit.Test;

import static org.junit.Assert.*;

public class InsufficientItemStockExceptionTest {
    /**
     * Test we should report item information when constructing a new exception
     * instance
     */
    @Test
    public void testShouldReportItemInformation() {
        Book book = new Book(1, "Book A", "Dafu", 2011);
        InsufficientItemStockException e = new InsufficientItemStockException(book);

        assertEquals("Insufficient item stock: " + book.toString(), e.getMessage());
    }
}
