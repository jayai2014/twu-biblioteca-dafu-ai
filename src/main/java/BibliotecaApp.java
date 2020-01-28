import java.util.ArrayList;
import java.util.Scanner;

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

    /**
     * Get a menu of options in string
     */
    public static String getMenuOptions() {
        return "Enter an option below:\n" +
                "1 - List of books\n" +
                "c[book id] - Checkout a book\n" +
                "r[book id] - Return a book\n" +
                "0 - Quit\n";
    }

    private static final String invalidOptionMessage =
            "Please select a valid option!\n";

    public static void main(String[] args) {
        System.out.print(getWelcomeMessage());
        System.out.print(getMenuOptions());

        Scanner in = new Scanner(System.in);

        // Repeatedly ask user for input unless user chooses to exit
        while (true) {
            if(!processInput(in)) {
                break;
            }
        }
    }

    /**
     * Process user input
     * @param input user input
     * @return true if user chooses to exit; false otherwise
     */
    public static boolean processInput(Scanner input) {
        String chosenOption = input.next();

        if (chosenOption.equals("1")) {
            System.out.print(getAllBooksString());
        } else if (chosenOption.equals("0")) {
            return false;
        } else if (chosenOption.startsWith("c")) {
            processBookCheckout(chosenOption.substring(1));
        } else {
            System.out.print(invalidOptionMessage);
        }
        return true;
    }

    /**
     * Process book checkout
     * @param bookIdStr book id in string, which will be validated first
     */
    public static void processBookCheckout(String bookIdStr) {
        // todo: error handling for int conversion
        int bookId = Integer.parseInt(bookIdStr);
        try {
            BookManager.getInstance().checkoutBook(1);
        } catch (InsufficientBookStockException e) {
            // todo: error handling
            e.printStackTrace();
        }
    }
}
