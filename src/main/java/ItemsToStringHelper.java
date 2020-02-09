import java.util.List;

public class ItemsToStringHelper<I extends Item> {
    private List<ItemStock<I>> items;

    public ItemsToStringHelper(List<ItemStock<I>> items) {
        this.items = items;
    }

    public String listToString() {
        StringBuilder builder = new StringBuilder();
        for (ItemStock<I> itemStock : items) {
            builder.append(itemStock.getItem().toString())
                    .append(", stock=")
                    .append(itemStock.getStockQty())
                    .append("\n");
        }
        return builder.toString();
    }
}
