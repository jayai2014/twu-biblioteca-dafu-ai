public class ItemStock<I extends Item> {
    private I item;
    private int stockQty;

    public ItemStock(I item, int stock) {
        this.item = item;
        this.stockQty = stock;
    }

    public I getItem() {
        return item;
    }

    public int getStockQty() {
        return stockQty;
    }

    public void incrementStockQty(int stockQty) {
        this.stockQty += stockQty;
    }

    public void decrementStockQty(int stockQty) throws InsufficientItemStockException {
        if (this.stockQty - stockQty < 0) {
            throw new InsufficientItemStockException(item);
        }

        this.stockQty -= stockQty;
    }
}
