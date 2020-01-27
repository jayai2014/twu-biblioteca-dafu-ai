import org.junit.Test;

import static org.junit.Assert.*;

public class BookManagerTest {
    /**
     * Test book list is initialised for the first time we request data from
     * book manager
     */
    @Test
    public void testShouldInitialiseData() {
        assertNotNull(BookManager.getInstance().getAllBooks());
    }
}
