import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BookNotExistExceptionTest {
    /**
     * Test we should report correct message when constructing a new exception
     * instance
     */
    @Test
    public void testShouldReportCorrectMessage() {
        BookNotExistException e = new BookNotExistException(1);
        assertEquals("Book does not exist: " + 1, e.getMessage());
    }
}
