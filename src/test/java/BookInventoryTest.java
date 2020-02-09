import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class BookInventoryTest {
    @Test
    public void testCheckoutBookShouldUpdateUserCheckouts()
            throws InsufficientItemStockException,
            ItemNotExistException,
            InvalidCredentialsException {
        UserRegistry userRegistry = UserRegistryFactory.create();
        userRegistry.login(UserRegistryFactory.CUSTOMER.getUsername(), UserRegistryFactory.CUSTOMER.getPassword());
        BookInventory bookInventory = new BookInventory(userRegistry);
        List<ItemStock<Book>> books = new ArrayList<>();
        Book book = new Book(1, "Book A", "Dafu", 2010);
        books.add(new ItemStock<>(book, 1));
        bookInventory.loadItemStockData(books);

        bookInventory.checkoutItem(1);
        BookCheckout bookCheckout = bookInventory.getUserCheckouts().get(0);
        assertSame(UserRegistryFactory.CUSTOMER, bookCheckout.getUser());
        assertSame(book, bookCheckout.getBook());
        assertSame(false, bookCheckout.isReturned());
    }

    @Test
    public void testReturnBookShouldIncrementStockQtyAndFlagCheckoutReturned()
            throws ItemNotExistException, InsufficientItemStockException {
        BookInventory bookInventory = new BookInventory(UserRegistryFactory.create());
        List<ItemStock<Book>> books = new ArrayList<>();
        books.add(new ItemStock<>(new Book(1, "Book A", "Dafu", 2010), 1));
        bookInventory.loadItemStockData(books);
        bookInventory.checkoutItem(1);
        bookInventory.returnBook(1);
        assertSame(1, bookInventory.getItemStock(1).getStockQty());

        BookCheckout bookCheckout = bookInventory.getUserCheckouts().get(0);
        assertSame(true, bookCheckout.isReturned());
    }

    @Test
    public void testReturnBookShouldRunWhenThereIsNoCheckoutRecord()
            throws ItemNotExistException {
        BookInventory bookInventory = new BookInventory(UserRegistryFactory.create());
        List<ItemStock<Book>> books = new ArrayList<>();
        books.add(new ItemStock<>(new Book(1, "Book A", "Dafu", 2010), 1));
        bookInventory.loadItemStockData(books);

        // Return with no previous checkout record
        bookInventory.returnBook(1);
        assertSame(2, bookInventory.getItemStock(1).getStockQty());
    }
}
