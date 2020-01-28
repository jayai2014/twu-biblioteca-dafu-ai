public class BookNotExistException extends Exception{
    public BookNotExistException(int bookId) {
        super("Book does not exist: " + bookId);
    }
}
