import org.junit.Test;
import static org.junit.Assert.*;

public class BookTest {
    /**
     * Test we can convert a Book object to the correct string representation.
     */
    @Test
    public void testShouldConvertToCorrectString() {
        Book book = new Book(1, "Book A", "Dafu", 2011, 2);
        assertEquals("id=1|title=Book A|author=Dafu|year=2011|stock=2", book.toString());
    }
}
