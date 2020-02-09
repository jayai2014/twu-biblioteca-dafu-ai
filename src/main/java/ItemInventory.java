import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ItemInventory<I extends Item> {
    protected Map<Integer, ItemStock<I>> inv = new HashMap<>();

    public void loadItemStockData(List<ItemStock<I>> itemStocks) {
        for (ItemStock<I> itemStock: itemStocks) {
            this.inv.put(itemStock.getItem().getId(), itemStock);
        }
    }

    public List<ItemStock<I>> findAll() {
        return new ArrayList<>(inv.values());
    }

    void checkoutItem(int itemId)
            throws InsufficientItemStockException, ItemNotExistException {
        getItemStock(itemId).decrementStockQty(1);
    }

    ItemStock<I> getItemStock(int itemId) throws ItemNotExistException {
        if (!inv.containsKey(itemId)) {
            throw new ItemNotExistException(itemId);
        }
        return inv.get(itemId);
    }
}
