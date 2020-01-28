import java.util.ArrayList;
import java.util.HashMap;

/**
 * Manager class for books data
 */
class BookManager {
    private static final BookManager instance = new BookManager();
    private BookManager() {}

    public static BookManager getInstance(){
        return instance;
    }

    /**
     * Use map for fast querying
     */
    private HashMap<Integer, Book> books = null;

    /**
     * Use data fixtures for now
     */
    ArrayList<Book> getBooksData() {
        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book(1, "Book A", "Dafu", 2010, 3));
        books.add(new Book(2, "Book B", "Dafu", 2011, 2));
        books.add(new Book(3, "Book C", "Dafu", 2012, 1));
        return books;
    }

    /**
     * Initialise book data fixtures
     * Should be called only once in runtime lifetime
     */
    void initialiseBookData() {
        ArrayList<Book> data = getBooksData();
        books = new HashMap<>();
        for (Book book: data) {
            books.put(book.getId(), book);
        }
    }

    /**
     * Retrieve all available books.
     * @return list of books
     */
    ArrayList<Book> getAllBooks() {
        if (books == null) {
            initialiseBookData();
        }
        return new ArrayList<>(books.values());
    }

    /**
     * Checkout a book
     * @param bookId id of the book
     */
    void checkoutBook(int bookId)
            throws InsufficientBookStockException, BookNotExistException {
        getBook(bookId).decrementStock(1);
    }

    /**
     * Return a book
     * @param bookId id of the book
     */
    void returnBook(int bookId) throws BookNotExistException {
        getBook(bookId).incrementStock(1);
    }

    /**
     * Get a book, safely
     * Avoid using books.get()
     * @param bookId id of the book
     * @return the book associated with thd id
     */
    Book getBook(int bookId) throws BookNotExistException {
        if (books == null) {
            initialiseBookData();
        }
        // Checks we have the book with provided id
        if (!books.containsKey(bookId)) {
            throw new BookNotExistException(bookId);
        }
        return books.get(bookId);
    }
}
