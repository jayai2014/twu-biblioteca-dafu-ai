import java.util.ArrayList;

public class BibliotecaApp {
    /**
     * Retrieve welcome message
     */
    public static String getWelcomeMessage() {
        return "Welcome to Biblioteca. Your one-stop-shop for " +
                "great book titles in Bangalore!\n";
    }

    /**
     * Retrieve the book list in string representation
     * @return list of books in string
     */
    public static String getAllBooksString() {
        ArrayList<Book> books = BookManager.getInstance().getAllBooks();
        StringBuilder booksString = new StringBuilder();
        for (Book book : books) {
            booksString.append(book.toString()).append("\n");
        }
        return booksString.toString();
    }

    public static void main(String[] args) {
        System.out.print(getWelcomeMessage());
        System.out.print(getAllBooksString());
    }
}
