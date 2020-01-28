import org.junit.Test;

import static org.junit.Assert.*;

public class BookTest {
    Book book = new Book(1, "Book A", "Dafu", 2011, 2);
    /**
     * Test we can convert a Book object to the correct string representation.
     */
    @Test
    public void testShouldConvertToCorrectString() {
        assertEquals("id=1|title=Book A|author=Dafu|year=2011|stock=2", book.toString());
    }

    /**
     * Test we can increment stock quantity correctly
     */
    @Test
    public void testShouldIncrementStockCorrectly() {
        int prevStock = book.getStock();
        // Use a boundary value offset
        book.incrementStock( 1);
        assertSame(prevStock + 1, book.getStock());
    }

    /**
     * Test we can correctly decrement stock quantity when it's sufficient
     * @throws InsufficientBookStockException is NOT expected
     */
    @Test
    public void testShouldDecrementStockCorrectlyWhenStockIsSufficient()
            throws InsufficientBookStockException {
        int prevStock = book.getStock();
        // Use a boundary value offset
        book.decrementStock(2);
        assertSame(prevStock - 2, book.getStock());
    }

    /**
     * Test we can correctly decrement stock quantity when it's insufficient
     * @throws InsufficientBookStockException is expected
     */
    @Test(expected = InsufficientBookStockException.class)
    public void testShouldDecrementStockCorrectlyWhenStockIsInsufficient()
            throws InsufficientBookStockException {
        int prevStock = book.getStock();
        // Use a boundary value offset
        book.decrementStock(prevStock + 1);
    }
}
