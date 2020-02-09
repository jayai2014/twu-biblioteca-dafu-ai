import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ItemInventory<I extends Item> {
    protected Map<Integer, ItemStock<I>> inv = new HashMap<>();

    public void loadData(List<ItemStock<I>> itemStocks) {
        for (ItemStock<I> itemStock: itemStocks) {
            this.inv.put(itemStock.getItem().getId(), itemStock);
        }
    }

    public List<ItemStock<I>> findAll() {
        return new ArrayList<>(inv.values());
    }
}
