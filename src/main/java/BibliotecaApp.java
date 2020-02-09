import java.util.*;

public class BibliotecaApp {
    private UserRegistry userRegistry = new UserRegistry();
    private BookInventory bookInventory = new BookInventory(userRegistry);
    private MovieInventory movieInventory = new MovieInventory();

    public void loadBooksData(List<ItemStock<Book>> books) {
        bookInventory.loadItemStockData(books);
    }

    public void loadMoviesData(List<ItemStock<Movie>> movies) {
        movieInventory.loadItemStockData(movies);
    }

    public void loadUsersData(List<User> users) {
        userRegistry.loadData(users);
    }

    public void loadUserCheckoutData(Map<User, List<BookCheckout>> userCheckouts) {
        bookInventory.loadCheckoutData(userCheckouts);
    }

    public UserRole getCurrentUserRole() {
        return userRegistry.getCurrentUserRole();
    }

    public String getCurrentUserProfile() {
        return userRegistry.getCurrentUser().toString();
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

    public String getAllBookCheckoutsString() {
        List<BookCheckout> bookCheckouts = bookInventory.getUserCheckouts();
        BookCheckoutsToStringHelper helper = new BookCheckoutsToStringHelper(bookCheckouts);
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

    public boolean processLogin(String username, String password) {
        try {
            userRegistry.login(username, password);
        } catch (InvalidCredentialsException e) {
            System.out.print(Messages.INVALID_USER_CREDENTIALS_MESSAGE);
            return false;
        }
        return true;
    }

    /**
     * @return true if user chooses to exit; false otherwise
     */
    public static boolean processInput(Scanner input, BibliotecaApp app) {
        String chosenOption = input.next();

        MenuOption chosenMenuOption;
        if (chosenOption.equals(MenuOption.QUIT.getSymbol())) {
            chosenMenuOption = MenuOption.QUIT;
        } else if (chosenOption.equals(MenuOption.LOGIN.getSymbol())) {
            chosenMenuOption = MenuOption.LOGIN;
        } else if (chosenOption.equals(MenuOption.LIST_BOOKS.getSymbol())) {
            chosenMenuOption = MenuOption.LIST_BOOKS;
        } else if (chosenOption.startsWith(MenuOption.CHECKOUT_BOOK.getSymbol())) {
            chosenMenuOption = MenuOption.CHECKOUT_BOOK;
        } else if (chosenOption.startsWith(MenuOption.RETURN_BOOK.getSymbol())) {
            chosenMenuOption = MenuOption.RETURN_BOOK;
        } else if (chosenOption.startsWith(MenuOption.LIST_MOVIES.getSymbol())) {
            chosenMenuOption = MenuOption.LIST_MOVIES;
        } else if (chosenOption.startsWith(MenuOption.CHECKOUT_MOVIE.getSymbol())) {
            chosenMenuOption = MenuOption.CHECKOUT_MOVIE;
        } else if (chosenOption.startsWith(MenuOption.LIST_BOOK_CHECKOUTS.getSymbol())) {
            chosenMenuOption = MenuOption.LIST_BOOK_CHECKOUTS;
        } else if (chosenOption.equals(MenuOption.VIEW_PROFILE.getSymbol())) {
            chosenMenuOption = MenuOption.VIEW_PROFILE;
        } else {
            System.out.print(Messages.INVALID_OPTION_MESSAGE);
            return true;
        }

        // Check permission
        UserRole requiredRole = chosenMenuOption.getUserRole();
        if (requiredRole != null && requiredRole != app.getCurrentUserRole()) {
            System.out.print(Messages.INVALID_OPTION_MESSAGE);
            return true;
        }

        switch (chosenMenuOption) {
            case QUIT:
                return false;
            case LOGIN:
                System.out.println("Enter username (xxx-xxxx):");
                String username = input.next();
                System.out.println("Enter password:");
                String password = input.next();
                if (app.processLogin(username, password)) {
                    // Print updated menu after successful login
                    System.out.print(MenuOption.getAllOptionsString(app));
                }
                break;
            case LIST_BOOKS:
                System.out.print(app.getAllBooksString());
                break;
            case CHECKOUT_BOOK:
                app.processBookCheckout(chosenOption.substring(2));
                break;
            case RETURN_BOOK:
                app.processBookReturn(chosenOption.substring(2));
                break;
            case LIST_MOVIES:
                System.out.print(app.getAllMoviesString());
                break;
            case CHECKOUT_MOVIE:
                app.processMovieCheckout(chosenOption.substring(2));
                break;
            case LIST_BOOK_CHECKOUTS:
                System.out.print(app.getAllBookCheckoutsString());
                break;
            case VIEW_PROFILE:
                System.out.println(app.getCurrentUserProfile());
                break;
            default:
                break;
        }

        return true;
    }

    public static void loadData(BibliotecaApp app) {
        List<ItemStock<Book>> books = new ArrayList<>();
        Book book1 = new Book(1, "Book A", "Dafu", 2010);
        books.add(new ItemStock<>(book1, 3));
        books.add(new ItemStock<>(new Book(2, "Book B", "Dafu", 2011), 2));
        books.add(new ItemStock<>(new Book(3, "Book C", "Dafu", 2012), 1));
        app.loadBooksData(books);

        List<ItemStock<Movie>> movies = new ArrayList<>();
        movies.add(new ItemStock<>(new Movie(1, "Movie A", 2011,
                "Dafu", 10), 1));
        movies.add(new ItemStock<>(new Movie(2, "Movie B", 2012,
                "Dafu", 10), 2));
        app.loadMoviesData(movies);

        List<User> users = new ArrayList<>();
        users.add(new Librarian("000-0001", "123", "Libraian Dafu"));
        Customer customer = new Customer("000-0002", "123",
                "User Dafu",
                "dafu.ai819@gmail.com",
                "0478057208");
        users.add(customer);
        app.loadUsersData(users);

        Map<User, List<BookCheckout>> userCheckouts = new HashMap<>();
        List<BookCheckout> checkouts = new ArrayList<>();
        checkouts.add(new BookCheckout(customer, book1));
        userCheckouts.put(customer, checkouts);
        app.loadUserCheckoutData(userCheckouts);
    }

    public static void main(String[] args) {
        BibliotecaApp app = new BibliotecaApp();
        loadData(app);

        System.out.print(Messages.WELCOME_MESSAGE);
        System.out.print(MenuOption.getAllOptionsString(app));

        Scanner in = new Scanner(System.in);

        // Repeatedly ask user for input unless user chooses to exit
        while (true) {
            if(!processInput(in, app)) {
                break;
            }
        }
    }
}
