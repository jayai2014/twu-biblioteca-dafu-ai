import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;

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
        inventory.loadData(itemStocks);

        itemStocks.add(new ItemStock<>(new TestItem(2, "B"), 1));
        inventory.loadData(itemStocks);
        assertArrayEquals(itemStocks.toArray(), inventory.findAll().toArray());
    }

    @Test
    public void testFindAllShouldReturnAllItems() {
        TestItemInventory inventory = new TestItemInventory();
        List<ItemStock<TestItem>> itemStocks = new ArrayList<>();
        itemStocks.add(new ItemStock<>(new TestItem(1, "A"), 1));
        inventory.loadData(itemStocks);
        assertArrayEquals(itemStocks.toArray(), inventory.findAll().toArray());
    }
}
