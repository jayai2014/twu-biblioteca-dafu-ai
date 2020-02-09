import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ItemInventoryTest {
    private static class TestItem extends Item {
        public TestItem(int id, String title) {
            super(id, title);
        }
    }

    private static class TestItemInventory extends ItemInventory<TestItem> {
    }

    @Test
    public void testLoadDataShouldUpdateInventory() {
        TestItemInventory inventory = new TestItemInventory();
        List<ItemStock<TestItem>> itemStocks = new ArrayList<>();
        itemStocks.add(new ItemStock<>(new TestItem(1, "A"), 1));
        inventory.loadItemStockData(itemStocks);

        itemStocks.add(new ItemStock<>(new TestItem(2, "B"), 1));
        inventory.loadItemStockData(itemStocks);
        assertArrayEquals(itemStocks.toArray(), inventory.findAll().toArray());
    }

    @Test
    public void testFindAllShouldReturnAllItems() {
        TestItemInventory inventory = new TestItemInventory();
        List<ItemStock<TestItem>> itemStocks = new ArrayList<>();
        itemStocks.add(new ItemStock<>(new TestItem(1, "A"), 1));
        inventory.loadItemStockData(itemStocks);
        assertArrayEquals(itemStocks.toArray(), inventory.findAll().toArray());
    }

    @Test
    public void testCheckoutItemShouldDecrementStockQtyWhenIdIsValid()
            throws InsufficientItemStockException, ItemNotExistException {
        TestItemInventory inventory = new TestItemInventory();
        List<ItemStock<TestItem>> itemStocks = new ArrayList<>();
        itemStocks.add(new ItemStock<>(new TestItem(1, "A"), 1));
        inventory.loadItemStockData(itemStocks);
        inventory.checkoutItem(1);

        assertSame(0, inventory.getItemStock(1).getStockQty());
    }

    @Test
    public void testCheckoutItemShouldThrowExceptionWhenIdIsInvalid()
            throws InsufficientItemStockException, ItemNotExistException {
        TestItemInventory inventory = new TestItemInventory();
        List<ItemStock<TestItem>> itemStocks = new ArrayList<>();
        itemStocks.add(new ItemStock<>(new TestItem(1, "A"), 1));
        inventory.loadItemStockData(itemStocks);
        inventory.checkoutItem(1);

        assertSame(0, inventory.getItemStock(1).getStockQty());
    }
}
