import org.junit.Test;
import static org.junit.Assert.*;

public class BookTest {
    /**
     * Test we can convert a Book object to the correct string representation.
     */
    @Test
    public void testShouldConvertToCorrectString() {
        Book book = new Book(1, "Book A", 2);
        assertEquals("id=1, title=Book A, stock=2", book.toString());
    }
}
