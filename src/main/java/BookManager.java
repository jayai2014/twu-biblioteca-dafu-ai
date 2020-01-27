import java.util.ArrayList;

/**
 * Manager class for books data
 */
class BookManager {
    private static final BookManager instance = new BookManager();
    private BookManager() {}

    public static BookManager getInstance(){
        return instance;
    }

    private ArrayList<Book> books = null;

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
     * Retrieve all available books.
     * @return list of books
     */
    ArrayList<Book> getAllBooks() {
        if (books == null) {
            // Initialise fixtures once in runtime
            books = getBooksData();
        }
        return books;
    }
}
