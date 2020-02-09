public class InsufficientItemStockException extends Exception {
    public InsufficientItemStockException(Item item) {
        super("Insufficient item stock: " + item.toString());
    }
}
