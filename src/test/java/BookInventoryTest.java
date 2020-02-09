import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertSame;

public class BookInventoryTest {
    @Test
    public void testReturnBookShouldIncrementStockQty() throws ItemNotExistException {
        BookInventory bookInventory = new BookInventory();
        List<ItemStock<Book>> books = new ArrayList<>();
        books.add(new ItemStock<>(new Book(1, "Book A", "Dafu", 2010), 1));
        bookInventory.loadData(books);

        bookInventory.returnBook(1);
        assertSame(2, bookInventory.getItemStock(1).getStockQty());
    }
}
