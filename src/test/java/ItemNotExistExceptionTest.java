import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ItemNotExistExceptionTest {
    /**
     * Test we should report correct message when constructing a new exception
     * instance
     */
    @Test
    public void testShouldReportItemInformation() {
        ItemNotExistException e = new ItemNotExistException(1);
        assertEquals("Item does not exist: " + 1, e.getMessage());
    }
}
