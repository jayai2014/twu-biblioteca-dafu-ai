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
        return "Enter a number from options below to make a selection: \n" +
                "1 - List of books\n";
    }

    public static void main(String[] args) {
        System.out.print(getWelcomeMessage());
        System.out.print(getMenuOptions());

        Scanner in = new Scanner(System.in);

        if (in.hasNextInt()) {
            int chosenOption = in.nextInt();

            if (chosenOption == 1) {
                System.out.print(getAllBooksString());
            }
        }
    }
}
