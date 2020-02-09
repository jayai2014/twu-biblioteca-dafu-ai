import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BibliotecaApp {
    private BookInventory bookInventory = new BookInventory();
    private MovieInventory movieInventory = new MovieInventory();

    public void loadBooksData(List<ItemStock<Book>> books) {
        bookInventory.loadData(books);
    }

    public void loadMoviesData(List<ItemStock<Movie>> movies) {
        movieInventory.loadData(movies);
    }

    public String getAllBooksString() {
        List<ItemStock<Book>> books = bookInventory.findAll();
        ItemsToStringHelper<Book> helper = new ItemsToStringHelper<>(books);
        return helper.listToString();
    }

    public String getAllMoviesString() {
        List<ItemStock<Movie>> movies = movieInventory.findAll();
        ItemsToStringHelper<Movie> helper = new ItemsToStringHelper<>(movies);
        return helper.listToString();
    }

    public void processBookCheckout(String bookIdStr) {
        int bookId;
        try {
            bookId = Integer.parseInt(bookIdStr);
        } catch (NumberFormatException e) {
            // We need to ensure the book id is an integer
            System.out.print(Messages.CHECKOUT_BOOK_FAILURE_MESSAGE);
            return;
        }

        try {
            bookInventory.checkoutItem(bookId);
        } catch (InsufficientItemStockException | ItemNotExistException e) {
            // We also need to ensure book id exist and stock is sufficient
            System.out.print(Messages.CHECKOUT_BOOK_FAILURE_MESSAGE);
            return;
        }

        System.out.print(Messages.CHECKOUT_BOOK_SUCCESS_MESSAGE);
    }

    public void processBookReturn(String bookIdStr) {
        int bookId;
        try {
            bookId = Integer.parseInt(bookIdStr);
        } catch (NumberFormatException e) {
            // We need to ensure the book id is an integer
            System.out.print(Messages.RETURN_BOOK_FAILURE_MESSAGE);
            return;
        }

        try {
            bookInventory.returnBook(bookId);
        } catch (ItemNotExistException e) {
            // We also need to ensure book id exist and stock is sufficient
            System.out.print(Messages.RETURN_BOOK_FAILURE_MESSAGE);
            return;
        }

        System.out.print(Messages.RETURN_BOOK_SUCCESS_MESSAGE);
    }

    public void processMovieCheckout(String movieIdStr) {
        int movieId;
        try {
            movieId = Integer.parseInt(movieIdStr);
        } catch (NumberFormatException e) {
            // We need to ensure the movie id is an integer
            System.out.print(Messages.CHECKOUT_MOVIE_FAILURE_MESSAGE);
            return;
        }

        try {
            movieInventory.checkoutItem(movieId);
        } catch (InsufficientItemStockException | ItemNotExistException e) {
            // We also need to ensure movie id exist and stock is sufficient
            System.out.print(Messages.CHECKOUT_MOVIE_FAILURE_MESSAGE);
            return;
        }

        System.out.print(Messages.CHECKOUT_MOVIE_SUCCESS_MESSAGE);
    }

    /**
     * @return true if user chooses to exit; false otherwise
     */
    public static boolean processInput(Scanner input, BibliotecaApp app) {
        String chosenOption = input.next();

        if (chosenOption.equals(MenuOption.QUIT.getSymbol())) {
            return false;
        } else if (chosenOption.equals(MenuOption.LIST_BOOKS.getSymbol())) {
            System.out.print(app.getAllBooksString());
        } else if (chosenOption.startsWith(MenuOption.CHECKOUT_BOOK.getSymbol())) {
            app.processBookCheckout(chosenOption.substring(2));
        } else if (chosenOption.startsWith(MenuOption.RETURN_BOOK.getSymbol())) {
            app.processBookReturn(chosenOption.substring(2));
        } else if (chosenOption.startsWith(MenuOption.LIST_MOVIES.getSymbol())) {
            System.out.print(app.getAllMoviesString());
        } else if (chosenOption.startsWith(MenuOption.CHECKOUT_MOVIE.getSymbol())) {
            app.processMovieCheckout(chosenOption.substring(2));
        } else {
            System.out.print(Messages.INVALID_OPTION_MESSAGE);
        }
        return true;
    }

    public static void loadData(BibliotecaApp app) {
        List<ItemStock<Book>> books = new ArrayList<>();
        books.add(new ItemStock<>(new Book(1, "Book A", "Dafu", 2010), 3));
        books.add(new ItemStock<>(new Book(2, "Book B", "Dafu", 2011), 2));
        books.add(new ItemStock<>(new Book(3, "Book C", "Dafu", 2012), 1));
        app.loadBooksData(books);

        List<ItemStock<Movie>> movies = new ArrayList<>();
        movies.add(new ItemStock<>(new Movie(1, "Movie A", 2011,
                "Dafu", 10), 1));
        movies.add(new ItemStock<>(new Movie(2, "Movie B", 2012,
                "Dafu", 10), 2));
        app.loadMoviesData(movies);
    }

    public static void main(String[] args) {
        System.out.print(Messages.WELCOME_MESSAGE);
        System.out.print(MenuOption.getAllOptionsString());

        Scanner in = new Scanner(System.in);
        BibliotecaApp app = new BibliotecaApp();
        loadData(app);

        // Repeatedly ask user for input unless user chooses to exit
        while (true) {
            if(!processInput(in, app)) {
                break;
            }
        }
    }
}
