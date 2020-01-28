import org.junit.Test;

import static org.junit.Assert.*;

public class BookManagerTest {
    /**
     * Test data fixtures is initialised for the first time we request data from
     * book manager
     */
    @Test
    public void testShouldInitialiseDataWhenGetAllBooks() {
        assertNotNull(BookManager.getInstance().getAllBooks());
    }

    /**
     * Test data fixtures is initialised for the first time we request data from
     * book manager
     */
    @Test
    public void testShouldInitialiseDataWhenGetOneBook() throws BookNotExistException {
        assertNotNull(BookManager.getInstance().getBook(1));
    }

    /**
     * Test book should be updated when we checkout one book
     */
    @Test
    public void testShouldUpdateBookWhenCheckoutBook()
            throws InsufficientBookStockException, BookNotExistException {
        BookManager bookManager = BookManager.getInstance();
        int bookId = 1;
        Book book = bookManager.getBook(bookId);
        int prevStock = book.getStock();

        bookManager.checkoutBook(bookId);

        // Stock quantity should be updated
        assertSame(prevStock - 1, bookManager.getBook(bookId).getStock());
    }

    /**
     * Test we can throw book not exist exception when we supply an invalid
     * book id to the getBook() method
     */
    @Test(expected = BookNotExistException.class)
    public void testShouldThrowExceptionWhenUseInvalidBookId()
            throws BookNotExistException {
        BookManager bookManager = BookManager.getInstance();
        bookManager.getBook(0);
    }
}
