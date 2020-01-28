public class InsufficientBookStockException extends Exception {
    public InsufficientBookStockException(Book book) {
        super("Insufficient book stock: " + book.toString());
    }
}
