import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MenuOptionTest {
    @Test
    public void testAllOptionStringReturnAllInformation() {
        assertEquals("Available options:\n" +
                "0 - Quit\n" +
                "b - List all books\n" +
                "cb - Checkout a book (e.g. cb1)\n" +
                "rb - Return a book (e.g. rb1)\n" +
                "m - List all movies\n" +
                "cm - Checkout a movie (e.g. cm1)\n",
                MenuOption.getAllOptionsString());
    }
}
