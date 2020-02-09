import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ItemsToStringHelperTest {
    @Test
    public void testListToString() {
        List<ItemStock<Book>> books = new ArrayList<>();
        Book book1 = new Book(1, "Book A", "Dafu", 2010);
        Book book2 = new Book(2, "Book B", "Dafu", 2010);

        books.add(new ItemStock<>(book1, 1));
        books.add(new ItemStock<>(book2, 2));

        ItemsToStringHelper<Book> helper = new ItemsToStringHelper<>(books);
        assertEquals(book1.toString() + ", stock=1\n"
                + book2.toString() + ", stock=2\n",
                helper.listToString());
    }
}
